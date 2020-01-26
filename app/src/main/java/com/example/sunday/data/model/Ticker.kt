package com.example.sunday.data.model

open class Ticker(
    var currency: String? = "",
    var baseCurrency: String? = "",
    var last: Double?,
    var high: Double?,
    var low: Double?,
    var volume: Double?,
    var diff: Double? = null) : TickerProvider{

    override fun toTicker(): Ticker = this
}