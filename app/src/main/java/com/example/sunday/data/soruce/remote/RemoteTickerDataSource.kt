package com.example.sunday.data.soruce.remote

interface RemoteTickerDataSource {
    fun getAllTicker():Any
    fun getTicker(currency: String):Any
    fun getExchangeTicker(currency: String):Any
}