package com.example.sunday.data.repository

import com.example.sunday.data.response.bithumb.BithumbAllResponse
import com.example.sunday.data.response.upbit.UpbitMarketResponse
import com.example.sunday.data.response.upbit.UpbitTickerResponse
import com.example.sunday.data.soruce.remote.RemoteDataSoruce
import io.reactivex.Single

class CoinRepositoryImpl (private val remoteDataSoruce: RemoteDataSoruce) : CoinRepository {
    override fun getMarketList(): Single<List<UpbitMarketResponse>> = remoteDataSoruce.getMarketList()
    override fun getTickerList(markets: String): Single<List<UpbitTickerResponse>> = remoteDataSoruce.getTickerList(markets)
    override fun getBithumbTickerList(): Single<BithumbAllResponse> = remoteDataSoruce.getBitbumbTickerList()
}