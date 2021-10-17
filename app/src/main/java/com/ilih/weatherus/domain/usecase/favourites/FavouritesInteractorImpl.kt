package com.ilih.weatherus.domain.usecase.favourites

import com.ilih.weatherus.domain.boundary.FavouriteCityStore
import com.ilih.weatherus.domain.entity.CityDto
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class FavouritesInteractorImpl
@Inject
constructor(private val favoriteStore: FavouriteCityStore) : FavouritesInteractor {

    private val favouriteSubject = PublishSubject.create<FavouriteEvent>()

    override fun getFavourites() {
        favoriteStore.getFavouriteCities()
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    favouriteSubject.onNext(FavouriteResult(it))
                },
                {
                    favouriteSubject.onNext(FavouritesError)
                }
            )
    }

    override fun deleteFavourite(cityId: Long) {
        favoriteStore.deleteFavouriteCity(cityId)
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    getFavourites()
                },
                {
                    favouriteSubject.onNext(FavouritesError)
                }
            )
    }

    override fun getObservable(): Observable<FavouriteEvent> {
        return favouriteSubject
    }
}