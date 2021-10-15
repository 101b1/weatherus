package com.ilih.weatherus.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ilih.weatherus.data.source.db.WeatherDB
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton

@Module
object DatabaseModule {

    private val DB_NAME = "weather.db"

    @Provides
    @Singleton
    fun providesDatabase(context: Context) =
        Room.databaseBuilder(context, WeatherDB::class.java, DB_NAME)
            .createFromAsset("weatherus.db")
            .addCallback(object : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            // 3
            db.execSQL("INSERT INTO cities_fts(cities_fts) VALUES ('rebuild')")
        }
    }).build()

}