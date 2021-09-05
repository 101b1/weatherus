package com.ilih.weatherus.domain.usecase.home

import com.ilih.weatherus.domain.boundary.HomeForecastResult
import com.ilih.weatherus.domain.boundary.WeatherRepo
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject



class HomeInteractorImpl
@Inject constructor(
    private val repo: WeatherRepo,
    private val cityId: Long
) : HomeInteractor {

    val homeForecastSubject: PublishSubject<HomeForecastResult> = PublishSubject.create()

    override fun nextForecast(){
//        Observable.zip(
//            repo.getCurrentWeather(cityId),
//            repo.getDailyForecast(cityId),
//            repo.getHourlyForecast(cityId),
//            {current, daily, hourly ->
//                HomeSuccess(HomeDto(current, hourly, daily)) as HomeForecastResult
//            }
//        ).onErrorReturn { t -> Error(t.localizedMessage) as HomeForecastResult }
//            .subscribeOn(Schedulers.io())
//            .subscribe {
//                homeForecastSubject.onNext(it)
//            }


    }

    override fun getObservable(): Observable<HomeForecastResult> = homeForecastSubject

}