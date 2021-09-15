package com.ilih.weatherus

import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.ilih.weatherus.di.AppComponent
import com.ilih.weatherus.di.ContextComponent
import com.ilih.weatherus.di.ContextModule

class MainActivity : AppCompatActivity() {

    companion object {
        private var contextComponent: ContextComponent? = null
    }

    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getContextComponent().inject(this)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when(destination.id){
                R.id.navigation_home -> {
                    menu?.let {
                        it.getItem(0).setVisible(true)
                        it.getItem(1).setVisible(false)
                        it.getItem(2).setVisible(false)
                    }
                }
            }
        }

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    fun getContextComponent(): ContextComponent {
        return MainActivity.contextComponent ?: ContextComponent.create(this).also {
            MainActivity.contextComponent = it
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu, menu)
        val search = menu?.findItem(R.id.menu_search)
        search?.setOnMenuItemClickListener {
            val navController = findNavController(R.id.nav_host_fragment)
            navController.currentDestination
            return@setOnMenuItemClickListener true
        }
        val searchView = search?.actionView as SearchView
        searchView.queryHint = getString(R.string.city_name)

//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(p0: String?): Boolean {
//
//            }
//
//            override fun onQueryTextChange(p0: String?): Boolean {
//                return false
//            }
//
//        })

        return super.onCreateOptionsMenu(menu)
    }
}