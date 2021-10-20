package com.ilih.weatherus.ui.favourites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ilih.weatherus.domain.boundary.HomeForecastResult
import com.ilih.weatherus.domain.boundary.Router
import com.ilih.weatherus.domain.entity.CityDto
import com.ilih.weatherus.domain.usecase.favourites.EmptyFavourites
import com.ilih.weatherus.domain.usecase.favourites.FavouriteEvent
import com.ilih.weatherus.domain.usecase.favourites.FavouriteResult
import com.ilih.weatherus.domain.usecase.favourites.FavouritesInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FavouritesViewModelImpl
@Inject constructor(private val interactor: FavouritesInteractor, private val router: Router) :
    ViewModel(), FavouritesViewModel, FavouritesView.Listener {

    private val _data = MutableLiveData<FavouriteEvent>()
    val data = _data
    private var disposable: Disposable? = null



    override fun getData(): LiveData<FavouriteEvent> {
        return data
    }

    override fun getViewListener(): FavouritesView.Listener {
        return this
    }

    override fun onFavouriteClicked(city: CityDto) {
        router.navigateToHome(city.id)
    }

    override fun inflated() {
        disposable = interactor.getObservable()
            .doOnSubscribe {
                interactor.getFavourites()
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                _data.value = it
            }
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}