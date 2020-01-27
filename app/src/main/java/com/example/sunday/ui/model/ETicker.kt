package com.example.sunday.ui.model

data class ETicker(
    var idx: Int? = 0,
    val exchangeName: String?,
    val nowPrice: Double?,
    val volume: Double?
)