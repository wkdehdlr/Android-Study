package com.example.sunday.network.api

import com.example.sunday.network.response.bithumb.BithumbAllResponse
import io.reactivex.Single
import retrofit2.http.GET

interface BithumbApi {

    @GET("/public/ticker/all")
    fun getTickerList(): Single<BithumbAllResponse>
}