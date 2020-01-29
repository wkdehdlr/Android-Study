package com.example.sunday.network.api

import com.example.sunday.network.response.coinone.CoinoneResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinoneApi {

    @GET("/ticker/?currency=all")
    suspend fun getAllTicker(): Map<String, Any>

    @GET("/ticker/?")
    suspend fun getTicker(@Query("currency") currency: String): CoinoneResponse

    @GET("/ticker/?")
    suspend fun getExchangeTicker(@Query("currency") currency: String): CoinoneResponse
}