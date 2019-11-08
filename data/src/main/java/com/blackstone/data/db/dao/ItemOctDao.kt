package com.blackstone.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.blackstone.data.db.ConstDao.TABLE_OCTAL
import com.blackstone.data.db.entity.ItemOctal
import com.blackstone.data.db.entity.ItemOrderExtended

@Dao
interface ItemOctDao {

    companion object {
        const val T1 = TABLE_OCTAL
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(itemOctResponce: List<ItemOctal>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(itemOctResponce: ItemOctal)

    @Query("DELETE FROM $TABLE_OCTAL")
    fun deleteAll()

    @Query("SELECT itemId as id, $T1.name, $T1.image, countStart, countFinish, price, reputation, countItemRep FROM $TABLE_OCTAL LEFT JOIN items ON $T1.itemId = items.id")
    fun getAll(): LiveData<List<ItemOrderExtended>>
}