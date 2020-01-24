package com.example.sunday.ui

import android.os.Bundle
import com.example.sunday.R
import com.example.sunday.base.BaseActivity
import com.example.sunday.databinding.ActivityMainBinding
import com.example.sunday.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel by viewModel<MainViewModel>()
    private val coinAdapter by lazy { CoinAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
//        viewModel.getBithumbTickerList()
        viewModel.getTickerList("KRW")
        viewModel.abc("KRW")
        initRecyclerView()
    }

    fun initViewModel(){
        binding.vm = viewModel
    }

    fun initRecyclerView(){
        with(binding.recyclerView){
            adapter = coinAdapter
        }
    }
}