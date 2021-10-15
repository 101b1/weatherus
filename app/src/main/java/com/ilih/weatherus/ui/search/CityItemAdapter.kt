package com.ilih.weatherus.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ilih.weatherus.R
import com.ilih.weatherus.domain.entity.CityDto
import com.ilih.weatherus.domain.entity.DailyForecastDto
import com.ilih.weatherus.ui.common.BaseDiffUtilCallback
import com.ilih.weatherus.ui.home.DailyForecastAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class CityItemAdapter(private val cityList: ArrayList<CityDto>) :
    RecyclerView.Adapter<CityItemAdapter.ViewHolder>() {

    private val diffUtilScope = CoroutineScope(Dispatchers.Default)

    inner class ViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val cityNameTextView: TextView = itemView.findViewById(R.id.textCityName)
        private val countryNameTextView: TextView = itemView.findViewById(R.id.text)

        fun bind(city: CityDto){
            cityNameTextView.text = city.name
            countryNameTextView.text = city.countryName
        }

    }

    fun setData(newList: ArrayList<CityDto>) {
        cityList.clear()
        cityList.addAll(newList)
    }

    fun updateData(newList: ArrayList<CityDto>) {
        val diffUtilCallback = CityDiffUtilCallback(cityList, newList)
        runBlocking {
            val result = calculateDiffAsync(diffUtilCallback).await()
            setData(newList)
            result.dispatchUpdatesTo(this@CityItemAdapter)
        }
    }

    private fun calculateDiffAsync(callback: CityItemAdapter.CityDiffUtilCallback) =
        diffUtilScope.async {
            DiffUtil.calculateDiff(callback)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_daily_forecast, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cityList[position])
    }

    override fun getItemCount() = cityList.size

    inner class CityDiffUtilCallback(
        oldList: ArrayList<CityDto>,
        newList: ArrayList<CityDto>
    ) : BaseDiffUtilCallback<CityDto>(oldList, newList)

}