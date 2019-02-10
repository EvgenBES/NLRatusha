package com.example.fox.ratusha.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.fox.ratusha.data.db.entity.ItemOctal
import io.reactivex.Flowable

@Dao
interface ItemOctDao {

    companion object {
        const val TABLE_NAME = "octal"
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(itemOctResponce: ItemOctal)

    @Query("DELETE FROM $TABLE_NAME")
    fun deleteAll()

    @Query("SELECT * FROM $TABLE_NAME")
    fun getAll(): Flowable<List<ItemOctal>>
}