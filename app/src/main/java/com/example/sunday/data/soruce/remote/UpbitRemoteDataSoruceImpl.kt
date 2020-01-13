package com.example.sunday.data.soruce.remote

import com.example.sunday.data.response.upbit.UpbitMarketResponse
import com.example.sunday.data.response.upbit.UpbitTickerResponse
import com.example.sunday.network.api.UpbitApi
import io.reactivex.Single

class UpbitRemoteDataSoruceImpl(private val upbitApi: UpbitApi) : UpbitRemoteDataSoruce {

    override fun getMarketList(): Single<List<UpbitMarketResponse>>  = upbitApi.getMarket()
    override fun getTickerList(markets: String): Single<List<UpbitTickerResponse>> = upbitApi.getTicker(markets)
}