package com.ilih.weatherus.di

import com.ilih.weatherus.data.source.db.WeatherDB
import com.ilih.weatherus.data.source.db.entity.toDto
import com.ilih.weatherus.domain.boundary.CitySearcher
import com.ilih.weatherus.domain.entity.CityDto
import com.ilih.weatherus.domain.usecase.search.*
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class CitySearcherImpl
@Inject constructor(
    private val db: WeatherDB
) : CitySearcher {

    private val searchSubject = BehaviorSubject.create<SearchEvent>()

    override fun search(query: String) {
        //TODO filter search query
        searchSubject.onNext(SearchLoading)
        db.cityDao().searchCities(query)
            .subscribeOn(Schedulers.io())
            .map {
                if (it.size > 0)
                    SearchResult(ArrayList(it.map { item -> item.toDto() }))
                else
                    EmptySearch
            }
            .onErrorResumeNext {
                Single.just(
                    it.message?.let { error ->
                        SearchError(error)
                    } ?: SearchError("Search error")
                )

            }
            .subscribe { result ->
                searchSubject.onNext(result)
            }
    }

    override fun getResultObservable(): Observable<SearchEvent> {
        return searchSubject
    }
}