package com.ilih.weatherus.data.source.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HomeForecastEntity(
    @PrimaryKey(autoGenerate = false)
    val cityId: Int,
    val location: String
)
