package com.example.sunday.data.soruce.remote

import com.example.sunday.data.response.bithumb.BithumbAllResponse
import com.example.sunday.data.response.upbit.UpbitMarketResponse
import com.example.sunday.data.response.upbit.UpbitTickerResponse
import com.example.sunday.network.api.BithumbApi
import com.example.sunday.network.api.UpbitApi
import io.reactivex.Single

class RemoteDataSoruceImpl(private val upbitApi: UpbitApi, private val bithumbApi: BithumbApi) : RemoteDataSoruce {

    override fun getMarketList(): Single<List<UpbitMarketResponse>>  = upbitApi.getMarket()
    override fun getTickerList(markets: String): Single<List<UpbitTickerResponse>> = upbitApi.getTicker(markets)
    override fun getBitbumbTickerList(): Single<BithumbAllResponse> = bithumbApi.getTickerList()

}