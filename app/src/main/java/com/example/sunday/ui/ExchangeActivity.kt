package com.example.sunday.ui

import android.os.Bundle
import com.example.sunday.R
import com.example.sunday.base.BaseActivity
import com.example.sunday.databinding.ActivityExchangeBinding
import com.example.sunday.viewmodel.ExchangeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ExchangeActivity : BaseActivity<ActivityExchangeBinding>(R.layout.activity_exchange) {

    private val viewModel by viewModel<ExchangeViewModel>()
    private val exchangeAdapter by lazy { ExchangeAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
        initRecyclerView()
        viewModel.getExchangeTicker()
    }

    fun initViewModel(){
        viewModel.liveCurrency.value = intent.getStringExtra("currency")
        binding.vm = viewModel
    }

    fun initRecyclerView(){
        with(binding.recyclerViewExchange){
            adapter  = exchangeAdapter
        }
    }
}