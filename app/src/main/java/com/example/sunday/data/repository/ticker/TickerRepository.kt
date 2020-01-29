package com.example.sunday.data.repository.ticker

interface TickerRepository {
    suspend fun getAllTicker():Any
    suspend fun getTicker(currency: String):Any
    suspend fun getExchangeTicker(currency: String):Any
}