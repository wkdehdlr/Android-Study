package com.example.sunday.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sunday.data.repository.CoinRepository
import com.example.sunday.data.response.bithumb.BithumbTickerResponse
import com.example.sunday.data.response.upbit.UpbitTickerResponse
import com.example.sunday.util.plusAssign
import com.example.sunday.util.withSchedulers
import com.google.gson.Gson
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction

class MainViewModel(private val repository: CoinRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _tickerList = MutableLiveData<List<UpbitTickerResponse>>()
    val tickerList: LiveData<List<UpbitTickerResponse>> get() = _tickerList


    fun abc(baseCurrency: String){

        val subscribe = Single.zip(
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
                },
            repository.getBithumbTickerList()
                .withSchedulers()
                .map {
                    var map2 = HashMap<String, BithumbTickerResponse>()
                    val gson = Gson()
                    it.item
                        .filter { it.key != "date" }
                        .map {(name, response) ->
                            map2.put(name, gson.fromJson(response.toString(), BithumbTickerResponse::class.java))
                            gson.fromJson(response.toString(), BithumbTickerResponse::class.java)
                        }.run { this.toHashSet() }
                },
            BiFunction<List<UpbitTickerResponse>, HashSet<BithumbTickerResponse>, String> { t1, t2 ->
                 "str" }
        )
            .subscribe({

            }, {

            })
    }
    fun getBithumbTickerList() {
        var map2 = HashMap<String, BithumbTickerResponse>()
        compositeDisposable += repository.getBithumbTickerList()
            .withSchedulers()
            .map {
                val gson = Gson()
                it.item
                    .filter { it.key != "date" }
                    .map {(name, response) ->
                        val fromJson = gson.fromJson(response.toString(), BithumbTickerResponse::class.java)
                        fromJson.name = name
                        fromJson
                    }
            }
            .subscribe({
                Log.d("BithumbAllResponse", it.toString())
            },{
                Log.e("error", it.message!!)

            })
    }
//    두군데에서 가져온 ticker를 통일시킬 필요가 있음

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