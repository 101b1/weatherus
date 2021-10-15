package com.ilih.weatherus.ui.search

import com.ilih.weatherus.domain.entity.CityDto

interface SearchView {

    fun onFinishInflate(listener: SearchView.Listener)

    interface Listener{
        fun onCityClicked(city: CityDto)
    }

}