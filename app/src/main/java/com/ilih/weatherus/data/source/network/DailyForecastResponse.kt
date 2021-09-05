package com.ilih.weatherus.data.source.network

import com.google.gson.annotations.SerializedName
import com.ilih.weatherus.domain.entity.DailyForecastDto

data class DailyForecastResponse(
    @SerializedName("data")
    val data: ArrayList<DailyForecastData>,
    @SerializedName("country_code")
    val countryCode: String,
    @SerializedName("city_name")
    val cityName: String
)