package com.ilih.weatherus.data.source.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.ilih.weatherus.data.source.db.entity.CityEntity
import com.ilih.weatherus.data.source.db.entity.HomeForecastEntity
import com.ilih.weatherus.data.source.db.entity.HomeForecastWrapper
import io.reactivex.Single

@Dao
interface HomeForecastDao: BaseDao<HomeForecastEntity> {
    @Query("SELECT * FROM home_forecast WHERE city_id=:id LIMIT 1")
    fun getByID(id: Long): HomeForecastEntity

    @Query("SELECT * FROM home_forecast WHERE city_id=:id LIMIT 1")
    fun getByIDRx(id: Long): Single<HomeForecastEntity>

    @Transaction
    @Query("SELECT * FROM home_forecast WHERE city_id=:cityID LIMIT 1")
    fun getHomeForecastWrapper(cityID: Long): HomeForecastWrapper
}