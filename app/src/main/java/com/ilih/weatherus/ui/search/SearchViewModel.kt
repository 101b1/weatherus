package com.ilih.weatherus.ui.search

import androidx.lifecycle.LiveData
import com.ilih.weatherus.domain.entity.CityDto
import com.ilih.weatherus.domain.usecase.search.SearchEvent

interface SearchViewModel {
    fun searchData(): LiveData<SearchEvent>
    fun getViewListener(): SearchView.Listener

}