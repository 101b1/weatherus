package com.ilih.weatherus.domain.boundary

import com.ilih.weatherus.domain.usecase.home.HomeDto

sealed class HomeForecastResult
data class HomeSuccess(val homeForecast: HomeDto): HomeForecastResult()
data class HomeCache(val homeForecast: HomeDto): HomeForecastResult()
object HomeEmpty: HomeForecastResult()
object HomeError: HomeForecastResult()
