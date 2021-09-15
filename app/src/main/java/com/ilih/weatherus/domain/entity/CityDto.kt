package com.ilih.weatherus.domain.entity

import androidx.room.ColumnInfo

data class CityDto(
    val id: Long,
    val name: String,
    val stateCode: String,
    val countryCode: String,
    val countryName: String,
    val latitude: Float,
    val longitude: Float
)
