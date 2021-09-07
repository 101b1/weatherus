package com.ilih.weatherus

import android.app.Application
import android.util.Log
import com.ilih.weatherus.di.AppComponent
import io.reactivex.plugins.RxJavaPlugins

const val LOG_TAG = "Weatherus"

fun Any.log(message: String){
    Log.d(LOG_TAG, "[${this.javaClass.name}]: $message")
}

open class App: Application() {

    companion object{
        private var appComponent: AppComponent? = null
    }

    override fun onCreate() {
        super.onCreate()
        RxJavaPlugins.setErrorHandler { e -> {} }
        getAppComponent().inject(this)
    }

    open fun getAppComponent(): AppComponent{
        return appComponent ?: AppComponent.create(applicationContext).also{
            appComponent = it
        }
    }
}