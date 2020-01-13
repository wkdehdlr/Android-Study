package com.example.sunday.data.repository.upbit

import com.example.sunday.data.response.upbit.UpbitMarketResponse
import com.example.sunday.data.response.upbit.UpbitTickerResponse
import com.example.sunday.data.soruce.remote.UpbitRemoteDataSoruce
import io.reactivex.Single

class UpbitRepositoryImpl (private val remoteDataSoruce: UpbitRemoteDataSoruce) : UpbitRepository {
    override fun getMarketList(): Single<List<UpbitMarketResponse>> = remoteDataSoruce.getMarketList()

    override fun getTickerList(markets: String): Single<List<UpbitTickerResponse>> = remoteDataSoruce.getTickerList(markets)
}