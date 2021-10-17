package com.ilih.weatherus.ui.boundary

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.ilih.weatherus.MainActivity
import com.ilih.weatherus.R
import com.ilih.weatherus.domain.boundary.Router
import com.ilih.weatherus.ui.home.NEW_CITY
import javax.inject.Inject

class RouterImpl
@Inject
constructor(
    private val activity: MainActivity
) : Router {

//    private val navController: NavController = activity.findNavController(R.id.nav_host_fragment)
    private var searchInitiator: Int = 0
    private val navHost = R.id.nav_host_fragment

    override fun navigateToHome() {
        val bundle = Bundle()
        bundle.putBoolean(NEW_CITY, true)
        activity.findNavController(navHost).navigate(R.id.navigation_home, bundle)
    }

    override fun navigateToSearch(initiator: Int) {
        searchInitiator = initiator
        activity.findNavController(navHost).navigate(R.id.navigation_search)
    }

    override fun navigateToFavourites() {
    }

    override fun getSearchInitiator(): Int {
        return searchInitiator
    }

    override fun navigateFromSearch() {
        when(searchInitiator){
            R.id.navigation_home -> navigateToHome()
        }
    }

}