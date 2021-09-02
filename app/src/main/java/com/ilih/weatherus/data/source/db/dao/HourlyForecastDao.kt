package com.ilih.weatherus.data.source.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.ilih.weatherus.data.source.db.entity.HourlyForecastEntity
import io.reactivex.Single

@Dao
interface HourlyForecastDao {
    @Query("SELECT * FROM hourly_forecast WHERE city_name=:name LIMIT 1")
    fun getCityForecast(name: String): Single<HourlyForecastEntity>
}