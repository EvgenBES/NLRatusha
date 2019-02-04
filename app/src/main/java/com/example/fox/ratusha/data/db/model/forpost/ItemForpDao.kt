package com.example.fox.ratusha.data.db.model.forpost

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface ItemForpDao {

    @Insert
    fun insert(itemForpResponce: ItemForpResponce)

    @Query("DELETE FROM forpost")
    fun deleteAll()
}