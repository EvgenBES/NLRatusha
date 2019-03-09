package com.example.fox.ratusha.data.db.requests

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.fox.ratusha.data.db.entity.ItemForpost
import io.reactivex.Flowable

@Dao
interface ItemForpDao {

    companion object {
        const val TABLE_NAME = "forpost"
    }

    @Insert
    fun insert(itemForpResponce: List<ItemForpost>) // (vararg student: StudentDb)(Student...student)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(itemForpResponce: ItemForpost)

    @Query("DELETE FROM $TABLE_NAME")
    fun deleteAll()

    @Query("SELECT * FROM $TABLE_NAME")
    fun getAll(): Flowable<List<ItemForpost>>
}