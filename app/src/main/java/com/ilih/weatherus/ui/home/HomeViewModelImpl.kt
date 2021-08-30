package com.ilih.weatherus.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ilih.weatherus.domain.boundary.WeatherRepo
import com.ilih.weatherus.domain.usecase.home.HomeDto
import com.ilih.weatherus.domain.usecase.home.HomeForecastResult
import com.ilih.weatherus.domain.usecase.home.HomeInteractor
import com.ilih.weatherus.domain.usecase.home.Success

sealed class LoadingStatus
object IN_PROCESS: LoadingStatus()
object DONE: LoadingStatus()

class HomeViewModel(interactor: HomeInteractor) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private val _data = MutableLiveData<HomeDto>()
    val data = _data

    private val _status = MutableLiveData<HomeForecastResult>()
    val status = _status

    private val _loadingStatus = MutableLiveData<LoadingStatus>()
    val loadingStatus = _loadingStatus

    init {
        interactor.getHomeDto()
            .doOnSubscribe{_loadingStatus.value = IN_PROCESS}
            .subscribe { fetched ->
                _loadingStatus.value = DONE
            when(fetched){
                is Success -> _data.value = fetched.homeForecast
                is Error -> _status.value = fetched
            }
        }
    }


}