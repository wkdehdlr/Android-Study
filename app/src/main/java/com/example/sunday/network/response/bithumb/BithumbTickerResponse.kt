package com.example.sunday.network.response.bithumb

import com.example.sunday.data.model.Ticker
import com.example.sunday.data.model.TickerProvider
import com.google.gson.annotations.SerializedName

data class BithumbTickerResponse(
    @SerializedName("acc_trade_value")
    val accTradeValue: String,
    @SerializedName("acc_trade_value_24H")
    val accTradeValue24H: Double,
    @SerializedName("closing_price")
    val closingPrice: Double,
    @SerializedName("fluctate_24H")
    val fluctate24H: Double,
    @SerializedName("fluctate_rate_24H")
    val fluctateRate24H: String,
    @SerializedName("max_price")
    val maxPrice: Double,
    @SerializedName("min_price")
    val minPrice: Double,
    @SerializedName("opening_price")
    val openingPrice: Double,
    @SerializedName("prev_closing_price")
    val prevClosingPrice: Double,
    @SerializedName("units_traded")
    val unitsTraded: String,
    @SerializedName("units_traded_24H")
    val unitsTraded24H: String
) : TickerProvider{
    override fun toTicker() =
        Ticker(baseCurrency = "KRW",
            last = closingPrice,
            high = maxPrice,
            low = minPrice,
            diff = fluctate24H,
            volume = accTradeValue24H)

    fun toTicker(name: String) =
        Ticker(currency = name,
            baseCurrency = "KRW",
            last = closingPrice,
            high = maxPrice,
            low = minPrice,
            diff = fluctate24H,
            volume = accTradeValue24H)
}