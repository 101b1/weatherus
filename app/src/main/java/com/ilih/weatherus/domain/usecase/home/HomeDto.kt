package com.ilih.weatherus.domain.usecase.home

import com.ilih.weatherus.domain.entity.CurrentWeatherDto
import com.ilih.weatherus.domain.entity.DailyForecastDto
import com.ilih.weatherus.domain.entity.HourlyForecastDto

data class HomeDto(
    val currentWeather: CurrentWeatherDto,
    val hourlyForecast: HourlyForecastDto,
    val dailyForecast: DailyForecastDto
)
