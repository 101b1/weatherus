package com.ilih.weatherus.ui.home

import android.content.Context
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.ilih.weatherus.R
import com.ilih.weatherus.databinding.FragmentHomeBinding
import com.ilih.weatherus.domain.usecase.home.Error
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class HomeViewImpl(
    private val binding: FragmentHomeBinding,
    private val viewModel: HomeViewModelImpl,
    private val lifecycleOwner: LifecycleOwner,
    private val context: Context
) : HomeView {

    lateinit var listener: HomeView.Listener

    fun onFinishInflate(listener: HomeView.Listener) {
        this.listener = listener
        initViews()
        setListeners()
    }

    override fun initViews() {
        viewModel.data.observe(lifecycleOwner) {
            binding.imageHomeRefresh.clearAnimation()
            binding.imageHomeRefresh.isClickable = true
            if (binding.recyclerHomeDaily.adapter == null) {
                binding.recyclerHomeDaily.adapter = DailyForecastAdapter(it.dailyForecast)
            } else {
                (binding.recyclerHomeDaily.adapter as DailyForecastAdapter).updateData(it.dailyForecast)
            }
            if (binding.recyclerHomeHourly.adapter == null){
                binding.recyclerHomeHourly.adapter = HourlyForecastAdapter(it.hourlyForecast)
            } else {
                (binding.recyclerHomeHourly.adapter as HourlyForecastAdapter).updateData(it.hourlyForecast)
            }
            it.currentWeather.apply {
                binding.textHomeCity.text = cityName
                binding.textHomeTemp.text = temperature.roundToInt().toString()
                binding.textHomeWeather.text = weatherDesc
                binding.textHomeHum.text = humidity
                binding.textHomePressure.text = pressure.roundToInt().toString()
                binding.textHomeWind.text = windSpeed.roundToInt().toString()
                binding.textHomeUpdated.text = SimpleDateFormat("dd.MM.yy HH:mm").format(Date(timestamp))
            }
        }
        viewModel.status.observe(lifecycleOwner){
            if (it is Error){
                binding.imageHomeRefresh.clearAnimation()
                binding.imageHomeRefresh.isClickable = true
                Toast.makeText(context, it.errorMessage, Toast.LENGTH_SHORT).show()
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