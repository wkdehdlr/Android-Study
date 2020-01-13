package com.example.sunday.data.repository.upbit

import com.example.sunday.data.response.upbit.UpbitMarketResponse
import com.example.sunday.data.response.upbit.UpbitTickerResponse
import io.reactivex.Single

interface UpbitRepository {
    fun getMarketList() : Single<List<UpbitMarketResponse>>
    fun getTickerList(markets: String): Single<List<UpbitTickerResponse>>
}