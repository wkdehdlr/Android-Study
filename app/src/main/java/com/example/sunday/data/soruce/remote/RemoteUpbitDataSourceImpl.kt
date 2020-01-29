package com.example.sunday.data.soruce.remote

import com.example.sunday.network.api.UpbitApi

class RemoteUpbitDataSourceImpl(private val networkApi: UpbitApi) : RemoteTickerDataSource {
    override suspend fun getAllTicker(): Any = networkApi.getMarket()
    override suspend fun getTicker(currency: String): Any  = networkApi.getTicker(currency)
    override suspend fun getExchangeTicker(currency: String): Any = networkApi.getExchangeTicker(currency)
}