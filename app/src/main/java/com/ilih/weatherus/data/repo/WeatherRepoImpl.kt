package com.ilih.weatherus.data.repo

import com.ilih.weatherus.data.source.db.WeatherDB
import com.ilih.weatherus.data.source.db.entity.toDto
import com.ilih.weatherus.data.source.network.*
import com.ilih.weatherus.domain.boundary.*
import com.ilih.weatherus.domain.entity.CurrentWeatherDto
import com.ilih.weatherus.domain.entity.DailyForecastDto
import com.ilih.weatherus.domain.entity.HourlyForecastDto
import com.ilih.weatherus.domain.usecase.home.HomeDto
import com.ilih.weatherus.log
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class WeatherRepoImpl
@Inject constructor(
    private val db: WeatherDB,
    private val api: WeatherAPI
) : WeatherRepo {

    private fun getCWCache(cityId: Long) =
        db.currentWeatherDao()
            .getHomeWeather(cityId)
            .onErrorReturn {
                null
            }
            .subscribeOn(Schedulers.io())

    private fun getDFCache(cityId: Long) =
        db.dailyForecastDao()
            .getCityForecast(cityId)
            .onErrorReturn {
                null
            }
            .subscribeOn(Schedulers.io())

//    private fun getHFCache(cityId: Long) =
//        db.hourlyForecastDao()
//            .getCityForecast(cityId)
//            .onErrorReturn {
//                null
//            }
//            .subscribeOn(Schedulers.io())

    private fun getCWNetwork(cityId: Long) =
        api.getCurrentWeather(cityId)
            .delay(2000, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())

    private fun getDFNetwork(cityId: Long) =
        api.getDailyForecast(cityId)
            .delay(2000, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())

//    private fun getHFNetwork(cityId: Long) =
//        api.getHourlyForecast(cityId)
//            .delay(2000, TimeUnit.MILLISECONDS)
//            .subscribeOn(Schedulers.io())


    private fun getHomeForecastCache(cityId: Long): Single<HomeForecastResult> {
        return Single.zip(
            getCWCache(cityId),
            getDFCache(cityId),
            { current, daily ->
                return@zip if (current == null || daily == null)
                    HomeEmpty as HomeForecastResult
                else
                    HomeCache(
                        HomeDto(
                            current.toDto(),
//                            ArrayList(hourly.map { item -> item.toDto() }),
                            ArrayList(daily.map { item -> item.toDto() })
                        )
                    ) as HomeForecastResult
            }
        ).doOnError { this.log("HomeForecast cache error: ${it.message}") }
            .onErrorReturnItem (
                HomeEmpty as HomeForecastResult
        )
            .subscribeOn(Schedulers.io())
    }

    private fun getHomeForecastNetwork(cityId: Long): Single<HomeForecastResult> {
        return Single.zip(
            getCWNetwork(cityId),
            getDFNetwork(cityId),
//            getHFNetwork(cityId),
            { current: CurrentWeatherResponse, daily: DailyForecastResponse ->
                val currentEntity = current.data[0].toEntity(cityId)
                val dailyList = daily.data.map { item -> item.toEntity(cityId) }
//                val hourlyList = hourly.data.map { item -> item.toEntity(cityId) }
                db.currentWeatherDao().deleteAllForCity(cityId)
                db.dailyForecastDao().deleteAllForCity(cityId)
//                db.hourlyForecastDao().deleteAllForCity(cityId)
                db.currentWeatherDao().insert(currentEntity)
                db.dailyForecastDao().insertAll(dailyList)
//                db.hourlyForecastDao().insertAll(hourlyList)
                HomeSuccess(
                    HomeDto(
                        currentEntity.toDto(),
//                        ArrayList(hourlyList.map { item -> item.toDto() }),
                        ArrayList(dailyList.map { item -> item.toDto() })
                    )
                ) as HomeForecastResult
            }).doOnError {
                this.log(it.stackTraceToString())
        }
            .subscribeOn(Schedulers.io())
    }


    override fun getHomeDto(cityId: Long): Observable<HomeForecastResult> {
        return Single.concat(
            getHomeForecastCache(cityId),
            getHomeForecastNetwork(cityId)
        ).toObservable()
    }

    // Not used
//    private fun getCWCacheAndNetwork(cityId: Long): Observable<CurrentWeatherDto> {
//        return Single.concat(
//            Single.just(
//                db.homeForecastDao().getHomeForecastWrapper(cityId)
//                    .currentWeather.toDto()
//            ),
//            api.getCurrentWeather(cityId).flatMap {
//                db.currentWeatherDao().update(it.data.toEntity(cityId))
//                Single.just(it.data.toDto())
//            }.doOnError { error ->
//                error.message?.let { this.log(it) }
//            }
//        ).toObservable()
//    }

    // Not used
//    private fun getCWFromNetwork(cityId: Long): Observable<CurrentWeatherDto> {
//        return db.homeForecastDao().insertRx(
//            HomeForecastEntity(cityId)
//        ).flatMap {
//            api.getCurrentWeather(cityId)
//                .doOnError { error ->
//                    error.message?.let { this.log(it) }
//                }
//        }.flatMap {
//            Single.just(
//                db.currentWeatherDao().getById(
//                    db.currentWeatherDao().insert(it.data.toEntity(cityId))
//                ).toDto()
//            )
//        }.toObservable().doOnError { error ->
//            error.message?.let { this.log(it) }
//        }
//    }


    override fun getCurrentWeather(cityId: Long): Observable<CurrentWeatherDto> {
        TODO("Not yet implemented")
    }

    override fun getDailyForecast(cityId: Long): Observable<ArrayList<DailyForecastDto>> {
        TODO("Not yet implemented")
    }

    override fun getHourlyForecast(cityId: Long): Observable<ArrayList<HourlyForecastDto>> {
        TODO("Not yet implemented")
    }

}