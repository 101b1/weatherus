package com.ilih.weatherus.ui.home

import androidx.lifecycle.LiveData
import com.ilih.weatherus.domain.boundary.HomeForecastResult

interface HomeViewModel {
    fun getData(): LiveData<HomeForecastResult>
    fun getStatus(): LiveData<LoadingStatus>
}