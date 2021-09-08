package com.ilih.weatherus.domain.entity

data class DailyForecastDto(
    val maxTemp: Float,
    val minTemp: Float,
    val weatherIcon: String,
    val weatherCode: String,
    val weatherDesc: String,
    val timestamp: Long,
)
