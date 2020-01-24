package com.example.sunday.network.response.coinone


import com.example.sunday.data.model.Ticker
import com.example.sunday.data.model.TickerProvider
import com.google.gson.annotations.SerializedName

data class CoinoneResponse(
    @SerializedName("currency")
    val currency: String,
    @SerializedName("errorCode")
    val errorCode: String,
    @SerializedName("first")
    val first: Double,
    @SerializedName("high")
    val high: Double,
    @SerializedName("last")
    val last: Double,
    @SerializedName("low")
    val low: Double,
    @SerializedName("result")
    val result: String,
    @SerializedName("timestamp")
    val timestamp: String,
    @SerializedName("volume")
    val volume: Double,
    @SerializedName("yesterday_first")
    val yesterdayFirst: String,
    @SerializedName("yesterday_high")
    val yesterdayHigh: String,
    @SerializedName("yesterday_last")
    val yesterdayLast: String,
    @SerializedName("yesterday_low")
    val yesterdayLow: String,
    @SerializedName("yesterday_volume")
    val yesterdayVolume: String
) : TickerProvider{
    override fun toTicker(): Ticker {
        val diff = (last - first) / first * 100
        return Ticker(currency,
            baseCurrency = "KRW",
            last = last,
            high = high,
            low = low,
            diff = diff,
            volume = volume * last)

    }
}