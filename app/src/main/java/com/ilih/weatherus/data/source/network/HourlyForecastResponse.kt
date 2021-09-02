package com.ilih.weatherus.data.source.network

import com.google.gson.annotations.SerializedName
import com.ilih.weatherus.domain.entity.DailyForecastDto

data class HourlyForecastResponse(
    @SerializedName("data")
    val data: ArrayList<HourlyForecastData>,
    @SerializedName("country_code")
    val countryCode: String,
    @SerializedName("city_name")
    val cityName: String
)
