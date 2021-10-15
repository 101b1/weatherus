package com.ilih.weatherus.domain.boundary

import com.ilih.weatherus.domain.entity.CityDto
import com.ilih.weatherus.domain.usecase.search.SearchEvent
import io.reactivex.Observable

interface CitySearcher {
    fun search(query: String)
    fun getResultObservable(): Observable<SearchEvent>
}