package com.ilih.weatherus.ui.boundary

import android.os.Bundle
import androidx.navigation.findNavController
import com.ilih.weatherus.MainActivity
import com.ilih.weatherus.R
import com.ilih.weatherus.domain.boundary.Router
import com.ilih.weatherus.ui.home.NEW_CITY
import com.ilih.weatherus.ui.home.TARGET_CITY
import javax.inject.Inject

class RouterImpl
@Inject
constructor(
    private val activity: MainActivity
) : Router {

//    private val navController: NavController = activity.findNavController(R.id.nav_host_fragment)
    private var searchInitiator: Int = 0
    private val navHost = R.id.nav_host_fragment

    override fun navigateToHome(cityId: Long) {
        val bundle = Bundle()
        bundle.putBoolean(NEW_CITY, true)
        if (cityId != 0L)
            bundle.putLong(TARGET_CITY, cityId)
        activity.findNavController(navHost).navigate(R.id.navigation_home, bundle)
    }

    override fun navigateToSearch(initiator: Int) {
        searchInitiator = initiator
        activity.findNavController(navHost).navigate(R.id.navigation_search)
    }

    override fun navigateToFavourites() {
        activity.findNavController(navHost).navigate(R.id.navigation_favourites)
    }

    override fun getSearchInitiator(): Int {
        return searchInitiator
    }

    override fun navigateFromSearch() {
        when(searchInitiator){
            R.id.navigation_home -> navigateToHome()
            R.id.navigation_favourites -> navigateToFavourites()
        }
    }

}