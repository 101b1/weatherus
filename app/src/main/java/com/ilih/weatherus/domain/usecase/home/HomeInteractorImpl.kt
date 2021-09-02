package com.ilih.weatherus.domain.usecase.home

import com.ilih.weatherus.domain.boundary.WeatherRepo
import com.ilih.weatherus.log
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import javax.inject.Inject

sealed class HomeForecastResult
data class Success(val homeForecast: HomeDto) : HomeForecastResult()
data class Error(val homeForecast: HomeDto, val errorMessage: String) : HomeForecastResult()

class HomeInteractorImpl
@Inject constructor(
    private val repo: WeatherRepo,
    private val cityId: Int
) : HomeInteractor {

    val homeForecastSubject: PublishSubject<HomeForecastResult> = PublishSubject.create()

    override fun nextForecast(){
        Observable.zip(
            repo.getCurrentWeather(cityId),
            repo.getDailyForecast(cityId),
            repo.getHourlyForecast(cityId),
            {current, daily, hourly ->
                Success(HomeDto(current, hourly, daily)) as HomeForecastResult
            }
        ).onErrorReturn { t -> Error(t.localizedMessage) as HomeForecastResult }
            .subscribeOn(Schedulers.io())
            .subscribe {
                homeForecastSubject.onNext(it)
            }
    }

    override fun getObservable(): Observable<HomeForecastResult> = homeForecastSubject

}