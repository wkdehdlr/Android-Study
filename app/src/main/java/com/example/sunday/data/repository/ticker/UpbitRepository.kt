package com.example.sunday.data.repository.ticker

import com.example.sunday.data.soruce.remote.RemoteTickerDataSource

class UpbitRepository(private val remoteTickerDataSource: RemoteTickerDataSource) : TickerRepository {
    override fun getAllTicker(): Any = remoteTickerDataSource.getAllTicker()
    override fun getTicker(currency: String): Any = remoteTickerDataSource.getTicker(currency)
    override fun getExchangeTicker(currency: String): Any = remoteTickerDataSource.getExchangeTicker(currency)
}