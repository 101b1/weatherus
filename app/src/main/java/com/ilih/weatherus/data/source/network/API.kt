package com.ilih.weatherus.data.source.network

import io.reactivex.Single

interface API {

    fun getHourlyForecast(cityID: Int): Single<HourlyForecastResponse>

    fun getDailyForecast(cityID: Int): Single<DailyForecastResponse>

    fun getCurrentWeather(cityID: Int): Single<CurrentForecastResponse>

    fun getCurrentWeather(lat: Float, lon: Float): Single<CurrentForecastResponse>

}