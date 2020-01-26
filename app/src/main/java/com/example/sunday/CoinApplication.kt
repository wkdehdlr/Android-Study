package com.example.sunday


import android.app.Application
import com.example.sunday.di.networkModuelUpbit
import com.example.sunday.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class CoinApplication : Application() {
    private val moduleList = listOf(viewModelModule, networkModuelUpbit)

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CoinApplication)
            modules(moduleList)
        }
    }
}