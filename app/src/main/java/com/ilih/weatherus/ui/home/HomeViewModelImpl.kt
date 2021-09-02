package com.ilih.weatherus.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ilih.weatherus.domain.boundary.WeatherRepo
import com.ilih.weatherus.domain.usecase.home.HomeDto
import com.ilih.weatherus.domain.usecase.home.HomeForecastResult
import com.ilih.weatherus.domain.usecase.home.HomeInteractor
import com.ilih.weatherus.domain.usecase.home.Success
import io.reactivex.disposables.Disposable
import javax.inject.Inject

sealed class LoadingStatus
object IN_PROCESS : LoadingStatus()
object DONE : LoadingStatus()

class HomeViewModelImpl
@Inject
constructor(private val interactor: HomeInteractor) : ViewModel(), HomeViewModel, HomeView.Listener {

    private val _data = MutableLiveData<HomeDto>()
    val data = _data

    private val _status = MutableLiveData<HomeForecastResult>()
    val status = _status

    private val _loadingStatus = MutableLiveData<LoadingStatus>()
    val loadingStatus = _loadingStatus

    private val disposable: Disposable

    init {
        disposable = interactor.getObservable()
            .doOnSubscribe { _loadingStatus.value = IN_PROCESS }
            .subscribe { fetched ->
                _loadingStatus.value = DONE
                when (fetched) {
                    is Success -> _data.value = fetched.homeForecast
                    is Error -> _status.value = fetched
                }
            }
    }

    override fun onRefreshClicked() {
        interactor.nextForecast()
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}