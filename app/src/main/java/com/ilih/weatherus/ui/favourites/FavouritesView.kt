package com.ilih.weatherus.ui.favourites

import com.ilih.weatherus.domain.entity.CityDto

interface FavouritesView {
    fun onFinishInflate(listener: Listener)

    interface Listener{
        fun onFavouriteClicked(city: CityDto)
    }
}