package com.example.sunday.di

import com.example.sunday.data.repository.CoinRepository
import com.example.sunday.data.repository.CoinRepositoryImpl
import com.example.sunday.data.soruce.remote.RemoteDataSoruce
import com.example.sunday.data.soruce.remote.RemoteDataSoruceImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<CoinRepository> {
        CoinRepositoryImpl(
            get()
        )
    }
    single<RemoteDataSoruce> { RemoteDataSoruceImpl(get(), get(), get()) }
}