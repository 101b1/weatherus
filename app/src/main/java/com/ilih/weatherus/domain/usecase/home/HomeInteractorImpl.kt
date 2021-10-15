package com.ilih.weatherus.domain.usecase.home

import com.ilih.weatherus.domain.boundary.HomeCityStore
import com.ilih.weatherus.domain.boundary.HomeError
import com.ilih.weatherus.domain.boundary.HomeForecastResult
import com.ilih.weatherus.domain.boundary.WeatherRepo
import com.ilih.weatherus.log
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject


class HomeInteractorImpl
@Inject constructor(
    private val repo: WeatherRepo,
    private val homeCityStore: HomeCityStore
) : HomeInteractor {

    val homeForecastSubject: PublishSubject<HomeForecastResult> = PublishSubject.create()

    override fun nextForecast() {
        homeCityStore.getHomeCity()
            .toObservable()
            .flatMap {
                repo.getHomeDto(it)
            }
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { this.log("SUBSCRIBED TO gethomedto") }
            .onErrorReturn {
                it.message?.let { this.log(it) }
                HomeError
            }
            .subscribe(
                {
                    homeForecastSubject.onNext(it)
                },
                {
                    this.log(it.message!!)
                    HomeError
                }
            )
    }

    override fun getObservable(): Observable<HomeForecastResult> = homeForecastSubject

}