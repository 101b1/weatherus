package com.ilih.weatherus.domain.usecase.favourites

import com.ilih.weatherus.domain.entity.CityDto
import io.reactivex.Observable
import io.reactivex.Single

interface FavouritesInteractor {

    fun getFavourites()
    fun deleteFavourite(cityId: Long)
    fun getObservable(): Observable<FavouriteEvent>

}