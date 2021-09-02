package com.ilih.weatherus.data.source.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sixteen_days")
data class SixteenDaysEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "data")
    val data: ArrayList<DailyForecastEntity>
)
