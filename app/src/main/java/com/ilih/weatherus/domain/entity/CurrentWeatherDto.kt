package com.ilih.weatherus.domain.entity

import com.ilih.weatherus.data.source.network.CurrentWeatherData

data class CurrentWeatherDto(
        val temperature: Float,
        val feelsLikeTemp: Float,
        val pressure: Float,
        val weaterIcon: String,
        val weatherCode: String,
        val weatherDesc: String,
        val windSpeed: Float,
        val windDir: String,
        val sunriseTime: String,
        val sunsetTime: String,
        val cloudCoverage: Float,
        val uvIndex: Float,
        val cityName: String,
        val countryCode: String,
        val humidity: String,
        val timestamp: Long
)


