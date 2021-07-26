package com.ilih.weatherus.domain.usecase.home

import com.ilih.weatherus.domain.boundary.HomeRepo
import com.ilih.weatherus.log
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.schedulers.Schedulers

sealed class HomeForecastResult
data class Success(val homeForecast: HomeDto) : HomeForecastResult()
data class Error(val homeForecast: HomeDto, val errorMessage: String) : HomeForecastResult()

class HomeInteractorImpl(
    private val repo: HomeRepo,
    private val cityId: Int
) : HomeInteractor {

    override fun getLatestForecasts() =
        Observable.create(ObservableOnSubscribe<HomeForecastResult> { emitter ->
            repo.getHomenDto(cityId)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe { it ->
                    this@HomeInteractorImpl.log("Location forecast = $it")
                    emitter.onNext(it)
                }
        })


}