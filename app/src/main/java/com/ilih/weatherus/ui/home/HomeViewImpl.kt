package com.ilih.weatherus.ui.home

import android.content.Context
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import com.ilih.weatherus.R
import com.ilih.weatherus.databinding.FragmentHomeBinding
import com.ilih.weatherus.domain.boundary.HomeError
import com.ilih.weatherus.domain.boundary.HomeSuccess
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

    override fun onFinishInflate(listener: HomeView.Listener) {
        this.listener = listener
        initViews()
        setListeners()
        this.listener.inflated()
    }

    override fun initViews() {
        viewModel.getData().observe(lifecycleOwner) {
            when (it){
                is HomeSuccess -> {
                    if (binding.recyclerHomeDaily.adapter == null) {
                        binding.recyclerHomeDaily.adapter = DailyForecastAdapter(it.homeForecast.dailyForecast)
                    } else {
                        (binding.recyclerHomeDaily.adapter as DailyForecastAdapter).updateData(it.homeForecast.dailyForecast)
                    }
                    if (binding.recyclerHomeHourly.adapter == null){
                        binding.recyclerHomeHourly.adapter = HourlyForecastAdapter(it.homeForecast.hourlyForecast)
                    } else {
                        (binding.recyclerHomeHourly.adapter as HourlyForecastAdapter).updateData(it.homeForecast.hourlyForecast)
                    }
                    it.homeForecast.currentWeather.apply {
                        binding.textHomeCity.text = cityName
                        binding.textHomeTemp.text = temperature.roundToInt().toString()
                        binding.textHomeWeather.text = weatherDesc
                        binding.textHomeHum.text = humidity
                        binding.textHomePressure.text = pressure.roundToInt().toString()
                        binding.textHomeWind.text = windSpeed.roundToInt().toString()
                        binding.textHomeUpdated.text = SimpleDateFormat("dd.MM.yy HH:mm").format(Date(timestamp))
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
            }
        }
    }

    override fun setListeners() {
        binding.imageHomeRefresh.setOnClickListener {
            refresh()
            listener.onRefreshClicked()
        }
    }

    override fun refresh() {
        val animation =
            AnimationUtils.loadAnimation(context, R.anim.anim_rotate)
        binding.imageHomeRefresh.isClickable = false
        binding.imageHomeRefresh.startAnimation(animation)
    }
}