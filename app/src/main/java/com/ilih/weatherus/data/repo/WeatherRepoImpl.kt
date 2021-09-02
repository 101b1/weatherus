package com.ilih.weatherus.data.repo

import com.ilih.weatherus.data.source.db.WeatherDB
import com.ilih.weatherus.data.source.db.entity.CurrentWeatherEntity
import com.ilih.weatherus.data.source.db.entity.toDto
import com.ilih.weatherus.data.source.network.WeatherAPI
import com.ilih.weatherus.data.source.network.toDto
import com.ilih.weatherus.data.source.network.toEntity
import com.ilih.weatherus.domain.boundary.WeatherRepo
import com.ilih.weatherus.domain.entity.CurrentWeatherDto
import com.ilih.weatherus.domain.entity.DailyForecastDto
import com.ilih.weatherus.domain.entity.HourlyForecastDto
import com.ilih.weatherus.domain.usecase.home.HomeForecastResult
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WeatherRepoImpl
@Inject constructor(
    private val db: WeatherDB,
    private val api: WeatherAPI
) : WeatherRepo {

//    override fun getHomeDto(cityId: Int?): Single<HomeForecastResult> {
//        TODO("Not yet implemented")
//    }

    override fun getCurrentWeather(cityId: Int): Observable<CurrentWeatherDto> {
        var savedWeather: CurrentWeatherEntity? = null
        return Single.concat(
            db.currentWeatherDao().getCityWeather(cityId.toString())
                .flatMap {
                    savedWeather = it
                    Single.just(it.toDto())
                },
            api.getCurrentWeather(cityId).flatMap {
                savedWeather?.let{db.currentWeatherDao().delete(savedWeather!!)}
                db.currentWeatherDao().insert(it.data.toEntity())
                Single.just(it.data.toDto())
            })
            .toObservable()
    }

    override fun getDailyForecast(cityId: Int): Observable<ArrayList<DailyForecastDto>> {
        return Single.concat(,

        )
    }

    override fun getHourlyForecast(cityId: Int): Observable<ArrayList<HourlyForecastDto>> {
        TODO("Not yet implemented")
    }

}