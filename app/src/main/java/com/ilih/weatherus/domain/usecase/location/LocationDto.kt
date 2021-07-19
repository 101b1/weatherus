package com.ilih.weatherus.domain.usecase.location

import com.ilih.weatherus.domain.entity.CurrentForecastDto
import com.ilih.weatherus.domain.entity.DailyForecastDto
import com.ilih.weatherus.domain.entity.HourlyForecastDto

data class LocationDto(
    val currentForecast: CurrentForecastDto,
    val hourlyForecast: HourlyForecastDto,
    val dailyForecast: DailyForecastDto
)
