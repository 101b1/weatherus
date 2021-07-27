package com.ilih.weatherus.data.source.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherIO: API {

    @GET("forecast/hourly")
    override fun getHourlyForecast(
        @Query("city_id") cityID: Int
    ): Single<HourlyForecastResponse>

    @GET("forecast/daily")
    override fun getDailyForecast(
        @Query("city_id") cityID: Int
    ): Single<DailyForecastResponse>

    @GET("current")
    override fun getCurrentWeather(
        @Query("city_id") cityID: Int
    ): Single<CurrentForecastResponse>

    @GET("current")
    override fun getCurrentWeather(
        @Query("lat") lat: Float,
        @Query("lon") lon: Float
    ): Single<CurrentForecastResponse>

}