package com.ilih.weatherus.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ilih.weatherus.R
import com.ilih.weatherus.domain.entity.DailyForecastDto
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.roundToInt

class DailyForecastAdapter(private val itemList: ArrayList<DailyForecastDto>) :
    RecyclerView.Adapter<DailyForecastAdapter.ViewHolder>() {

    inner class ViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val textDay: TextView = itemView.findViewById(R.id.textDayForecast)
        private val imageWeather: ImageView = itemView.findViewById(R.id.imageDailyWeather)
        private val textHighTemp: TextView = itemView.findViewById(R.id.textDailyHighTemp)
        private val textLowTemp: TextView = itemView.findViewById(R.id.textDailyLowTemp)

        fun bind(forecast: DailyForecastDto) {
            forecast.apply {
                textDay.text = SimpleDateFormat("EEEE").format(Date(timestamp))
                // TODO inflate weather icons via WeatherIconRepo
                textHighTemp.text = maxTemp.roundToInt().toString()
                textLowTemp.text = minTemp.roundToInt().toString()
            }
        }
    }

    fun updateData(newList: ArrayList<DailyForecastDto>){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_daily_forecast, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount() = itemList.size

}