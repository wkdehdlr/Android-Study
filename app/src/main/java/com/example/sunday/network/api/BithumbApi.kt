package com.example.sunday.network.api

import com.example.sunday.network.response.bithumb.BithumbAllResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface BithumbApi {

    @GET("/public/ticker/all")
    suspend fun getTickerList(): BithumbAllResponse


    @GET("/public/ticker/{exchangeName}")
    suspend fun getExchangeTicker(@Path("exchangeName") exchangeName: String) : BithumbAllResponse
}