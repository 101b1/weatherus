package com.ilih.weatherus.domain.usecase.search

import com.ilih.weatherus.domain.entity.CityDto

sealed class SearchEvent

object EmptySearch: SearchEvent()
object SearchLoading: SearchEvent()
data class SearchResult(val list: ArrayList<CityDto>): SearchEvent()
data class SearchError(val errorMessage: String): SearchEvent()
