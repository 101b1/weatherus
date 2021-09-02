package com.ilih.weatherus.di

import android.content.Context
import com.ilih.weatherus.MainActivity
import com.ilih.weatherus.ui.home.HomeViewImpl
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ContextModule::class])
@Singleton
interface ContextComponent {

    companion object{
        fun create(context: Context): ContextComponent{
            return DaggerContextComponent.builder().contextModule(ContextModule(context)).build()
        }
    }

    fun getContext(): Context

    fun inject(activity: MainActivity)
}