package com.ilih.weatherus.domain.boundary

import com.ilih.weatherus.domain.entity.CurrentWeatherDto
import com.ilih.weatherus.domain.entity.DailyForecastDto
import com.ilih.weatherus.domain.entity.HourlyForecastDto
import io.reactivex.Observable
import io.reactivex.Single

interface WeatherRepo {

    fun getHomeDto(cityId: Long): Observable<HomeForecastResult>

    fun getCurrentWeather(cityId: Long): Observable<CurrentWeatherDto>

    fun getDailyForecast(cityId: Long): Observable<ArrayList<DailyForecastDto>>

    fun getHourlyForecast(cityId: Long): Observable<ArrayList<HourlyForecastDto>>
}