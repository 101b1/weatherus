package com.ilih.weatherus.domain.usecase.location

import com.ilih.weatherus.domain.boundary.ForecastRepo
import com.ilih.weatherus.log
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.schedulers.Schedulers

sealed class ForecastResult
data class Success(val locationForecast: LocationDto): ForecastResult()
data class Error(val locationForecast: LocationDto, val errorMessage: String): ForecastResult()

class LocationInteractorImpl(
    private val repo: ForecastRepo,
    private val cityId: Int
) : LocationInteractor {

    override fun getLatestForecasts() =
        Observable.create(ObservableOnSubscribe<ForecastResult> { emitter ->
            repo.apply {
                getLocationDto(cityId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .subscribe { it ->
                        this@LocationInteractorImpl.log("Location forecast = $it")
                        emitter.onNext(it)
                    }
            }
        })


}