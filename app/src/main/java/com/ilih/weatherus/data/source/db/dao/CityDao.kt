package com.ilih.weatherus.data.source.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.ilih.weatherus.data.source.db.entity.CityEntity
import io.reactivex.Single

@Dao
interface CityDao: BaseDao<CityEntity> {
    @Query("SELECT * FROM cities WHERE city_id=:id LIMIT 1")
    fun getByID(id: Long): CityEntity

    @Query("SELECT * FROM cities WHERE city_id=:id LIMIT 1")
    fun getByIDRx(id: Long): Single<CityEntity>
}