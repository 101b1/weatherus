package com.ilih.weatherus.data.source.network

import com.google.gson.annotations.SerializedName
import com.ilih.weatherus.data.source.db.entity.DailyForecastEntity
import com.ilih.weatherus.domain.entity.DailyForecastDto

data class DailyForecastData(
    @SerializedName("max_temp")
    val maxTemp: Float,
    @SerializedName("min_temp")
    val minTemp: Float,
    @SerializedName("weather_icon")
    val weatherIcon: String,
    @SerializedName("weather_code")
    val weatherCode: Int,
    @SerializedName("weather_desc")
    val weatherDesc: String,
    @SerializedName("timestamp")
    val timestamp: Long
)

fun DailyForecastData.toDto() =
    let {
        DailyForecastDto(
            maxTemp = maxTemp,
            minTemp = minTemp,
            weatherIcon = weatherIcon,
            weatherCode = weatherCode,
            weatherDesc = weatherDesc,
            timestamp = timestamp
        )
    }

fun DailyForecastData.toEntity(parentId: Long) =
    let {
        DailyForecastEntity(
            null,
            maxTemp = maxTemp,
            minTemp = minTemp,
            weatherIcon = weatherIcon,
            weatherCode = weatherCode,
            weatherDesc = weatherDesc,
            timestamp = timestamp,
            homeParent = parentId
        )
    }
