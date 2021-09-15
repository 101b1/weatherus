package com.ilih.weatherus.domain.boundary

import android.database.Observable
import com.ilih.weatherus.domain.entity.CityDto
import io.reactivex.Completable

interface FavouriteCityStore {
    fun getFavouriteCities(): Observable<CityDto>
    fun addFavouriteCity(cityId: Long): Completable
    fun deleteFavouriteCity(cityId: Long): Completable
}