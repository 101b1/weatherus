package com.ilih.weatherus.data.source.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ilih.weatherus.data.source.db.dao.*
import com.ilih.weatherus.data.source.db.entity.*
import com.ilih.weatherus.domain.boundary.CityDB
import com.ilih.weatherus.domain.entity.CityDto
import io.reactivex.Single

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
abstract class WeatherDB : RoomDatabase(), CityDB {
    abstract fun currentWeatherDao(): CurrentWeatherDao
    abstract fun dailyForecastDao(): DailyForecastDao

    //    abstract fun hourlyForecastDao(): HourlyForecastDao
//    abstract fun homeForecastDao(): HomeForecastDao
    abstract fun cityDao(): CityDao

    override fun searchCitiesByNameQuery(query: String): Single<ArrayList<CityDto>> {
        return cityDao().searchCities(query).map {
            ArrayList(it.map { item -> item.toDto() })
        }
    }

}