package com.example.sunday.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sunday.R
import com.example.sunday.base.BaseViewHolder
import com.example.sunday.databinding.ItemExchangeBinding
import com.example.sunday.ui.model.ETicker

class ExchangeAdapter : RecyclerView.Adapter<ExchangeAdapter.ExchangeViewHolder>() {

    private val data = mutableListOf<ETicker>()

    fun setData(newData: List<ETicker>?){
        if(newData != null){
            data.clear()
            data.addAll(newData)
            notifyDataSetChanged()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExchangeViewHolder = ExchangeViewHolder(parent)

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ExchangeViewHolder, position: Int) {
        holder.binnTo(data[position])
    }


    class ExchangeViewHolder(parent: ViewGroup): BaseViewHolder<ItemExchangeBinding>(R.layout.item_exchange, parent) {
        fun binnTo(exchangeTicker: ETicker){
            binding.item = exchangeTicker
        }
    }

}