package com.ilih.weatherus.domain.boundary

import com.ilih.weatherus.domain.entity.CityDto
import io.reactivex.Completable
import io.reactivex.Single

interface FavouriteCityStore {
    fun getFavouriteCities(): Single<ArrayList<CityDto>>
    fun addFavouriteCity(cityId: Long): Completable
    fun deleteFavouriteCity(cityId: Long): Completable
}