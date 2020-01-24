package com.example.sunday.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sunday.network.response.upbit.UpbitTickerResponse
import com.example.sunday.ui.CoinAdapter


@BindingAdapter("setData")
fun RecyclerView.setData(list: List<UpbitTickerResponse>?) {
    (adapter as? CoinAdapter)?.setData(list)
}