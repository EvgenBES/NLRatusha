package com.blackstone.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.blackstone.data.db.entity.ItemForpost
import com.blackstone.domain.entity.ItemOrder
import io.reactivex.Flowable

@Dao
interface ItemForpDao {

    companion object {
        const val TABLE_NAME = "forpost"
        const val T1 = "forpost"
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(itemForpResponce: List<ItemForpost>) // (vararg student: StudentDb)(Student...student)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(itemForpResponce: ItemForpost)

    @Query("DELETE FROM $TABLE_NAME")
    fun deleteAll()

    @Query("SELECT itemId as id, $T1.name, $T1.image, countStart, countFinish, price, reputation, countItemRep FROM $TABLE_NAME LEFT JOIN items ON $T1.itemId = items.id")
    fun getAll(): Flowable<List<ItemOrder>>
}