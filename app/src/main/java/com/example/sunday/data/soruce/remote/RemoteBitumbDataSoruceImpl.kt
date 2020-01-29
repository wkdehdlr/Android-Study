package com.example.sunday.data.soruce.remote

import com.example.sunday.network.api.BithumbApi

class RemoteBitumbDataSoruceImpl(private val networkApi: BithumbApi) : RemoteTickerDataSource {
    override suspend fun getAllTicker(): Any = networkApi.getTickerList()
    override suspend fun getTicker(currency: String): Any = this
    override suspend fun getExchangeTicker(currency: String):Any = networkApi.getExchangeTicker(currency)
}