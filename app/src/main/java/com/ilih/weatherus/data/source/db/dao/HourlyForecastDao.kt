package com.ilih.weatherus.data.source.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.ilih.weatherus.data.source.db.entity.HourlyForecastEntity
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface HourlyForecastDao: BaseDao<HourlyForecastEntity> {
//    @Query("SELECT * FROM hourly_forecast WHERE city_name=:name LIMIT 1")
//    fun getCityForecast(name: String): Single<HourlyForecastEntity>

    @Query("SELECT * FROM hourly_forecast WHERE homeParent=:cityId")
    fun getCityForecast(cityId: Long): Observable<List<HourlyForecastEntity>>

    @Query("DELETE FROM hourly_forecast WHERE homeParent=:cityId")
    fun deleteAllForCity(cityId: Long)
}