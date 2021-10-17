package com.ilih.weatherus.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ilih.weatherus.R
import com.ilih.weatherus.domain.boundary.Router
import com.ilih.weatherus.domain.entity.CityDto
import com.ilih.weatherus.domain.usecase.search.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchViewModelImpl
@Inject
constructor(
    private val interactor: SearchInteractor,
    private val router: Router
    ) : ViewModel(), SearchViewModel,
    SearchView.Listener {

    private val _data = MutableLiveData<SearchEvent>()
    val data = _data

    private val disposable: Disposable

    init {
        disposable = interactor.getSearchResultObservable()
            .subscribeOn(Schedulers.io())
            .doOnSubscribe{
                _data.value = EmptySearch
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _data.value = it
            }, {
                _data.value = it.message?.let { error ->
                    SearchError(error)
                } ?: SearchError("Search error")
            }
            )
    }

    override fun searchData(): LiveData<SearchEvent> = data

    override fun getViewListener(): SearchView.Listener {
        return this
    }

    override fun onCityClicked(city: CityDto) {
        when (router.getSearchInitiator()){
            R.id.navigation_home ->{
                interactor.homeCityChosen(city.id)
                router.navigateFromSearch()
            }
        }
//        interactor.homeCityChosen(city.id)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}