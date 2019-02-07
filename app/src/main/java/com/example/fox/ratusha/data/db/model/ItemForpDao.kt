package com.example.fox.ratusha.data.db.model

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface ItemForpDao {

    @Insert
    fun insert(itemForpResponce: List<ItemForpost>) // (vararg student: StudentDb)(Student...student)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(itemForpResponce: ItemForpost)

    @Query("DELETE FROM forpost")
    fun deleteAll()
}