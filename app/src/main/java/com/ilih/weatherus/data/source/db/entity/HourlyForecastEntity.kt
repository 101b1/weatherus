package com.ilih.weatherus.data.source.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ilih.weatherus.domain.entity.HourlyForecastDto

@Entity(tableName = "hourly_forecast")
data class HourlyForecastEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long?,
    @ColumnInfo(name = "temperature")
    val temperature: Float,
    @ColumnInfo(name = "weather_icon")
    val weatherIcon: String,
    @ColumnInfo(name = "weather_code")
    val weatherCode: Int,
    @ColumnInfo(name = "weather_desc")
    val weatherDesc: String,
    @ColumnInfo(name = "timestamp")
    val localTimestamp: String,
    val homeParent: Long
)

fun HourlyForecastEntity.toDto() =
    let {
        HourlyForecastDto(
            temperature = temperature,
            weaterIcon = weatherIcon,
            weatherCode = weatherCode,
            weatherDesc = weatherDesc,
            localTimestamp = localTimestamp
        )
    }
