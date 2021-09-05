package com.ilih.weatherus.data.source.network

import com.google.gson.annotations.SerializedName
import com.ilih.weatherus.data.source.db.entity.HourlyForecastEntity

data class HourlyForecastData(
    @SerializedName("temperature")
    val temperature: Float,
    @SerializedName("weather_icon")
    val weatherIcon: String,
    @SerializedName("weather_code")
    val weatherCode: Int,
    @SerializedName("weather_desc")
    val weatherDesc: String,
    @SerializedName("timestamp")
    val localTimestamp: String
)

fun HourlyForecastData.toEntity(parentId: Long) =
    HourlyForecastEntity(
        id = null,
        temperature = temperature,
        weatherIcon = weatherIcon,
        weatherCode = weatherCode,
        weatherDesc = weatherDesc,
        localTimestamp = localTimestamp,
        homeParent = parentId
    )
