package com.ilih.weatherus.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ilih.weatherus.domain.boundary.HomeEmpty
import com.ilih.weatherus.domain.boundary.HomeError
import com.ilih.weatherus.domain.boundary.HomeForecastResult
import com.ilih.weatherus.domain.boundary.HomeSuccess
import com.ilih.weatherus.domain.usecase.home.HomeDto
import com.ilih.weatherus.domain.usecase.home.HomeInteractor
import io.reactivex.disposables.Disposable
import javax.inject.Inject

sealed class LoadingStatus
object IN_PROCESS : LoadingStatus()
object DONE : LoadingStatus()

class HomeViewModelImpl
@Inject
constructor(private val interactor: HomeInteractor) : ViewModel(), HomeViewModel, HomeView.Listener {

    private val _data = MutableLiveData<HomeForecastResult>()
    val data = _data

    private val _status = MutableLiveData<LoadingStatus>()
    val status = _status

    private val disposable: Disposable

    init {
        disposable = interactor.getObservable()
            .doOnSubscribe { _status.value = IN_PROCESS }
            .subscribe { fetched ->
                when (fetched) {
                    is HomeSuccess -> {
                        _status.value = DONE
                        _data.value = fetched
                    }
                    is HomeError -> {
                        _status.value = DONE
                        _data.value = fetched
                    }
                    is HomeEmpty ->{
                        _data.value = fetched
                    }
                }
            }
        interactor.nextForecast()
    }

    override fun onRefreshClicked() {
        interactor.nextForecast()
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}