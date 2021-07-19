package com.ilih.weatherus.domain.entity

data class CurrentForecastDto(
        val temperature: Float,
        val feelsLikeTemp: Float,
        val pressure: Float,
        val weaterIcon: String,
        val weatherCode: Int,
        val weatherDesc: String,
        val windSpeed: Float,
        val windDir: String,
        val sunriseTime: String,
        val sunsetTime: String,
        val cloudCoverage: Float,
        val uvIndex: Int,
        val cityName: String,
)

