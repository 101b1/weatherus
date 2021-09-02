package com.ilih.weatherus.domain.usecase.home

import io.reactivex.Observable
import io.reactivex.Single


interface HomeInteractor {

//    fun getLatestForecasts(): Observable<HomeForecastResult>

    fun nextForecast()
    fun getObservable(): Observable<HomeForecastResult>

}