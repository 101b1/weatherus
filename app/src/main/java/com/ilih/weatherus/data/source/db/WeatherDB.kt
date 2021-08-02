package com.ilih.weatherus.data.source.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ilih.weatherus.data.source.db.dao.CurrentWeatherDao

@Database(entities = [], version = 1, exportSchema = false)
abstract class WeatherDB: RoomDatabase() {

    abstract fun currentWeatherDao(): CurrentWeatherDao

}