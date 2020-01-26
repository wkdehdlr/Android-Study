package com.example.sunday.data.soruce.remote

import com.example.sunday.network.api.BithumbApi

class RemoteBitumbDataSoruceImpl(private val networkApi: BithumbApi) : RemoteTickerDataSource {
    override fun getAllTicker(): Any = networkApi.getTickerList()
    override fun getTicker(currency: String): Any = this
    override fun getExchangeTicker(currency: String):Any = networkApi.getExchangeTicker(currency)
}