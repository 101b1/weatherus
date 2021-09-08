package com.ilih.weatherus.data.source.network

import com.google.gson.annotations.SerializedName
import com.ilih.weatherus.data.source.db.entity.DailyForecastEntity
import com.ilih.weatherus.domain.entity.DailyForecastDto

data class DailyForecastData(
    @SerializedName("max_temp")
    val maxTemp: Float,
    @SerializedName("min_temp")
    val minTemp: Float,
    @SerializedName("weather")
    val weather: WeatherDescription,
    @SerializedName("ts")
    val timestamp: Long
)

fun DailyForecastData.toDto() =
    let {
        DailyForecastDto(
            maxTemp = maxTemp,
            minTemp = minTemp,
            weatherIcon = weather.iconID,
            weatherCode = weather.iconCode,
            weatherDesc = weather.description,
            timestamp = timestamp
        )
    }

fun DailyForecastData.toEntity(parentId: Long) =
    let {
        DailyForecastEntity(
            null,
            maxTemp = maxTemp,
            minTemp = minTemp,
            weatherIcon = weather.iconID,
            weatherCode = weather.iconCode,
            weatherDesc = weather.description,
            timestamp = timestamp,
            homeParent = parentId
        )
    }
