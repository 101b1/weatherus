package com.ilih.weatherus.data.source.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.ilih.weatherus.data.source.db.entity.DailyForecastEntity
import io.reactivex.Single

@Dao
interface DailyForecastDao: BaseDao<DailyForecastEntity> {
    @Query("SELECT * FROM daily_forecast WHERE city_name=:name LIMIT 1")
    fun getCityForecast(name: String): Single<DailyForecastEntity>
}