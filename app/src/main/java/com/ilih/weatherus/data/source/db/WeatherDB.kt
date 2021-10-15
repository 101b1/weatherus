package com.ilih.weatherus.data.source.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ilih.weatherus.data.source.db.dao.CityDao
import com.ilih.weatherus.data.source.db.dao.CurrentWeatherDao
import com.ilih.weatherus.data.source.db.dao.DailyForecastDao
import com.ilih.weatherus.data.source.db.entity.CityEntity
import com.ilih.weatherus.data.source.db.entity.CityFTS
import com.ilih.weatherus.data.source.db.entity.CurrentWeatherEntity
import com.ilih.weatherus.data.source.db.entity.DailyForecastEntity

@Database(
    entities = [
        CurrentWeatherEntity::class,
        DailyForecastEntity::class,
//        HourlyForecastEntity::class,
        CityEntity::class,
//        HomeForecastEntity::class
        CityFTS::class],
    version = 1,
    exportSchema = true
)
abstract class WeatherDB : RoomDatabase() {
    abstract fun currentWeatherDao(): CurrentWeatherDao
    abstract fun dailyForecastDao(): DailyForecastDao
    abstract fun cityDao(): CityDao

}