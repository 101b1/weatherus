package com.ilih.weatherus.ui.home

import androidx.recyclerview.widget.DiffUtil
import com.ilih.weatherus.domain.entity.HourlyForecastDto

open class BaseDiffUtilCallback<T>(
    private val oldList: ArrayList<T>,
    private val newList: ArrayList<T>,
) : DiffUtil.Callback() {

    override fun getOldListSize() =
        oldList.size

    override fun getNewListSize() =
        newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].hashCode() == newList[newItemPosition].hashCode()

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem == newItem
    }
}