package com.example.sunday.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.sunday.R
import com.example.sunday.base.BaseActivity
import com.example.sunday.data.enums.BaseCurrency
import com.example.sunday.databinding.ActivityMainBinding
import com.example.sunday.ui.listener.ItemClickListener
import com.example.sunday.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel by viewModel<MainViewModel>()
    private val coinAdapter by lazy { CoinAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
        initRecyclerView()
        viewModel.getTicker(BaseCurrency.KRW.toString())
//        viewModel.getTicker(BaseCurrency.KRW.toString())
    }

    fun initViewModel(){
        binding.vm = viewModel
    }

    fun initRecyclerView(){
        with(binding.recyclerView){
            coinAdapter.setItemClickListner( object : ItemClickListener {
                override fun onClick(view: View, currency: String) {
                    val intent = Intent(this@MainActivity, ExchangeActivity::class.java).run {
                        putExtra("currency", currency)
                    }
                    startActivity(intent)
                }
            })
            adapter = coinAdapter

        }
    }

}