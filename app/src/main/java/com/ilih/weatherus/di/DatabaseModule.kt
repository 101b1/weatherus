package com.ilih.weatherus.di

import android.content.Context
import androidx.room.Room
import com.ilih.weatherus.data.source.db.WeatherDB
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {

    private val DB_NAME = "weather.db"

    @Provides
    @Singleton
    fun providesDatabase(context: Context) = Room.databaseBuilder(context, WeatherDB::class.java, DB_NAME).createFromAsset("weatherus.db").build()

}