package com.ilih.weatherus.di

import com.ilih.weatherus.data.source.preference.HomeCityStoreImpl
import com.ilih.weatherus.domain.boundary.HomeCityStore
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
abstract class PreferenceModule{

    @Binds
    @Reusable
    abstract fun bindsHomeCityStore(homeCityStore: HomeCityStoreImpl): HomeCityStore
}