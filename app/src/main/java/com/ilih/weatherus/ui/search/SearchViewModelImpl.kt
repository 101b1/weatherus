package com.ilih.weatherus.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ilih.weatherus.domain.entity.CityDto
import com.ilih.weatherus.domain.usecase.search.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchViewModelImpl
@Inject
constructor(private val interactor: SearchInteractor) : ViewModel(), SearchViewModel,
    SearchView.Listener {

    private val _data = MutableLiveData<SearchEvent>()
    val data = _data

    private val disposable: Disposable

    init {
        disposable = interactor.getSearchResultObservable()
            .subscribeOn(Schedulers.io())
            .doOnSubscribe{
                _data.value = SearchLoading
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
        interactor.homeCityChosen(city.id)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}