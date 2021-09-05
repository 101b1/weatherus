package com.ilih.weatherus.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ilih.weatherus.R
import com.ilih.weatherus.domain.entity.HourlyForecastDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlin.math.roundToInt

class HourlyForecastAdapter(private val itemList: ArrayList<HourlyForecastDto>) :
    RecyclerView.Adapter<HourlyForecastAdapter.ViewHolder>() {

    private val diffUtilScope = CoroutineScope(Dispatchers.Default)

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

    private fun calculateDiffAsync(callback: HourlyDiffUtilCallback) = diffUtilScope.async {
        DiffUtil.calculateDiff(callback)
    }

    fun setData(newMessageList: ArrayList<HourlyForecastDto>) {
        itemList.clear()
        itemList.addAll(newMessageList)
    }

    fun updateData(newList: ArrayList<HourlyForecastDto>) {
        val diffUtilCallback = HourlyDiffUtilCallback(itemList, newList)
        runBlocking {
            val result = calculateDiffAsync(diffUtilCallback).await()
            setData(newList)
            result.dispatchUpdatesTo(this@HourlyForecastAdapter)
        }
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

    inner class HourlyDiffUtilCallback(
        private val oldList: ArrayList<HourlyForecastDto>,
        private val newList: ArrayList<HourlyForecastDto>,
    ) : BaseDiffUtilCallback<HourlyForecastDto>(oldList, newList)

}