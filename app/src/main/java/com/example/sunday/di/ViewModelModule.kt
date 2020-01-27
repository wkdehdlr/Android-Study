package com.example.sunday.di

import com.example.sunday.data.enums.Exchange
import com.example.sunday.data.repository.ticker.BithumbRepository
import com.example.sunday.data.repository.ticker.CoinoneRepository
import com.example.sunday.data.repository.ticker.TickerRepository
import com.example.sunday.data.repository.ticker.UpbitRepository
import com.example.sunday.data.soruce.remote.RemoteBitumbDataSoruceImpl
import com.example.sunday.data.soruce.remote.RemoteCoinoneDataSourceImpl
import com.example.sunday.data.soruce.remote.RemoteUpbitDataSourceImpl
import com.example.sunday.viewmodel.ExchangeViewModel
import com.example.sunday.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(
        mapOf(Exchange.UPBIT.exchangeName to UpbitRepository(RemoteUpbitDataSourceImpl(get())) as TickerRepository,
            Exchange.BITHUMB.exchangeName to BithumbRepository(RemoteBitumbDataSoruceImpl(get())) as TickerRepository,
            Exchange.COINONE.exchangeName to CoinoneRepository(RemoteCoinoneDataSourceImpl(get())) as TickerRepository)) }

    viewModel { ExchangeViewModel(
        mapOf(Exchange.UPBIT.exchangeName to UpbitRepository(RemoteUpbitDataSourceImpl(get())) as TickerRepository,
            Exchange.BITHUMB.exchangeName to BithumbRepository(RemoteBitumbDataSoruceImpl(get())) as TickerRepository,
            Exchange.COINONE.exchangeName to CoinoneRepository(RemoteCoinoneDataSourceImpl(get())) as TickerRepository)) }
}

