package com.example.sunday.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sunday.data.model.Ticker
import com.example.sunday.data.repository.CoinRepository
import com.example.sunday.network.response.bithumb.BithumbTickerResponse
import com.example.sunday.network.response.coinone.CoinoneResponse
import com.example.sunday.network.response.upbit.UpbitTickerResponse
import com.example.sunday.util.plusAssign
import com.example.sunday.util.withSchedulers
import com.google.gson.Gson
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function3

class MainViewModel(private val repository: CoinRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _tickerList = MutableLiveData<List<UpbitTickerResponse>>()
    val tickerList: LiveData<List<UpbitTickerResponse>> get() = _tickerList


    fun abc(baseCurrency: String){

        compositeDisposable += Single.zip(
            repository.getMarketList()
                .withSchedulers()
                .map { marketResponseList ->
                    marketResponseList.asSequence()
                        .map { it.market }
                        .filter {
                            it.split("-")[0] == baseCurrency
                        }
                        .toList()
                }
                .flatMap {
                    repository.getTickerList(it.joinToString())
                        .withSchedulers()
                        .map {
                            it.map {
                                it.toTicker()
                            }
                        }
                },
            repository.getBithumbTickerList()
                .withSchedulers()
                .map {
                    val gson = Gson()
                    it.item
                        .filter { it.key != "date" }
                        .map {(name, response) ->
                            gson.fromJson(response.toString(), BithumbTickerResponse::class.java).toTicker(name)
                        }
                },
            repository.getCoinoneTickerList()
                .withSchedulers()
                .map {
                    val gson =  Gson()
                    it.filter { it.key != "errorCode" && it.key != "timestamp" && it.key != "result" }
                        .map {
                            gson.fromJson(it.value.toString(), CoinoneResponse::class.java).toTicker()
                        }

                },
            Function3<List<Ticker>, List<Ticker>, List<Ticker>, String>{ t1, t2, t3 -> t3.toString() }
        )
            .subscribe({
                Log.d("API CALL :: ", it)
            }, {
                Log.e("API ERROR:: ", it.message!!)
            })
    }
    fun getBithumbTickerList() {
        var map2 = HashMap<String, BithumbTickerResponse>()
        compositeDisposable += repository.getBithumbTickerList()
            .withSchedulers()
            .map {
                val gson = Gson()
                it.item
                    .filter {
                        it.key != "date"
                    }
                    .map {(name, response) ->
                        val fromJson = gson.fromJson(response.toString(), BithumbTickerResponse::class.java)
                        fromJson
                    }
            }
            .subscribe({
                Log.d("BithumbAllResponse", it.toString())
            },{
                Log.e("error", it.message!!)

            })
    }
//    TODO : 여러곳에서 가져온 ticker를 통일시킬 필요가 있음

    fun getTickerList(baseCurrency: String) {
        compositeDisposable += repository.getMarketList()
            .withSchedulers()
            .map { marketResponseList ->
                marketResponseList.asSequence()
                    .map { it.market }
                    .filter {
                        it.split("-")[0] == baseCurrency
                    }
                    .toList()
            }
            .flatMap {
                repository.getTickerList(it.joinToString())
                    .withSchedulers()
            }
            .subscribe({
                _tickerList.value = it
            }, {
                Log.e("error", it.message!!)
            })

    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}