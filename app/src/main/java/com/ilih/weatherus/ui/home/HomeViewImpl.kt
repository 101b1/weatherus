package com.ilih.weatherus.ui.home

import android.content.Context
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ilih.weatherus.R
import com.ilih.weatherus.databinding.FragmentHomeBinding
import com.ilih.weatherus.di.AppComponent
import com.ilih.weatherus.domain.boundary.HomeCache
import com.ilih.weatherus.domain.boundary.HomeError
import com.ilih.weatherus.domain.boundary.HomeSuccess
import com.ilih.weatherus.log
import com.ilih.weatherus.utils.TimeUtils
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class HomeViewImpl(
    private val binding: FragmentHomeBinding,
    private val viewModel: HomeViewModel,
    private val lifecycleOwner: LifecycleOwner,
    private val context: Context
) : HomeView {

    lateinit var listener: HomeView.Listener

    override fun onFinishInflate(listener: HomeView.Listener, targetCity: Long) {
        this.listener = listener
        viewModel.setTargetCity(targetCity)
        initViews()
        setListeners()
        listener.inflated()
    }

    override fun initViews() {
        viewModel.getData().observe(lifecycleOwner) {
            when (it){
                is HomeCache -> {
                    if (binding.recyclerHomeDaily.adapter == null) {
                        binding.recyclerHomeDaily.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                        binding.recyclerHomeDaily.adapter = DailyForecastAdapter(it.homeForecast.dailyForecast)
                    } else {
                        (binding.recyclerHomeDaily.adapter as DailyForecastAdapter).updateData(it.homeForecast.dailyForecast)
                    }
//                    if (binding.recyclerHomeHourly.adapter == null){
//                        binding.recyclerHomeHourly.adapter = HourlyForecastAdapter(it.homeForecast.hourlyForecast)
//                    } else {
//                        (binding.recyclerHomeHourly.adapter as HourlyForecastAdapter).updateData(it.homeForecast.hourlyForecast)
//                    }
                    try {
                        val h = context.getString(R.string.humidity_value, it.homeForecast.currentWeather.humidity)
                        this.log(h)
                    } catch (e: Exception){
                        this.log(e.stackTraceToString())
                    }

                    it.homeForecast.currentWeather.apply {
                        binding.textHomeCity.text = cityName
                        binding.textHomeTemp.text = context.getString(R.string.temperature_value, temperature.roundToInt())
                        binding.textHomeWeather.text = weatherDesc
                        binding.textHomeHum.text = humidity
                        binding.textHomePressure.text = context.getString(R.string.pressure_value,(pressure*0.75).roundToInt())
                        binding.textHomeWind.text = context.getString(R.string.windspeed_value,windSpeed.roundToInt())
                        binding.textHomeUpdated.text = SimpleDateFormat(TimeUtils.FULL_DATETIME_PATTERN)
                            .format(Date(TimeUtils.expandTimestamp(timestamp)))
                    }
                }
                is HomeSuccess -> {
                    if (binding.recyclerHomeDaily.adapter == null) {
                        binding.recyclerHomeDaily.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                        binding.recyclerHomeDaily.adapter = DailyForecastAdapter(it.homeForecast.dailyForecast)
                    } else {
                        (binding.recyclerHomeDaily.adapter as DailyForecastAdapter).updateData(it.homeForecast.dailyForecast)
                    }
//                    if (binding.recyclerHomeHourly.adapter == null){
//                        binding.recyclerHomeHourly.adapter = HourlyForecastAdapter(it.homeForecast.hourlyForecast)
//                    } else {
//                        (binding.recyclerHomeHourly.adapter as HourlyForecastAdapter).updateData(it.homeForecast.hourlyForecast)
//                    }
                    val h = context.getString(R.string.humidity_value, it.homeForecast.currentWeather.humidity)
                    it.homeForecast.currentWeather.apply {
                        binding.textHomeCity.text = cityName
                        binding.textHomeTemp.text = context.getString(R.string.temperature_value, temperature.roundToInt())
                        binding.textHomeWeather.text = weatherDesc
                        binding.textHomeHum.text = context.getString(R.string.humidity_value, humidity)
                        binding.textHomePressure.text = context.getString(R.string.pressure_value,(pressure*0.75).roundToInt())
                        binding.textHomeWind.text = context.getString(R.string.windspeed_value,windSpeed.roundToInt())
                        binding.textHomeUpdated.text = SimpleDateFormat(TimeUtils.FULL_DATETIME_PATTERN)
                            .format(Date(TimeUtils.expandTimestamp(timestamp)))
                    }
                }
                is HomeError -> {
                    Toast.makeText(context, context.getString(R.string.data_download_error), Toast.LENGTH_SHORT).show()
                }
            }
        }
        viewModel.getStatus().observe(lifecycleOwner) {
            when (it) {
                is DONE -> {
                    binding.imageHomeRefresh.clearAnimation()
                    binding.imageHomeRefresh.isClickable = true
                }
                is IN_PROCESS -> {
                    val animation =
                        AnimationUtils.loadAnimation(context, R.anim.anim_rotate)
                    binding.imageHomeRefresh.isClickable = false
                    binding.imageHomeRefresh.startAnimation(animation)
                }
            }
        }
    }

    override fun setListeners() {
        binding.imageHomeRefresh.setOnClickListener {
            listener.onRefreshClicked()
        }
    }

    override fun refresh() {
//        val animation =
//            AnimationUtils.loadAnimation(context, R.anim.anim_rotate)
//        binding.imageHomeRefresh.isClickable = false
//        binding.imageHomeRefresh.startAnimation(animation)
        listener.onRefreshClicked()
    }
}