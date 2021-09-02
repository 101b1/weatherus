package com.ilih.weatherus.di

import android.content.Context
import com.ilih.weatherus.ui.home.HomeViewImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton

@Module
class ContextModule(
    private val context: Context
) {

    @Provides
    @Singleton
    fun providesAppContext() = context

}