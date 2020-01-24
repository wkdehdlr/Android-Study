package com.example.sunday.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sunday.R
import com.example.sunday.base.BaseViewHolder
import com.example.sunday.databinding.ItemCoinBinding
import com.example.sunday.network.response.upbit.UpbitTickerResponse

class CoinAdapter : RecyclerView.Adapter<CoinAdapter.CoinViewHolder>() {

    private val data = mutableListOf<UpbitTickerResponse>()

    fun setData(newData: List<UpbitTickerResponse>?){
        if(newData != null){
            data.clear()
            data.addAll(newData)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder = CoinViewHolder(parent)

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) = holder.bindTo(data[position])


    class CoinViewHolder(parent: ViewGroup): BaseViewHolder<ItemCoinBinding>(R.layout.item_coin, parent){
        fun bindTo(upbitTickerResponse: UpbitTickerResponse){
            binding.item = upbitTickerResponse
        }
    }

}