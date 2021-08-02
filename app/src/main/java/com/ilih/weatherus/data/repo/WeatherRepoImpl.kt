package com.ilih.weatherus.data.repo

import com.ilih.weatherus.data.source.db.WeatherDB
import com.ilih.weatherus.data.source.network.WeatherAPI
import com.ilih.weatherus.domain.boundary.WeatherRepo
import com.ilih.weatherus.domain.entity.CurrentWeatherDto
import com.ilih.weatherus.domain.entity.DailyForecastDto
import com.ilih.weatherus.domain.entity.HourlyForecastDto
import com.ilih.weatherus.domain.usecase.home.HomeForecastResult
import io.reactivex.Single
import javax.inject.Inject

class WeatherRepoImpl
@Inject constructor(
    private val db: WeatherDB,
    private val api: WeatherAPI
) : WeatherRepo {

    override fun getHomeDto(cityId: Int): Single<HomeForecastResult> {
        TODO("Not yet implemented")
    }

    override fun getCurrentWeather(cityId: Int): Single<CurrentWeatherDto> {
        TODO("Not yet implemented")
    }

    override fun getDailyForecast(cityId: Int): Single<DailyForecastDto> {
        TODO("Not yet implemented")
    }

    override fun getHourlyForecast(cityId: Int): Single<HourlyForecastDto> {
        TODO("Not yet implemented")
    }

}