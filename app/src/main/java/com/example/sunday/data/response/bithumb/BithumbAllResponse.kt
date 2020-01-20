package com.example.sunday.data.response.bithumb

import com.google.gson.annotations.SerializedName

data class BithumbAllResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("data")
    val item: HashMap<String, Any>
)