package com.ilih.weatherus.di

import com.ilih.weatherus.data.repo.WeatherRepoImpl
import com.ilih.weatherus.data.source.network.WeatherAPI
import com.ilih.weatherus.domain.boundary.FavouriteCityStore
import com.ilih.weatherus.domain.boundary.WeatherRepo
import com.ilih.weatherus.domain.usecase.home.HomeInteractor
import com.ilih.weatherus.domain.usecase.home.HomeInteractorImpl
import com.ilih.weatherus.domain.usecase.search.SearchInteractor
import com.ilih.weatherus.domain.usecase.search.SearchInteractorImpl
import com.ilih.weatherus.ui.home.HomeViewModel
import com.ilih.weatherus.ui.home.HomeViewModelImpl
import com.ilih.weatherus.ui.search.SearchViewImpl
import com.ilih.weatherus.ui.search.SearchViewModel
import com.ilih.weatherus.ui.search.SearchViewModelImpl
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

    @Binds
    @Reusable
    abstract fun bindsHomeViewModel(homeViewModelImpl: HomeViewModelImpl): HomeViewModel

    @Binds
    @Reusable
    abstract fun bindsSearchViewModel(searchViewModelImpl: SearchViewModelImpl): SearchViewModel

    @Binds
    @Reusable
    abstract fun bindsHomeInteractor(homeInteractorImpl: HomeInteractorImpl): HomeInteractor

    @Binds
    @Reusable
    abstract fun bindsSearchInteractor(searchInteractorImpl: SearchInteractorImpl): SearchInteractor

    //TODO Here to bind other ModelViews and Interactors

    @Binds
    @Reusable
    abstract fun bindsWeatherRepo(weatherRepoImpl: WeatherRepoImpl): WeatherRepo

    @Binds
    @Reusable
    abstract fun bindsFavouriteCityStore(store: FavouriteCityStoreImpl): FavouriteCityStore

}