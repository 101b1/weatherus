package com.ilih.weatherus.di

import com.ilih.weatherus.data.source.db.WeatherDB
import com.ilih.weatherus.data.source.db.entity.toDto
import com.ilih.weatherus.domain.boundary.FavouriteCityStore
import com.ilih.weatherus.domain.entity.CityDto
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class FavouriteCityStoreImpl
@Inject
constructor(private val db: WeatherDB): FavouriteCityStore {
    override fun getFavouriteCities(): Single<ArrayList<CityDto>> {
        return db.cityDao().getFavouriteCities()
            .map {
                ArrayList(it.map { item -> item.toDto() })
            }
    }

    override fun addFavouriteCity(cityId: Long): Completable {
        return Completable.fromSingle(db.cityDao().getByIDRx(cityId)
            .flatMap {
                db.cityDao().updateRx(it.copy(favourite = true))
            })
    }

    override fun deleteFavouriteCity(cityId: Long): Completable {
        return Completable.fromSingle(db.cityDao().getByIDRx(cityId)
            .flatMap {
                db.cityDao().updateRx(it.copy(favourite = false))
            })
    }
}