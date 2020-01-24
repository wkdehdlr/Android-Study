package com.example.sunday.network.api

import com.example.sunday.network.response.coinone.CoinoneResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinoneApi {

    @GET("/ticker/?currency=all")
    fun getAllTicker(): Single<Map<String, Any>>

    @GET("/ticker/?")
    fun getTicker(@Query("currency") currency: String): Single<CoinoneResponse>
}