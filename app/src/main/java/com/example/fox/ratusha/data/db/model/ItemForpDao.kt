package com.example.fox.ratusha.data.db.model

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface ItemForpDao {

    @Insert
    fun insert(itemForpResponce: ItemForpost)

    @Query("DELETE FROM forpost")
    fun deleteAll()
}