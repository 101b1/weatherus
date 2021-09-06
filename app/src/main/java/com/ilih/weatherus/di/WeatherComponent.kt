package com.ilih.weatherus.di

import androidx.fragment.app.Fragment
import com.ilih.weatherus.ui.home.HomeFragment
import dagger.Component


@Component(dependencies = [AppComponent::class], modules = [WeatherModule::class])
@WeatherScope
interface WeatherComponent {

    companion object{

        fun create(appComponent: AppComponent): WeatherComponent{
            return DaggerWeatherComponent.builder().appComponent(appComponent).build()
        }

    }

    fun injectHomeFragment(fragment: HomeFragment)

    //TODO Here to inject other fragments

}