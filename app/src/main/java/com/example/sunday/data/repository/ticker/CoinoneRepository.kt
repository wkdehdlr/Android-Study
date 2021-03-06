package com.example.sunday.data.repository.ticker

import com.example.sunday.data.soruce.remote.RemoteTickerDataSource

class CoinoneRepository(private val remoteTickerDataSource: RemoteTickerDataSource) : TickerRepository {
    override fun getAllTicker() = remoteTickerDataSource.getAllTicker()
    override fun getTicker(currency: String) = remoteTickerDataSource.getTicker(currency)
    override fun getExchangeTicker(currency: String) = remoteTickerDataSource.getExchangeTicker(currency)
}