package com.ilih.weatherus.ui.home

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ilih.weatherus.R
import com.ilih.weatherus.domain.entity.DailyForecastDto
import com.ilih.weatherus.domain.entity.HourlyForecastDto
import kotlin.math.roundToInt

class HourlyForecastAdapter(private val itemList: ArrayList<HourlyForecastDto>) :
    RecyclerView.Adapter<HourlyForecastAdapter.ViewHolder>() {

    inner class ViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val textTime: TextView = itemView.findViewById(R.id.textHourlyTime)
        private val imageWeather: ImageView = itemView.findViewById(R.id.imageHourlyWeather)
        private val textTemp: TextView = itemView.findViewById(R.id.textHourlyTemp)

        fun bind(forecast: HourlyForecastDto) {
            textTime.text = forecast.localTimestamp
            // TODO inflate icons via WeatherIconRepo
            textTemp.text = forecast.temperature.roundToInt().toString()
        }

    }

    fun updateData(newList: ArrayList<HourlyForecastDto>){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_hourly_forecast, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount() = itemList.size

}