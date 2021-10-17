package com.ilih.weatherus.di

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.ilih.weatherus.MainActivity
import com.ilih.weatherus.ui.favourites.FavouritesFragment
import com.ilih.weatherus.ui.home.HomeFragment
import com.ilih.weatherus.ui.search.SearchFragment
import dagger.Component


@Component(dependencies = [AppComponent::class], modules = [WeatherModule::class, CitySearchModule::class, RouterModule::class])
@WeatherScope
interface WeatherComponent {

    companion object{

        fun create(appComponent: AppComponent, activity: MainActivity): WeatherComponent{
            return DaggerWeatherComponent.builder()
                .appComponent(appComponent)
                .routerModule(RouterModule(activity))
                .build()
        }

    }

    fun injectHomeFragment(fragment: HomeFragment)
    fun injectSearchFragment(fragment: SearchFragment)
    fun injectFavouritesFragment(fragment: FavouritesFragment)
    fun injectMainActivity(activity: MainActivity)
    //TODO Here to inject other fragments

}