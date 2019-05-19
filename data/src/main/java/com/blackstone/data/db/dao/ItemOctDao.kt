package com.blackstone.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.blackstone.data.db.entity.ItemOctal
import com.blackstone.domain.entity.ItemOrder
import io.reactivex.Flowable

@Dao
interface ItemOctDao {

    companion object {
        const val TABLE_NAME = "octal"
        const val T1 = "octal"
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(itemOctResponce: List<ItemOctal>) // (vararg student: StudentDb)(Student...student)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(itemOctResponce: ItemOctal)

    @Query("DELETE FROM $TABLE_NAME")
    fun deleteAll()

    @Query("SELECT itemId as id, $T1.name, $T1.image, countStart, countFinish, price, reputation, countItemRep FROM $TABLE_NAME LEFT JOIN items ON $T1.itemId = items.id")
    fun getAll(): Flowable<List<ItemOrder>>
}