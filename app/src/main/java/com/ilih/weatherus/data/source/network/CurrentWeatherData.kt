package com.ilih.weatherus.data.source.network

import com.google.gson.annotations.SerializedName
import com.ilih.weatherus.data.source.db.entity.CurrentWeatherEntity
import com.ilih.weatherus.domain.entity.CurrentWeatherDto

data class CurrentWeatherData(
    @SerializedName("wind_cdir")
    val windDir: String,
    @SerializedName("rh")
    val relativeHumidity: Int,
    @SerializedName("pod")
    val partOfTheDay: String,
    @SerializedName("lon")
    val lng: String,
    @SerializedName("pres")
    val pressure: Float,
    @SerializedName("timezone")
    val timezome: String,
    @SerializedName("ob_time")
    val observationTime: String,
    @SerializedName("country_code")
    val countryCode: String,
    @SerializedName("clouds")
    val cloudCoverage: Int,
    @SerializedName("vis")
    val visibility: Int,
    @SerializedName("wind_spd")
    val windSpeed: Float,
    @SerializedName("app_temp")
    val apparentTemp: Int,
    @SerializedName("state_code")
    val stateCode: String,
    @SerializedName("ts")
    val timestamp: Long,
    @SerializedName("h_angle")
    val solarHourAngle: Int,
    @SerializedName("dewpt")
    val dewPoint: Float,
    @SerializedName("weather")
    val weather: WeatherDescription,
    @SerializedName("uv")
    val uvIndex: Int,
    @SerializedName("aqi")
    val airQuality: Int,
    @SerializedName("station")
    val station: String,
    @SerializedName("wind_dir")
    val windDirectionDegrees: Int,
    @SerializedName("elev_angle")
    val solarElevationAngle: Int,
    @SerializedName("datetime")
    val dateTime: String,
    @SerializedName("precip")
    val liquidPrecipitation: Int,
    @SerializedName("solar_rad")
    val solarRad: Int,
    @SerializedName("city_name")
    val cityName: String,
    @SerializedName("sunrise")
    val sunrise: String,
    @SerializedName("sunset")
    val sunset: String,
    @SerializedName("temp")
    val temp: Float,
    @SerializedName("lat")
    val lat: String,
    @SerializedName("slp")
    val seaLevelPressure: Float
)


fun CurrentWeatherData.toEntity() =
    let{
        CurrentWeatherEntity(
            "$cityName,$countryCode",
            temperature = temp,
            feelsLikeTemp = apparentTemp,
            pressure = pressure,
            weatherIcon = weather.iconID,
            weatherCode = weather.iconCode,
            weatherDesc = weather.description,
            windSpeed = windSpeed,
            windDir = windDir,
            sunriseTime = sunrise,
            sunsetTime = sunset,
            cloudCoverage = cloudCoverage,
            uvIndex = uvIndex,
            cityName = cityName,
            countryCode = countryCode,
            humidity = relativeHumidity.toString(),
            timestamp = timestamp,
        )
    }
fun CurrentWeatherData.toDto() =
    let {
        CurrentWeatherDto(
            temperature = temp,
            feelsLikeTemp = apparentTemp,
            pressure = pressure,
            weaterIcon = weather.iconID,
            weatherCode = weather.iconCode,
            weatherDesc = weather.description,
            windSpeed = windSpeed,
            windDir = windDir,
            sunriseTime = sunrise,
            sunsetTime = sunset,
            cloudCoverage = cloudCoverage,
            uvIndex = uvIndex,
            cityName = cityName,
            countryCode = countryCode,
            humidity = relativeHumidity.toString(),
            timestamp = timestamp
        )
    }

