package com.ilih.weatherus.domain.usecase.search

import io.reactivex.Completable
import io.reactivex.Observable

interface SearchInteractor {
    fun searchCities(query: String)
    fun homeCityChosen(cityId: Long): Boolean
    fun favouriteCityChosen(cityId: Long): Completable
    fun getSearchResultObservable(): Observable<SearchEvent>
}