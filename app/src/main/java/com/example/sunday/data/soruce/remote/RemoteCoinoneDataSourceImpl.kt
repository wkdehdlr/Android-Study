package com.example.sunday.data.soruce.remote

import com.example.sunday.network.api.CoinoneApi

class RemoteCoinoneDataSourceImpl(private val networkApi: CoinoneApi) : RemoteTickerDataSource {
    override fun getAllTicker(): Any = networkApi.getAllTicker()
    override fun getTicker(currency: String): Any = networkApi.getTicker(currency)
    override fun getExchangeTicker(currency: String): Any = networkApi.getExchangeTicker(currency)
}