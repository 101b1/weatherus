package com.ilih.weatherus.data.source.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "home_forecast")
data class HomeForecastEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "city_id")
    val cityId: Long,
//    val location: String
)
