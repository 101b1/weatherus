package com.ilih.weatherus.domain.boundary

interface Router {
    fun navigateToHome()
    fun navigateToSearch(initiator: Int)
    fun getSearchInitiator(): Int
    fun navigateToFavourites()
    fun navigateFromSearch()
}