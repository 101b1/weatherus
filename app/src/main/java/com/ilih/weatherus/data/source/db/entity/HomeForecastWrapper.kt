package com.ilih.weatherus.data.source.db.entity

import androidx.room.Embedded
import androidx.room.Relation

data class HomeForecastWrapper(
    @Embedded
    val homeForecast: HomeForecastEntity,

    @Relation(
        parentColumn = "cityId",
        entityColumn = "homeParent"
    )
    val currentWeather: CurrentWeatherEntity,

    @Relation(
        parentColumn = "cityId",
        entityColumn = "homeParent"
    )
    val dailyForecast: List<DailyForecastEntity>,

    @Relation(
        parentColumn = "cityId",
        entityColumn = "homeParent"
    )
    val hourlyForecast: List<HourlyForecastEntity>,
)
