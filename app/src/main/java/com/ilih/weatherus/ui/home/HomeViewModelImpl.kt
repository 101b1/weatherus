package com.ilih.weatherus.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ilih.weatherus.domain.boundary.*
import com.ilih.weatherus.domain.usecase.home.HomeInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
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

    private var disposable: Disposable? = null
    private var targetCity = 0L

    init {
//        disposable = interactor.getObservable()
//            .subscribeOn(Schedulers.io())
//            .doOnSubscribe {
//                _status.value = IN_PROCESS
//                interactor.nextForecast(targetCity)
//            }
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe { fetched ->
//                when (fetched) {
//                    is HomeSuccess -> {
//                        _status.value = DONE
//                        _data.value = fetched
//                    }
//                    is HomeCache -> {
//                        _data.value = fetched
//                    }
//                    is HomeError -> {
//                        _status.value = DONE
//                        _data.value = fetched
//                    }
//                    is HomeEmpty ->{
//                        _data.value = fetched
//                    }
//                }
//            }
    }

    override fun onRefreshClicked() {
        interactor.nextForecast(targetCity)
        _status.value = IN_PROCESS
    }

    override fun inflated() {
        disposable = interactor.getObservable()
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {
                _status.value = IN_PROCESS
                interactor.nextForecast(targetCity)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { fetched ->
                when (fetched) {
                    is HomeSuccess -> {
                        _status.value = DONE
                        _data.value = fetched
                    }
                    is HomeCache -> {
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
//        interactor.nextForecast(targetCity)
        _status.value = IN_PROCESS
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }

    override fun getListener(): HomeView.Listener {
        return this
    }

    override fun getData(): LiveData<HomeForecastResult> {
        return data
    }

    override fun getStatus(): LiveData<LoadingStatus> {
        return status
    }

    override fun setTargetCity(cityID: Long) {
        targetCity = cityID
    }
}