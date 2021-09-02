package com.ilih.weatherus.data.source.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET("forecast/hourly")
    fun getHourlyForecast(
        @Query("city_id") cityID: Int
    ): Single<HourlyForecastResponse>

    @GET("forecast/daily")
    fun getDailyForecast(
        @Query("city_id") cityID: Int
    ): Single<DailyForecastResponse>

    @GET("current")
    fun getCurrentWeather(
        @Query("city_id") cityID: Int
    ): Single<CurrentWeatherResponse>

    @GET("current")
    fun getCurrentWeather(
        @Query("city") cityName: String,
        @Query("country") countryCode: String
    ): Single<CurrentWeatherResponse>

    @GET("current")
    fun getCurrentWeather(
        @Query("lat") lat: Float,
        @Query("lon") lon: Float
    ): Single<CurrentWeatherResponse>

}