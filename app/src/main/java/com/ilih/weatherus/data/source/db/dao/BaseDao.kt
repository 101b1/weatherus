package com.ilih.weatherus.data.source.db.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

interface BaseDao<T> {

    @Insert
    fun insert(arg: T)

    @Update
    fun update(arg: T)

    @Delete
    fun delete(arg: T)

}