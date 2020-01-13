package com.example.sunday.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sunday.data.repository.upbit.UpbitRepository
import com.example.sunday.data.response.upbit.UpbitTickerResponse
import com.example.sunday.util.plusAssign
import com.example.sunday.util.withSchedulers
import io.reactivex.disposables.CompositeDisposable

class MainViewModel(private val repository: UpbitRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _tickerList = MutableLiveData<List<UpbitTickerResponse>>()
    val tickerList: LiveData<List<UpbitTickerResponse>> get() = _tickerList


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