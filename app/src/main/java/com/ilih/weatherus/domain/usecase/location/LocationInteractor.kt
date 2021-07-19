package com.ilih.weatherus.domain.usecase.location

import com.ilih.weatherus.domain.entity.CurrentForecastDto
import com.ilih.weatherus.domain.entity.DailyForecastDto
import com.ilih.weatherus.domain.entity.HourlyForecastDto
import io.reactivex.Observable
import io.reactivex.Single


interface LocationInteractor {

    fun getLatestForecasts(): Observable<ForecastResult>

}