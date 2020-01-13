package com.example.sunday.di

import com.example.sunday.data.repository.upbit.UpbitRepository
import com.example.sunday.data.repository.upbit.UpbitRepositoryImpl
import com.example.sunday.data.soruce.remote.UpbitRemoteDataSoruce
import com.example.sunday.data.soruce.remote.UpbitRemoteDataSoruceImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<UpbitRepository> { UpbitRepositoryImpl(get()) }
    single<UpbitRemoteDataSoruce> { UpbitRemoteDataSoruceImpl(get()) }
}