package com.ilih.weatherus.di

import androidx.navigation.NavController
import com.ilih.weatherus.MainActivity
import com.ilih.weatherus.domain.boundary.Router
import com.ilih.weatherus.ui.boundary.RouterImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RouterModule(
    private val activity: MainActivity
) {
    @Provides
    @WeatherScope
    fun providesRouter(): Router = RouterImpl(activity)
}