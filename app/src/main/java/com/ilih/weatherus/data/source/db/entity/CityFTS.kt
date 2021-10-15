package com.ilih.weatherus.data.source.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4

@Fts4(contentEntity = CityEntity::class)
@Entity(tableName = "cities_fts")
data class CityFTS(
    @ColumnInfo(name = "city_name")
    val name: String,
    @ColumnInfo(name = "state_code")
    val stateCode: String,
    @ColumnInfo(name = "country_code")
    val countryCode: String,
    @ColumnInfo(name = "country_full")
    val countryName: String
)
