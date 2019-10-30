package com.blackstone.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.blackstone.data.db.ConstDao.TABLE_FORPOST
import com.blackstone.data.db.entity.ItemForpost
import com.blackstone.data.db.entity.ItemOrderExtended

@Dao
interface ItemForpDao {

    companion object {
        const val T1 = TABLE_FORPOST
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(itemForpResponce: List<ItemForpost>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(itemForpResponce: ItemForpost)

    @Query("DELETE FROM $TABLE_FORPOST")
    fun deleteAll()

    //TODO CHANGED
    @Query("SELECT itemId as id, $T1.name, $T1.image, countStart, countFinish, price, reputation, countItemRep FROM $TABLE_FORPOST LEFT JOIN items ON $T1.itemId = items.id")
    fun getAll(): LiveData<List<ItemOrderExtended>>
}