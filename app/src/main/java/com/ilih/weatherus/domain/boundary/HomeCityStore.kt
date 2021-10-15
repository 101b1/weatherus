package com.ilih.weatherus.domain.boundary

import io.reactivex.Completable
import io.reactivex.Single

interface HomeCityStore {
    fun getHomeCity(): Single<Long>
    fun saveHomeCity(city: Long): Completable
}