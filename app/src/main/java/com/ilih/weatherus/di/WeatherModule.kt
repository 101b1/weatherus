package com.ilih.weatherus.di

import com.ilih.weatherus.data.repo.WeatherRepoImpl
import com.ilih.weatherus.data.source.network.WeatherAPI
import com.ilih.weatherus.domain.boundary.WeatherRepo
import com.ilih.weatherus.domain.usecase.home.HomeInteractor
import com.ilih.weatherus.domain.usecase.home.HomeInteractorImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit


@Module
abstract class WeatherModule {

    companion object{
        @JvmStatic
        @Provides
        @WeatherScope
        fun providesWeatherApiService(retrofit: Retrofit): WeatherAPI{
            return retrofit.create(WeatherAPI::class.java)
        }
    }

    //TODO Here to bind HomeModelView

    @Binds
    @Reusable
    abstract fun bindsHomeInteractor(homeInteractorImpl: HomeInteractorImpl): HomeInteractor

    //TODO Here to bind other ModelViews and Interactors

    @Binds
    @Reusable
    abstract fun bindsWeatherRepo(weatherRepoImpl: WeatherRepoImpl): WeatherRepo
}