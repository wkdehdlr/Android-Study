package com.example.sunday.network.api

import com.example.sunday.network.response.upbit.UpbitMarketResponse
import com.example.sunday.network.response.upbit.UpbitTickerResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface UpbitApi {

    @GET("/v1/market/all")
    fun getMarket(): Single<List<UpbitMarketResponse>>

    @GET("/v1/ticker?")
    fun getTicker(@Query("markets") markets: String) : Single<List<UpbitTickerResponse>>
}