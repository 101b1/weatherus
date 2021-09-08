package com.ilih.weatherus.utils

object TimeUtils {

    val FULL_DATETIME_PATTERN = "dd.MM.yy HH:mm"

    fun cutTimestamp(stamp: Long): Long = stamp.toString().substring(0, 10).toLong()
    fun expandTimestamp(stamp: Long): Long = (stamp.toString() + "000").toLong()
}