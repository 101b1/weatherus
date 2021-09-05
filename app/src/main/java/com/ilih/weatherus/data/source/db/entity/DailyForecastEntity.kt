package com.ilih.weatherus.data.source.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ilih.weatherus.domain.entity.DailyForecastDto

@Entity(tableName = "daily_forecast")
data class DailyForecastEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long?,
    @ColumnInfo(name = "max_temp")
    val maxTemp: Float,
    @ColumnInfo(name = "min_temp")
    val minTemp: Float,
    @ColumnInfo(name = "weather_icon")
    val weatherIcon: String,
    @ColumnInfo(name = "weather_code")
    val weatherCode: Int,
    @ColumnInfo(name = "weather_desc")
    val weatherDesc: String,
    @ColumnInfo(name = "timestamp")
    val timestamp: Long,
    val homeParent: Long
)

fun DailyForecastEntity.toDto() =
    let {
        DailyForecastDto(
            maxTemp = maxTemp,
            minTemp = minTemp,
            weatherIcon = weatherIcon,
            weatherCode = weatherCode,
            weatherDesc = weatherDesc,
            timestamp = timestamp
        )
    }
