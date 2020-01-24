package com.example.sunday.data.repository

import com.example.sunday.data.soruce.remote.RemoteDataSoruce
import com.example.sunday.network.response.bithumb.BithumbAllResponse
import com.example.sunday.network.response.coinone.CoinoneResponse
import com.example.sunday.network.response.upbit.UpbitMarketResponse
import com.example.sunday.network.response.upbit.UpbitTickerResponse
import io.reactivex.Single

class CoinRepositoryImpl (private val remoteDataSoruce: RemoteDataSoruce) : CoinRepository {
    override fun getMarketList(): Single<List<UpbitMarketResponse>> = remoteDataSoruce.getMarketList()
    override fun getTickerList(markets: String): Single<List<UpbitTickerResponse>> = remoteDataSoruce.getTickerList(markets)
    override fun getBithumbTickerList(): Single<BithumbAllResponse> = remoteDataSoruce.getBitbumbTickerList()
    override fun getCoinoneTickerList(): Single<Map<String, Any>> = remoteDataSoruce.getCoinoneTickerList()
    override fun getCoinoneTicker(currency: String): Single<CoinoneResponse> = remoteDataSoruce.getCoinoneTicker(currency)
}