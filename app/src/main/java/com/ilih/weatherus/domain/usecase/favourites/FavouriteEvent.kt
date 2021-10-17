package com.ilih.weatherus.domain.usecase.favourites

import com.ilih.weatherus.domain.entity.CityDto

sealed class FavouriteEvent

data class FavouriteResult(val list: ArrayList<CityDto>): FavouriteEvent()
object EmptyFavourites: FavouriteEvent()
object FavouritesError: FavouriteEvent()
