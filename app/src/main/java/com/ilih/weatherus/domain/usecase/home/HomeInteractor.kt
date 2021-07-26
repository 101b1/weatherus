package com.ilih.weatherus.domain.usecase.home

import io.reactivex.Observable


interface HomeInteractor {

    fun getLatestForecasts(): Observable<HomeForecastResult>

}