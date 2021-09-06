package com.ilih.weatherus.data.source.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.ilih.weatherus.data.source.db.entity.DailyForecastEntity
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface DailyForecastDao: BaseDao<DailyForecastEntity> {

    @Query("SELECT * FROM daily_forecast WHERE homeParent=:cityId")
    fun getCityForecast(cityId: Long): Observable<List<DailyForecastEntity>>

    @Query("DELETE FROM daily_forecast WHERE homeParent=:cityId")
    fun deleteAllForCity(cityId: Long)
}