package com.example.fox.ratusha.data.db.model

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface ItemOctDao {

    @Insert
    fun insert(itemOctResponce: ItemOctal)

    @Query("DELETE FROM octal")
    abstract fun deleteAll()
}