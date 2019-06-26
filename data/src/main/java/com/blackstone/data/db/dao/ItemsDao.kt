package com.blackstone.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.blackstone.data.db.entity.Items

/**
 * @author Evgeny Butov
 * @created 09.03.2019
 */

@Dao
interface ItemsDao {

    @Query("SELECT * FROM items WHERE categoryID = :id ORDER BY name")
    suspend fun getItemsCategory(id: Int): List<Items>

    @Query("SELECT * FROM items WHERE id = :id")
    suspend fun getItem(id: Int): Items

}