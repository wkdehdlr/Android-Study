package com.example.sunday.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sunday.data.enums.BaseCurrency
import com.example.sunday.data.enums.Exchange
import com.example.sunday.data.model.ExchangeTicker
import com.example.sunday.data.repository.ticker.TickerRepository
import com.example.sunday.network.response.bithumb.BithumbAllResponse
import com.example.sunday.network.response.bithumb.BithumbTickerResponse
import com.example.sunday.network.response.coinone.CoinoneResponse
import com.example.sunday.network.response.upbit.UpbitTickerResponse
import com.example.sunday.ui.model.ETicker
import com.example.sunday.util.plusAssign
import com.example.sunday.util.withSchedulers
import com.google.gson.Gson
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function3

class ExchangeViewModel(private val repoMap: Map<String, TickerRepository>) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _tickerList = MutableLiveData<List<ETicker>>()
    val tickerList: LiveData<List<ETicker>> get() = _tickerList

    val liveCurrency = MutableLiveData<String>()

    fun getExchangeTicker(){
        compositeDisposable += Single.zip(
            (repoMap[Exchange.UPBIT.exchangeName]?.getExchangeTicker("${BaseCurrency.KRW}-${liveCurrency.value}") as Single<List<UpbitTickerResponse>>)
                .withSchedulers()
                .map {
                    it.map {
                        it.toExchangeTicker(Exchange.UPBIT.exchangeName)
                    }
                },
            (repoMap[Exchange.BITHUMB.exchangeName]?.getExchangeTicker(liveCurrency.value!!) as Single<BithumbAllResponse>)
                .withSchedulers()
                .map {
                    val gson = Gson()
                    gson.fromJson(it.item.toString(), BithumbTickerResponse::class.java).toExchangeTicker(Exchange.BITHUMB.exchangeName)
                },
            (repoMap[Exchange.COINONE.exchangeName]?.getExchangeTicker(liveCurrency.value!!) as Single<CoinoneResponse> )
                .withSchedulers()
                .map {
                    it.toExchangeTicker(Exchange.COINONE.exchangeName)
                },
            Function3<List<ExchangeTicker>, ExchangeTicker, ExchangeTicker, List<ETicker>>{ t1, t2, t3 ->
                val list: MutableList<ETicker> = mutableListOf()
                list.add(ETicker(0,t1[0].exchangeName, t1[0].last, t1[0].volume))
                list.add(ETicker(0,t2.exchangeName, t2.last, t2.volume))
                list.add(ETicker(0,t3.exchangeName, t3.last, t3.volume))
                val sortedWith = list.sortedWith(Comparator<ETicker> { a: ETicker, b: ETicker ->
                    when {
                        a.nowPrice!! < b.nowPrice!! -> 1
                        a.nowPrice!! > b.nowPrice!! -> -1
                        else -> 0
                    }
                })
                sortedWith.forEachIndexed { index, eTicker -> eTicker.idx = index + 1 }
                sortedWith
            }
        )
            .subscribe({
                _tickerList.value = it
            },{
                Log.e("exchange Api Error :: ",it.message!!)

            })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}