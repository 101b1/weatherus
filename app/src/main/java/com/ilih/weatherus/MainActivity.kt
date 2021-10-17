package com.ilih.weatherus

import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ilih.weatherus.di.ContextComponent
import com.ilih.weatherus.di.WeatherComponent
import com.ilih.weatherus.domain.boundary.CitySearcher
import com.ilih.weatherus.domain.boundary.Router
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    companion object {
        private var contextComponent: ContextComponent? = null

        //        private var searchComponent: SearchComponentonent? = null
        private var weatherComponent: WeatherComponent? = null
    }

    @Inject
    lateinit var citySearcher: CitySearcher

    @Inject
    lateinit var router: Router

    private var mainMenu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getContextComponent()
        getWeatherComponent().injectMainActivity(this)

        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        setSupportActionBar(toolbar)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)
        val navController = findNavController(R.id.nav_host_fragment)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.navigation_home -> {
                    mainMenu?.let {
                        it.getItem(0).setVisible(true)
                        it.getItem(1).setVisible(false)
//                        it.getItem(2).setVisible(false)
                        val search = it.findItem(R.id.menu_search)
                        val searchView = search?.actionView as SearchView
                        searchView.setQuery("", false)
                        if (!searchView.isIconified)
                            searchView.isIconified = true
                    }
                }
                R.id.navigation_favourites -> {
                    mainMenu?.let {
                        it.getItem(0).setVisible(true)
                        it.getItem(1).setVisible(true)
//                        it.getItem(2).setVisible(false)
                        val search = it.findItem(R.id.menu_search)
                        val searchView = search?.actionView as SearchView
                        searchView.setQuery("", false)
                        if (!searchView.isIconified)
                            searchView.isIconified = true
                    }
                }
                R.id.navigation_search -> {
                    mainMenu?.let {
                        it.getItem(0).setVisible(true)
                        it.getItem(1).setVisible(false)
//                        it.getItem(2).setVisible(false)
                    }
                }
                else -> {
                    mainMenu?.let {
                        it.getItem(0).setVisible(true)
                        it.getItem(1).setVisible(true)
//                        it.getItem(2).setVisible(true)
                    }
                }
            }
        }

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_favourites, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setupWithNavController(navController)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu, menu)
        val search = menu?.findItem(R.id.menu_search)
        val searchView = search?.actionView as SearchView
        searchView.queryHint = getString(R.string.city_name)
        searchView.setOnSearchClickListener {
            val navController = findNavController(R.id.nav_host_fragment)
            when (navController.currentDestination?.id) {
                R.id.navigation_home -> {
                    router.navigateToSearch(R.id.navigation_home)//navController.navigate(R.id.navigation_search)
                }
                R.id.navigation_favourites -> {
                    router.navigateToSearch(R.id.navigation_favourites)
                }
            }
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                p0?.let { citySearcher.search(it) }
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })
        mainMenu = menu
        return super.onCreateOptionsMenu(menu)
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
    }

    override fun onDestroy() {
//        (application as App).getAppComponent().getHomeCityStore().closeStore()
        super.onDestroy()
    }


    fun getContextComponent(): ContextComponent {
        return contextComponent ?: ContextComponent.create(this).also {
            contextComponent = it
        }
    }

    fun getWeatherComponent(): WeatherComponent {
        return weatherComponent ?: WeatherComponent.create(
            (application as App).getAppComponent(),
            this
        )
            .also {
                weatherComponent = it
            }
    }
}