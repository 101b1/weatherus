package com.ilih.weatherus.domain.boundary

import com.ilih.weatherus.domain.entity.CurrentForecastDto
import com.ilih.weatherus.domain.entity.DailyForecastDto
import com.ilih.weatherus.domain.entity.HourlyForecastDto
import com.ilih.weatherus.domain.usecase.location.ForecastResult
import io.reactivex.Single

interface ForecastRepo {

    fun getLocationDto(cityId: Int): Single<ForecastResult>

    fun getCurrentForecast(cityId: Int): Single<CurrentForecastDto>

    fun getDailyForecast(cityId: Int): Single<DailyForecastDto>

    fun getHourlyForecast(cityId: Int): Single<HourlyForecastDto>
}