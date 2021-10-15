package com.ilih.weatherus.di

import com.ilih.weatherus.MainActivity
import dagger.Component

@Component(dependencies = [AppComponent::class], modules = [CitySearchModule::class])
@SearchScope
interface SearchComponent {

    companion object{

        fun create(appComponent: AppComponent): SearchComponent{
            return DaggerSearchComponent.builder().appComponent(appComponent).build()
        }

    }

    fun injectMainActivity(activity: MainActivity)
}