package com.example.sunday.data.soruce.remote

import com.example.sunday.network.api.BithumbApi
import com.example.sunday.network.api.CoinoneApi
import com.example.sunday.network.api.UpbitApi
import com.example.sunday.network.response.bithumb.BithumbAllResponse
import com.example.sunday.network.response.coinone.CoinoneResponse
import com.example.sunday.network.response.upbit.UpbitMarketResponse
import com.example.sunday.network.response.upbit.UpbitTickerResponse
import io.reactivex.Single

class RemoteDataSoruceImpl(private val upbitApi: UpbitApi, private val bithumbApi: BithumbApi, private val coinoneApi: CoinoneApi) : RemoteDataSoruce {

    override fun getMarketList(): Single<List<UpbitMarketResponse>>  = upbitApi.getMarket()
    override fun getTickerList(markets: String): Single<List<UpbitTickerResponse>> = upbitApi.getTicker(markets)
    override fun getBitbumbTickerList(): Single<BithumbAllResponse> = bithumbApi.getTickerList()
    override fun getCoinoneTickerList(): Single<Map<String, Any>> = coinoneApi.getAllTicker()
    override fun getCoinoneTicker(currency: String): Single<CoinoneResponse> = coinoneApi.getTicker(currency)
}