package com.ilih.weatherus

import android.app.Application
import android.util.Log

const val LOG_TAG = "Weatherus"

fun Any.log(message: String){
    Log.d(LOG_TAG, "[${this.javaClass.name}]: $message")
}

class WeatherusApp: Application() {
}