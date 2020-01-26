package com.example.sunday.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sunday.R
import com.example.sunday.base.BaseViewHolder
import com.example.sunday.databinding.ItemCoinBinding
import com.example.sunday.ui.listener.ItemClickListener
import com.example.sunday.ui.model.UTicker

class CoinAdapter : RecyclerView.Adapter<CoinAdapter.CoinViewHolder>() {

    private val data = mutableListOf<UTicker>()
    private lateinit var itemClickListener: ItemClickListener

    fun setData(newData: List<UTicker>?){
        if(newData != null){
            data.clear()
            data.addAll(newData)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder = CoinViewHolder(parent)

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        holder.bindTo(data[position])
        holder.itemView.setOnClickListener { itemClickListener.onClick(it, position) }
    }

    fun setItemClickListner(itemClickListener: ItemClickListener){
        this.itemClickListener = itemClickListener
    }


    class CoinViewHolder(parent: ViewGroup): BaseViewHolder<ItemCoinBinding>(R.layout.item_coin, parent){
        fun bindTo(uTicker: UTicker){
            binding.item = uTicker
        }
    }

}