package com.example.sunday.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sunday.data.enums.Exchange
import com.example.sunday.data.model.Ticker
import com.example.sunday.data.repository.ticker.TickerRepository
import com.example.sunday.network.response.bithumb.BithumbAllResponse
import com.example.sunday.network.response.bithumb.BithumbTickerResponse
import com.example.sunday.network.response.coinone.CoinoneResponse
import com.example.sunday.network.response.upbit.UpbitMarketResponse
import com.example.sunday.network.response.upbit.UpbitTickerResponse
import com.example.sunday.ui.model.UTicker
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.math.min

class MainViewModel(private val repoMap: Map<String, TickerRepository>) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _tickerList = MutableLiveData<List<UTicker>>()
    val tickerList: LiveData<List<UTicker>> get() = _tickerList

    fun getTicker(baseCurrency: String): Job{
        return viewModelScope.launch {
            try{
                val upbitMarketData = getUpbitMarketData(baseCurrency)
                val t1 = getUpbitTickerData(upbitMarketData)

                val t2 = getBithumbTickerList()
                val t3 = getCoinoneTickerList()

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
                _tickerList.value = list

            }catch (error: Exception){
                Log.e("Coroutine ::", error.toString())
            }
        }
    }

    private suspend fun getUpbitMarketData(baseCurrency: String): List<String>{
        return (repoMap[Exchange.UPBIT.exchangeName]?.getAllTicker() as List<UpbitMarketResponse>)
            .map { it.market }
            .filter { it.split("-")[0] == baseCurrency }
            .toList()
    }

    private suspend fun getUpbitTickerData(list: List<String>): List<Ticker> {
        return (repoMap[Exchange.UPBIT.exchangeName]?.getTicker(list.joinToString()) as List<UpbitTickerResponse>)
            .map { it.toTicker()}
    }

    private suspend fun getBithumbTickerList(): List<Ticker>{
        val gson = Gson()
        return (repoMap[Exchange.BITHUMB.exchangeName]?.getAllTicker() as BithumbAllResponse)
            .item
            .filter { it.key != "date" }
            .map {(name, response) -> gson.fromJson(response.toString(), BithumbTickerResponse::class.java).toTicker(name)}
    }

    private suspend fun getCoinoneTickerList(): List<Ticker>{
        val gson = Gson()
        return (repoMap[Exchange.COINONE.exchangeName]?.getAllTicker() as Map<String, Any>)
            .filter { it.key != "errorCode" && it.key != "timestamp" && it.key != "result" }
            .map { gson.fromJson(it.value.toString(), CoinoneResponse::class.java).toTicker() }
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}