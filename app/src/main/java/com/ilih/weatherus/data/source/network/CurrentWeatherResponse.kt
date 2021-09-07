package com.ilih.weatherus.data.source.network

import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(
    @SerializedName("data")
    val data: List<CurrentWeatherData>,
    @SerializedName("count")
    val count: Int
)
