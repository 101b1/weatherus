package com.ilih.weatherus.domain.usecase.search

import io.reactivex.Observable

interface SearchInteractor {
    fun homeCityChosen(cityId: Long): Boolean
    fun favouriteCityChosen(cityId: Long)
    fun getSearchResultObservable(): Observable<SearchEvent>
}