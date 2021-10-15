package com.ilih.weatherus.data.source.preference

import android.content.Context
import com.ilih.weatherus.data.source.db.WeatherDB
import com.ilih.weatherus.data.source.db.entity.toDto
import com.ilih.weatherus.domain.boundary.HomeCityStore
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.Single
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class HomeCityStoreImpl
@Inject constructor(
    context: Context,
    private val db: WeatherDB
) : HomeCityStore {

    private val PREF_NAME = "weatherus"
    private val HOME_CITY_KEY = "home_city"

    private val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    override fun getHomeCity(): Single<Long> {
        return Single.just(prefs.getLong(HOME_CITY_KEY, 524901L))
//            .flatMap { cityId ->
//                if (cityId == 0L){
//                    db.cityDao()
//                        .getByIDRx(524901L)
//                        .map { it.toDto() }
//                } else {
//                    db.cityDao()
//                        .getByIDRx(cityId)
//                        .map { it.toDto() }
//                }
//            }
    }

    override fun saveHomeCity(city: Long): Completable{
        return Completable.create {
            if (prefs.edit().putLong(HOME_CITY_KEY, city).commit())
                it.onComplete()
            else
                it.onError(Throwable("Couldn't save city ID to prefs"))
        }
//        runBlocking {
//            coroutineScope {
//                prefs.edit().putLong(HOME_CITY_KEY, city).commit()
//            }
//        }
    }
}