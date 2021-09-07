package com.ilih.weatherus.data.source.network

import com.ilih.weatherus.BuildConfig
import com.ilih.weatherus.di.RetrofitModule
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET("forecast/hourly")
    fun getHourlyForecast(
        @Query("city_id") cityID: Long,
        @Query("key")
        key: String = BuildConfig.API_KEY
    ): Single<HourlyForecastResponse>

    @GET("forecast/daily")
    fun getDailyForecast(
        @Query("city_id") cityID: Long,
        @Query("key")
        key: String = BuildConfig.API_KEY
    ): Single<DailyForecastResponse>

    @GET("current")
    fun getCurrentWeather(
        @Query("city_id") cityID: Long,
        @Query("key")
        key: String = BuildConfig.API_KEY
    ): Single<CurrentWeatherResponse>

    @GET("current")
    fun getCurrentWeather(
        @Query("city") cityName: String,
        @Query("country") countryCode: String,
        @Query("key")
        key: String = BuildConfig.API_KEY
    ): Single<CurrentWeatherResponse>

    @GET("current")
    fun getCurrentWeather(
        @Query("lat") lat: Float,
        @Query("lon") lon: Float,
        @Query("key")
        key: String = BuildConfig.API_KEY
    ): Single<CurrentWeatherResponse>

}