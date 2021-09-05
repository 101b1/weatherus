package com.ilih.weatherus.domain.usecase.home

import com.ilih.weatherus.domain.boundary.HomeForecastResult
import io.reactivex.Observable


interface HomeInteractor {

//    fun getLatestForecasts(): Observable<HomeForecastResult>

    fun nextForecast()
    fun getObservable(): Observable<HomeForecastResult>

}