package com.example.sunday.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sunday.ui.CoinAdapter
import com.example.sunday.ui.ExchangeAdapter
import com.example.sunday.ui.model.ETicker
import com.example.sunday.ui.model.UTicker


@BindingAdapter("setData")
fun RecyclerView.setData(list: List<UTicker>?) {
    (adapter as? CoinAdapter)?.setData(list)
}

@BindingAdapter("setETicker")
fun RecyclerView.setETicker(list: List<ETicker>?) {
    (adapter as? ExchangeAdapter)?.setData(list)
}