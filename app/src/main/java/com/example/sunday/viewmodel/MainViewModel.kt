package com.example.sunday.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sunday.data.enums.Exchange
import com.example.sunday.data.model.Ticker
import com.example.sunday.data.repository.ticker.TickerRepository
import com.example.sunday.network.response.bithumb.BithumbAllResponse
import com.example.sunday.network.response.bithumb.BithumbTickerResponse
import com.example.sunday.network.response.coinone.CoinoneResponse
import com.example.sunday.network.response.upbit.UpbitMarketResponse
import com.example.sunday.network.response.upbit.UpbitTickerResponse
import com.example.sunday.ui.model.UTicker
import com.example.sunday.util.plusAssign
import com.example.sunday.util.withSchedulers
import com.google.gson.Gson
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function3
import kotlin.math.min

class MainViewModel(private val repoMap: Map<String, TickerRepository>) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _tickerList = MutableLiveData<List<UTicker>>()
    val tickerList: LiveData<List<UTicker>> get() = _tickerList


    fun getTicker(baseCurrency: String){

        compositeDisposable += Single.zip(
            (repoMap[Exchange.UPBIT.exchangeName]?.getAllTicker() as Single<List<UpbitMarketResponse>>)
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
                    (repoMap[Exchange.UPBIT.exchangeName]?.getTicker(it.joinToString()) as Single<List<UpbitTickerResponse>>)
                        .withSchedulers()
                        .map {
                            it.map {
                                it.toTicker()
                            }
                        }
                },
            (repoMap[Exchange.BITHUMB.exchangeName]?.getAllTicker() as Single<BithumbAllResponse>)
                .withSchedulers()
                .map {
                    val gson = Gson()
                    it.item
                        .filter { it.key != "date" }
                        .map {(name, response) ->
                            gson.fromJson(response.toString(), BithumbTickerResponse::class.java).toTicker(name)
                        }
                },
            (repoMap[Exchange.COINONE.exchangeName]?.getAllTicker() as Single<Map<String, Any>>)
                .withSchedulers()
                .map {
                    val gson =  Gson()
                    it.filter { it.key != "errorCode" && it.key != "timestamp" && it.key != "result" }
                        .map {
                            gson.fromJson(it.value.toString(), CoinoneResponse::class.java).toTicker()
                        }

                },
            Function3<List<Ticker>, List<Ticker>, List<Ticker>, List<UTicker>>{ t1, t2, t3 ->
                val list: MutableList<UTicker> = mutableListOf()
                t1.forEach {
                    val name: String? = it.currency?.toUpperCase()
                    val last1: Double? = it.last
                    t2.forEach{
                        val last2: Double? = it.last
                        if(name == it.currency?.toUpperCase()){
                         t3.forEach {
                             val last3: Double? = it.last
                             if(name == it.currency?.toUpperCase()){
                                 list.add(UTicker(name,last1,last2,last3,
                                     when(min(min(last1!!, last2!!), last3!!)){
                                         last1 -> Exchange.UPBIT.exchangeName
                                         last2 -> Exchange.BITHUMB.exchangeName
                                         else -> Exchange.COINONE.exchangeName
                                     }))

                                 return@forEach
                             }
                         }
                            return@forEach
                        }
                    }
                }
                list!!
            }
        )
            .subscribe({
                _tickerList.value = it
            }, {
                Log.e("Ticker API ERROR:: ", it.message!!)
            })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}