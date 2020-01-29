package com.example.sunday.data.soruce.remote

interface RemoteTickerDataSource {
    suspend fun getAllTicker():Any
    suspend fun getTicker(currency: String):Any
    suspend fun getExchangeTicker(currency: String):Any
}