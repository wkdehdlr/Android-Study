package com.example.sunday.network.api

import com.example.sunday.network.response.upbit.UpbitMarketResponse
import com.example.sunday.network.response.upbit.UpbitTickerResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UpbitApi {

    @GET("/v1/market/all")
    suspend fun getMarket(): List<UpbitMarketResponse>

    @GET("/v1/ticker?")
    suspend fun getTicker(@Query("markets") markets: String) : List<UpbitTickerResponse>

    @GET("/v1/ticker?")
    suspend fun getExchangeTicker(@Query("markets") markets: String) : List<UpbitTickerResponse>

}