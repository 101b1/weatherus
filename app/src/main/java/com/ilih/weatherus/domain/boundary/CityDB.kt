package com.ilih.weatherus.domain.boundary

import com.ilih.weatherus.domain.entity.CityDto
import io.reactivex.Single

interface CityDB {
    fun searchCitiesByNameQuery(query: String): Single<ArrayList<CityDto>>
}