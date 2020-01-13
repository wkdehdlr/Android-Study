package com.example.sunday.util

import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import com.example.sunday.R
import com.example.sunday.data.response.upbit.UpbitTickerResponse
import java.text.DecimalFormat


@BindingAdapter("floatNumber")
fun TextView.setFloatNumber(tmp: Double){
    text = DecimalFormat("0.###").format(tmp)
}

@BindingAdapter("coinName")
fun TextView.setCoinName(upbitTickerResponse: UpbitTickerResponse){
    text = upbitTickerResponse.market.split("-")[1]
}

@BindingAdapter("compareRise")
fun TextView.setCompareRise(upbitTickerResponse: UpbitTickerResponse){
    upbitTickerResponse.change.let{
        when{
            it.equals("RISE") -> {
                setTextColor(ResourcesCompat.getColor(resources, R.color.colorBlue, null))
            }
            it.equals("FALL") -> {
                setTextColor(ResourcesCompat.getColor(resources, R.color.colorRed, null))
            }
            else -> {
                setTextColor(ResourcesCompat.getColor(resources, R.color.colorGray, null))
            }
        }
    }
}