package com.example.sunday.di

import com.example.sunday.network.api.BithumbApi
import com.example.sunday.network.api.CoinoneApi
import com.example.sunday.network.api.UpbitApi
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val UPBIT_URL = "https://api.upbit.com"
private const val BITHUMB_URL = "https://api.bithumb.com"
private const val COINONE_URL = "https://api.coinone.co.kr"

val networkModuelUpbit = module {
//    single { (get() as Retrofit).create(UpbitApi::class.java) }

    single {
        Retrofit.Builder()
            .addConverterFactory(get())
            .addCallAdapterFactory(get())
            .baseUrl(UPBIT_URL)
            .build()
            .create(UpbitApi::class.java)
    }

    single { GsonConverterFactory.create() as Converter.Factory }
    single { RxJava2CallAdapterFactory.create() as CallAdapter.Factory }
}

val networkModuelBithumb = module {
//    single { (get() as Retrofit).create(BithumbApi::class.java) }

    single {
        Retrofit.Builder()
            .addConverterFactory(get())
            .addCallAdapterFactory(get())
            .baseUrl(BITHUMB_URL)
            .build()
            .create(BithumbApi::class.java)
    }

}

val networkModuleCoinone = module {

    single{
        Retrofit.Builder()
            .addConverterFactory(get())
            .addCallAdapterFactory(get())
            .baseUrl(COINONE_URL)
            .build()
            .create(CoinoneApi::class.java)
    }
}
