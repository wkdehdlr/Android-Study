package com.example.sunday.data.repository

import com.example.sunday.data.response.bithumb.BithumbAllResponse
import com.example.sunday.data.response.upbit.UpbitMarketResponse
import com.example.sunday.data.response.upbit.UpbitTickerResponse
import io.reactivex.Single

interface CoinRepository {
    fun getMarketList() : Single<List<UpbitMarketResponse>>
    fun getTickerList(markets: String): Single<List<UpbitTickerResponse>>
    fun getBithumbTickerList() : Single<BithumbAllResponse>
}