package com.example.sunday.data.soruce.remote

import com.example.sunday.data.response.upbit.UpbitMarketResponse
import com.example.sunday.data.response.upbit.UpbitTickerResponse
import io.reactivex.Single

interface UpbitRemoteDataSoruce {

    fun getMarketList(): Single<List<UpbitMarketResponse>>

    fun getTickerList(markets: String): Single<List<UpbitTickerResponse>>
}