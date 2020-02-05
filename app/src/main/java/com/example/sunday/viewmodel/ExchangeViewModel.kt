package com.example.sunday.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sunday.data.enums.BaseCurrency
import com.example.sunday.data.enums.Exchange
import com.example.sunday.data.model.ExchangeTicker
import com.example.sunday.data.repository.ticker.TickerRepository
import com.example.sunday.network.response.bithumb.BithumbAllResponse
import com.example.sunday.network.response.bithumb.BithumbTickerResponse
import com.example.sunday.network.response.coinone.CoinoneResponse
import com.example.sunday.network.response.upbit.UpbitTickerResponse
import com.example.sunday.ui.model.ETicker
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.*


class ExchangeViewModel(private val repoMap: Map<String, TickerRepository>) : ViewModel() {

    private val handler = CoroutineExceptionHandler { _, exception -> Log.e("Coroutines ExchangeViewModel ::","Caught $exception") }

    private val compositeDisposable = CompositeDisposable()

    private val _tickerList = MutableLiveData<List<ETicker>>()
    val tickerList: LiveData<List<ETicker>> get() = _tickerList

    val liveCurrency = MutableLiveData<String>()

    fun getExchangeTicker(): Job{
        return viewModelScope.launch(handler) {
            try{

                val t1 = async { getUpbitExchangeTickerList() }
                val t2 = async { getBithumbExchangeTickerList() }
                val t3 = async { getCoinoneExchangeTickerList() }
                _tickerList.value = computeResult(t1.await(),t2.await(), t3.await())

            }catch (error: Exception){
                Log.e("error::", error.toString())
            }

        }

    }
    private suspend fun computeResult(t1: List<ExchangeTicker>, t2: ExchangeTicker, t3: ExchangeTicker): MutableList<ETicker> {
        val list: MutableList<ETicker> = mutableListOf()
        withContext(Dispatchers.IO){
            list.add(ETicker(0,t1[0].exchangeName, t1[0].last, t1[0].volume))
            list.add(ETicker(0,t2.exchangeName, t2.last, t2.volume))
            list.add(ETicker(0,t3.exchangeName, t3.last, t3.volume))
            val sortedWith = list.sortedWith(Comparator<ETicker> { a: ETicker, b: ETicker ->
                when {
                    a.nowPrice!! > b.nowPrice!! -> 1
                    a.nowPrice!! < b.nowPrice!! -> -1
                    else -> 0
                }
            })
            sortedWith.forEachIndexed { index, eTicker -> eTicker.idx = index + 1 }
        }
        return list
    }

    private suspend fun getUpbitExchangeTickerList(): List<ExchangeTicker>{
        return (repoMap[Exchange.UPBIT.exchangeName]?.getExchangeTicker("${BaseCurrency.KRW}-${liveCurrency.value}") as List<UpbitTickerResponse>)
            .map{
                it.toExchangeTicker(Exchange.UPBIT.exchangeName)
            }
    }

    private suspend fun getBithumbExchangeTickerList(): ExchangeTicker{
        return (repoMap[Exchange.BITHUMB.exchangeName]?.getExchangeTicker(liveCurrency.value!!) as BithumbAllResponse)
            .let{
                val gson = Gson()
                gson.fromJson(it.item.toString(), BithumbTickerResponse::class.java).toExchangeTicker(Exchange.BITHUMB.exchangeName)
            }
    }

    private suspend fun getCoinoneExchangeTickerList(): ExchangeTicker {
        return (repoMap[Exchange.COINONE.exchangeName]?.getExchangeTicker(liveCurrency.value!!) as CoinoneResponse).toExchangeTicker(Exchange.COINONE.exchangeName)
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}