package com.example.fox.ratusha.data.db.model.octal

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface ItemOctDao {

    @Insert
    fun insert(itemOctResponce: ItemOctResponce)

    @Query("DELETE FROM octal")
    abstract fun deleteAll()
}