package com.ilih.weatherus.data.source.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ilih.weatherus.data.source.db.dao.*
import com.ilih.weatherus.data.source.db.entity.*

@Database(
    entities = [
        CurrentWeatherEntity::class,
        DailyForecastEntity::class,
//        HourlyForecastEntity::class,
        CityEntity::class,
//        HomeForecastEntity::class
               ],
    version = 1,
    exportSchema = true
)
abstract class WeatherDB : RoomDatabase() {
    abstract fun currentWeatherDao(): CurrentWeatherDao
    abstract fun dailyForecastDao(): DailyForecastDao
//    abstract fun hourlyForecastDao(): HourlyForecastDao
//    abstract fun homeForecastDao(): HomeForecastDao
    abstract fun cityDao(): CityDao
}