package com.ilih.weatherus.data.source.db.dao

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.PrimaryKey
import androidx.room.Query
import com.ilih.weatherus.data.source.db.entity.CurrentWeatherEntity
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface CurrentWeatherDao: BaseDao<CurrentWeatherEntity> {

    @Query("SELECT * FROM current_weather WHERE id=:id LIMIT 1")
    fun getById(id: Long): CurrentWeatherEntity

    @Query("SELECT * FROM current_weather WHERE homeParent=:cityId LIMIT 1")
    fun getHomeWeather(cityId: Long): Single<CurrentWeatherEntity?>

    @Query("SELECT * FROM current_weather")
    fun getAllSingle(): Single<List<CurrentWeatherEntity>>

    @Query("SELECT * FROM current_weather WHERE city_name=:name LIMIT 1")
    fun getCityWeather(name: String): Single<CurrentWeatherEntity>

    @Query("DELETE FROM current_weather WHERE homeParent=:cityId")
    fun deleteAllForCity(cityId: Long)
}
