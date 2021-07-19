package com.ilih.weatherus.domain.entity

data class HourlyForecastDto(
    val temperature: Float,
    val weaterIcon: String,
    val weatherCode: Int,
    val weatherDesc: String,
    val localTimestamp: String
)
