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

    private var homeCity: Long = homeCityStore.getHomeCityID()
    private val DEFAULT_HOME_CITY = 524901L

    val homeForecastSubject: PublishSubject<HomeForecastResult> = PublishSubject.create()

    override fun nextForecast(){
        if (homeCity == 0L){
            homeCity = DEFAULT_HOME_CITY
            homeCityStore.saveHomeCityID(homeCity)
        }
        repo.getHomeDto(homeCity)
            .subscribeOn(Schedulers.io())
            .onErrorReturn {
                it.message?.let { this.log(it) }
                HomeError
            }
            .subscribe {
                homeForecastSubject.onNext(it)
            }
    }

    override fun getObservable(): Observable<HomeForecastResult> = homeForecastSubject

}