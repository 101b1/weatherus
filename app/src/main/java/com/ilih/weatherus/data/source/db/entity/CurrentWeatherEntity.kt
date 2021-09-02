package com.ilih.weatherus.data.source.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ilih.weatherus.domain.entity.CurrentWeatherDto

@Entity(tableName = "current_weather")
data class CurrentWeatherEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")  // id is City,CountryCode
    val id: String,
    @ColumnInfo(name = "temperature")
    val temperature: Float,
    @ColumnInfo(name = "apparent_temp")
    val feelsLikeTemp: Int,
    @ColumnInfo(name = "pressure")
    val pressure: Float,
    @ColumnInfo(name = "weather_icon")
    val weatherIcon: String,
    @ColumnInfo(name = "weather_code")
    val weatherCode: String,
    @ColumnInfo(name = "weather_desc")
    val weatherDesc: String,
    @ColumnInfo(name = "wind_speed")
    val windSpeed: Float,
    @ColumnInfo(name = "wind_dir")
    val windDir: String,
    @ColumnInfo(name = "sunrise_time")
    val sunriseTime: String,
    @ColumnInfo(name = "sunset_time")
    val sunsetTime: String,
    @ColumnInfo(name = "cloud_coverage")
    val cloudCoverage: Int,
    @ColumnInfo(name = "uv_index")
    val uvIndex: Int,
    @ColumnInfo(name = "city_name")
    val cityName: String,
    @ColumnInfo(name = "country_code")
    val countryCode: String,
    @ColumnInfo(name = "humidity")
    val humidity: String,
    @ColumnInfo(name = "timestamp")
    val timestamp: Long
)

fun CurrentWeatherEntity.toDto() =
    let {
        CurrentWeatherDto(
            temperature = temperature,
            feelsLikeTemp = feelsLikeTemp,
            pressure = pressure,
            weaterIcon = weatherIcon,
            weatherCode = weatherCode,
            weatherDesc = weatherDesc,
            windSpeed = windSpeed,
            windDir = windDir,
            sunriseTime = sunriseTime,
            sunsetTime = sunsetTime,
            cloudCoverage = cloudCoverage,
            uvIndex = uvIndex,
            cityName = cityName,
            countryCode = countryCode,
            humidity = humidity,
            timestamp = timestamp

        )
    }
