package com.ilih.weatherus.data.source.db.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import io.reactivex.Completable
import io.reactivex.Single

interface BaseDao<T> {

    @Insert
    fun insert(arg: T): Long

    @Insert
    fun insertRx(arg: T): Single<Long>

    @Insert
    fun insertAll(args: List<T>)

    @Update
    fun update(arg: T): Int

    @Update
    fun updateRx(arg: T): Single<Int>

    @Delete
    fun delete(arg: T)

    @Delete
    fun deleteRx(arg: T): Completable

}