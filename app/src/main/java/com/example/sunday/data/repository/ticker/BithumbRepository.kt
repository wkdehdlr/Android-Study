package com.example.sunday.data.repository.ticker

import com.example.sunday.data.soruce.remote.RemoteTickerDataSource

class BithumbRepository(private val remoteTickerDataSource: RemoteTickerDataSource) : TickerRepository {
    override suspend fun getAllTicker():Any = remoteTickerDataSource.getAllTicker()
    override suspend fun getTicker(currency: String) = remoteTickerDataSource.getTicker(currency)
    override suspend fun getExchangeTicker(currency: String) = remoteTickerDataSource.getExchangeTicker(currency)
}