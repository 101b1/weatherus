package com.ilih.weatherus.data.source.preference

import android.content.Context
import com.ilih.weatherus.domain.boundary.HomeCityStore
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class HomeCityStoreImpl
@Inject constructor(
    context: Context
) : HomeCityStore {

    private val PREF_NAME = "weatherus"
    private val HOME_CITY_KEY = "home_city"

    private val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    override fun getHomeCityID(): Long {
        return prefs.getLong(HOME_CITY_KEY, 0)
    }

    override fun saveHomeCityID(cityID: Long) {
        runBlocking {
            coroutineScope {
                prefs.edit().putLong(HOME_CITY_KEY, cityID).commit()
            }
        }
    }
}