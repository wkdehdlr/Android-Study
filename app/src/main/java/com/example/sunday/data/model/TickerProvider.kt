package com.example.sunday.data.model

interface TickerProvider {
    fun toTicker(): Ticker
    fun toExchangeTicker(exchangeName: String = "empty") : ExchangeTicker
}