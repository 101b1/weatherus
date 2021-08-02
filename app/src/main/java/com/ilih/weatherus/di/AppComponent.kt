package com.ilih.weatherus.di

import android.content.Context
import com.ilih.weatherus.App
import com.ilih.weatherus.data.source.db.WeatherDB
import com.ilih.weatherus.data.source.db.dao.CurrentWeatherDao
import com.ilih.weatherus.di.AppComponent.Companion.create
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Component(modules = [RetrofitModule::class, AppContextModule::class, DatabaseModule::class])
@Singleton
interface AppComponent {

    companion object{

        fun create(appContext: Context): AppComponent{
            return DaggerAppComponent.builder().appContextModule(AppContextModule(appContext)).build()
        }

    }

    fun getRetrofit(): Retrofit

    fun getDatabase(): WeatherDB

    fun getAppContext(): Context

    fun inject(app: App)


}