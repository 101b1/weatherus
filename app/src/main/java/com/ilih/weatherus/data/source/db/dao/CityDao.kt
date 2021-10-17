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

    @Query("SELECT * FROM cities JOIN cities_fts ON cities.city_name == cities_fts.city_name WHERE cities_fts.city_name MATCH :search")
    fun searchCities(search: String): Single<List<CityEntity>>

    @Query("SELECT * FROM cities WHERE favourite=1")
    fun getFavouriteCities(): Single<List<CityEntity>>
}