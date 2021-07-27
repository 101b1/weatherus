package com.ilih.weatherus.data.source.network

import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("icon")
    val iconID: String,
    @SerializedName("code")
    val iconCode: String,
    @SerializedName("description")
    val description: String
)
