package com.ilih.weatherus.domain.usecase.search

import com.ilih.weatherus.domain.boundary.CityDB
import com.ilih.weatherus.domain.boundary.CitySearcher
import com.ilih.weatherus.domain.boundary.FavouriteCityStore
import com.ilih.weatherus.domain.boundary.HomeCityStore
import com.ilih.weatherus.domain.entity.CityDto
import com.ilih.weatherus.log
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.lang.Exception
import javax.inject.Inject

class SearchInteractorImpl
@Inject constructor(
    private val homeStore: HomeCityStore,
    private val favouriteStore: FavouriteCityStore,
//    private val cityDB: CityDB,
    private val citySearcher: CitySearcher
) : SearchInteractor {

    private val searchSubject: PublishSubject<SearchEvent> = PublishSubject.create()

    override fun searchCities(query: String) {
        citySearcher.getResultObservable()
            .subscribeOn(Schedulers.io())
            .subscribe (
                {
                    searchSubject.onNext(it)
                },
                {
                    this.log(it.message!!)
                    searchSubject.onNext(SearchError(it.message ?: "Search error"))
                }
            )
    }

    override fun homeCityChosen(cityId: Long): Boolean {
        try {
            homeStore.saveHomeCity(cityId)
            this.log("City $cityId saved as home")
            return true
        } catch (ex: Exception){
            this.log("City $cityId was not saved as home:\n${ex.stackTraceToString()}")
            return false
        }
    }

    override fun favouriteCityChosen(cityId: Long): Completable {
        return favouriteStore.addFavouriteCity(cityId)
            .subscribeOn(Schedulers.io())
    }

    override fun getSearchResultObservable(): Observable<SearchEvent> =
        searchSubject
}