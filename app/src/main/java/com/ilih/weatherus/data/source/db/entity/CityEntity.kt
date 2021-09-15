package com.ilih.weatherus.data.source.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ilih.weatherus.domain.entity.CityDto

@Entity(tableName = "cities")
data class CityEntity(
    @PrimaryKey
    @ColumnInfo(name = "city_id")
    val cityID: Long?,
    @ColumnInfo(name = "city_name")
    val name: String,
    @ColumnInfo(name = "state_code")
    val stateCode: String,
    @ColumnInfo(name = "country_code")
    val countryCode: String,
    @ColumnInfo(name = "country_full")
    val countryName: String,
    @ColumnInfo(name = "lat")
    val latitude: Float,
    @ColumnInfo(name = "lon")
    val longitude: Float
)

fun CityEntity.toDto() =
    let {
        CityDto(
            id = it.cityID!!,
            name = it.name,
            stateCode = it.stateCode,
            countryCode = it.countryCode,
            countryName = it.countryName,
            latitude = it.latitude,
            longitude = it.longitude
        )
    }
