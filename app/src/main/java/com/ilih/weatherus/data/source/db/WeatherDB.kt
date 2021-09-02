package com.ilih.weatherus.data.source.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ilih.weatherus.data.source.db.dao.CurrentWeatherDao
import com.ilih.weatherus.data.source.db.dao.DailyForecastDao
import com.ilih.weatherus.data.source.db.dao.HourlyForecastDao
import com.ilih.weatherus.data.source.db.entity.CurrentWeatherEntity
import com.ilih.weatherus.data.source.db.entity.DailyForecastEntity
import com.ilih.weatherus.data.source.db.entity.HourlyForecastEntity

@Database(
    entities = [CurrentWeatherEntity::class, DailyForecastEntity::class, HourlyForecastEntity::class],
    version = 1,
    exportSchema = false
)
abstract class WeatherDB : RoomDatabase() {
    abstract fun currentWeatherDao(): CurrentWeatherDao
    abstract fun dailyForecastDao(): DailyForecastDao
    abstract fun hourlyForecastDao(): HourlyForecastDao
}