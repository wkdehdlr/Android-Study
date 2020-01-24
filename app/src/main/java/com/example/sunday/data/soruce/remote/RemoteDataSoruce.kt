package com.example.sunday.data.soruce.remote

import com.example.sunday.network.response.bithumb.BithumbAllResponse
import com.example.sunday.network.response.coinone.CoinoneResponse
import com.example.sunday.network.response.upbit.UpbitMarketResponse
import com.example.sunday.network.response.upbit.UpbitTickerResponse
import io.reactivex.Single

interface RemoteDataSoruce {

    fun getMarketList(): Single<List<UpbitMarketResponse>>
    fun getTickerList(markets: String): Single<List<UpbitTickerResponse>>
    fun getBitbumbTickerList() : Single<BithumbAllResponse>
    fun getCoinoneTickerList() : Single<Map<String, Any>>
    fun getCoinoneTicker(currency: String) : Single<CoinoneResponse>
}