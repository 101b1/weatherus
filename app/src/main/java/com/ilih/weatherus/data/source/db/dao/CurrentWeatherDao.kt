package com.ilih.weatherus.data.source.db.dao

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.PrimaryKey

@Dao
data class CurrentWeatherDao(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: String
)
