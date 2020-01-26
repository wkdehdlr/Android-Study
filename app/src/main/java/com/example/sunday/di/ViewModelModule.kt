package com.example.sunday.di

import com.example.sunday.data.repository.ticker.BithumbRepository
import com.example.sunday.data.repository.ticker.CoinoneRepository
import com.example.sunday.data.repository.ticker.TickerRepository
import com.example.sunday.data.repository.ticker.UpbitRepository
import com.example.sunday.data.soruce.remote.RemoteBitumbDataSoruceImpl
import com.example.sunday.data.soruce.remote.RemoteCoinoneDataSourceImpl
import com.example.sunday.data.soruce.remote.RemoteUpbitDataSourceImpl
import com.example.sunday.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(
        mapOf("Upbit" to UpbitRepository(RemoteUpbitDataSourceImpl(get())) as TickerRepository,
            "Bithumb" to BithumbRepository(RemoteBitumbDataSoruceImpl(get())) as TickerRepository,
            "Coinone" to CoinoneRepository(RemoteCoinoneDataSourceImpl(get())) as TickerRepository))
    }
}

