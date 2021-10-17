package com.ilih.weatherus.di

import com.ilih.weatherus.data.source.db.WeatherDB
import com.ilih.weatherus.domain.boundary.CitySearcher
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Named
import javax.inject.Singleton

@Module
abstract class CitySearchModule {
    @Binds
    @WeatherScope
    abstract fun bindsCitySearcher(searcher: CitySearcherImpl): CitySearcher
}