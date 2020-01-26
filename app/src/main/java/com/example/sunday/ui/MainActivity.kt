package com.example.sunday.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.sunday.R
import com.example.sunday.base.BaseActivity
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
        viewModel.abc("KRW")
        initRecyclerView()
    }

    fun initViewModel(){
        binding.vm = viewModel
    }

    fun initRecyclerView(){
        with(binding.recyclerView){
            coinAdapter.setItemClickListner( object : ItemClickListener {
                override fun onClick(view: View, position: Int) {
                    Toast.makeText(this@MainActivity, "${position}번 리스트 선택", Toast.LENGTH_LONG).show()
                }
            })
            adapter = coinAdapter

        }
    }

}