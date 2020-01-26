package com.example.sunday.data.repository.ticker

interface TickerRepository {
    fun getAllTicker():Any
    fun getTicker(currency: String):Any
    fun getExchangeTicker(currency: String):Any
}