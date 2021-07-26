package com.ilih.weatherus.domain.boundary

import com.ilih.weatherus.domain.entity.CurrentForecastDto
import com.ilih.weatherus.domain.entity.DailyForecastDto
import com.ilih.weatherus.domain.entity.HourlyForecastDto
import com.ilih.weatherus.domain.usecase.home.HomeForecastResult
import io.reactivex.Single

interface HomeRepo {

    fun getHomenDto(cityId: Int): Single<HomeForecastResult>

    fun getCurrentForecast(cityId: Int): Single<CurrentForecastDto>

    fun getDailyForecast(cityId: Int): Single<DailyForecastDto>

    fun getHourlyForecast(cityId: Int): Single<HourlyForecastDto>
}