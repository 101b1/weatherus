package com.ilih.weatherus.domain.usecase.search

import com.ilih.weatherus.domain.boundary.CitySearcher
import com.ilih.weatherus.domain.boundary.FavouriteCityStore
import com.ilih.weatherus.domain.boundary.HomeCityStore
import com.ilih.weatherus.log
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class SearchInteractorImpl
@Inject constructor(
    private val homeStore: HomeCityStore,
    private val favouriteStore: FavouriteCityStore,
//    private val cityDB: CityDB,
    private val citySearcher: CitySearcher
) : SearchInteractor {

    private val searchSubject: PublishSubject<SearchEvent> = PublishSubject.create()

    init{
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
        return try {
            homeStore.saveHomeCity(cityId)
                .subscribeOn(Schedulers.io())
                .subscribe{
                    this.log("City $cityId saved as home")
                }
            true
        } catch (ex: Exception){
            this.log("City $cityId was not saved as home:\n${ex.stackTraceToString()}")
            false
        }
    }

    override fun favouriteCityChosen(cityId: Long) {
        favouriteStore.addFavouriteCity(cityId)
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    override fun getSearchResultObservable(): Observable<SearchEvent> =
        searchSubject
}