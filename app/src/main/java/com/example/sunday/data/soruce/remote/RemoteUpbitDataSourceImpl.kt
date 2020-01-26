package com.example.sunday.data.soruce.remote

import com.example.sunday.network.api.UpbitApi

class RemoteUpbitDataSourceImpl(private val networkApi: UpbitApi) : RemoteTickerDataSource {
    override fun getAllTicker(): Any = networkApi.getMarket()
    override fun getTicker(currency: String): Any  = networkApi.getTicker(currency)
    override fun getExchangeTicker(currency: String): Any = networkApi.getExchangeTicker(currency)
}