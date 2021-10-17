package com.ilih.weatherus.ui.favourites

import androidx.lifecycle.LiveData
import com.ilih.weatherus.domain.usecase.favourites.FavouriteEvent

interface FavouritesViewModel {
    fun getData(): LiveData<FavouriteEvent>
    fun getViewListener(): FavouritesView.Listener
}