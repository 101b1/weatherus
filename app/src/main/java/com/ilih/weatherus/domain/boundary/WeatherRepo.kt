package com.ilih.weatherus.domain.boundary

import com.ilih.weatherus.domain.entity.CurrentWeatherDto
import com.ilih.weatherus.domain.entity.DailyForecastDto
import com.ilih.weatherus.domain.entity.HourlyForecastDto
import com.ilih.weatherus.domain.usecase.home.HomeForecastResult
import io.reactivex.Observable
import io.reactivex.Single

interface WeatherRepo {

//    fun getHomeDto(cityId: Int?): Single<HomeForecastResult>

    fun getCurrentWeather(cityId: Int): Observable<CurrentWeatherDto>

    fun getDailyForecast(cityId: Int): Observable<ArrayList<DailyForecastDto>>

    fun getHourlyForecast(cityId: Int): Observable<ArrayList<HourlyForecastDto>>
}