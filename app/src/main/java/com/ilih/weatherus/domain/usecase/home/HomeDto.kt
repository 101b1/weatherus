package com.ilih.weatherus.domain.usecase.home

import com.ilih.weatherus.domain.entity.CurrentForecastDto
import com.ilih.weatherus.domain.entity.DailyForecastDto
import com.ilih.weatherus.domain.entity.HourlyForecastDto

data class HomeDto(
    val currentForecast: CurrentForecastDto,
    val hourlyForecast: HourlyForecastDto,
    val dailyForecast: DailyForecastDto
)
