package com.ilih.weatherus.domain.boundary

interface Router {
    fun navigateToHome(cityId: Long = 0L)
    fun navigateToSearch(initiator: Int)
    fun getSearchInitiator(): Int
    fun navigateToFavourites()
    fun navigateFromSearch()
}