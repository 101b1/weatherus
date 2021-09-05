package com.ilih.weatherus.domain.boundary

interface HomeCityStore {
    fun getHomeCityID(): Long
    fun saveHomeCityID(cityID: Long)
}