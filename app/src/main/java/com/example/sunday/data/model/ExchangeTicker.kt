package com.example.sunday.data.model

class ExchangeTicker (
    private val exchangeName: String,
    ticker: Ticker)
    : Ticker(currency = ticker.currency,
    baseCurrency = ticker.baseCurrency,
    last = ticker.last,
    high = ticker.high,
    low = ticker.low,
    volume = ticker.volume,
    diff = ticker.diff)
