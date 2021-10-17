package com.ilih.weatherus.domain.usecase.home

import com.ilih.weatherus.domain.boundary.HomeForecastResult
import io.reactivex.Observable


interface HomeInteractor {

//    fun getLatestForecasts(): Observable<HomeForecastResult>

    fun nextForecast(cityId: Long = 0L)
    fun getObservable(): Observable<HomeForecastResult>

}