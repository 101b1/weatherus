package com.ilih.weatherus.domain.usecase.search

import android.database.Observable
import com.ilih.weatherus.domain.entity.CityDto
import io.reactivex.Completable

interface SearchInteractor {
    fun searchCities(query: String)
    fun homeCityChosen(cityId: Long): Boolean
    fun favouriteCityChosen(cityId: Long): Completable
    fun getSearchResultObservable(): Observable<ArrayList<CityDto>>
}