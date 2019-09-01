package com.blackstone.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.blackstone.data.db.ConstDao.TABLE_CATEGORY
import com.blackstone.data.db.entity.Category

/**
 * @author Evgeny Butov
 * @created 08.03.2019
 */

@Dao
interface CategoryDao {

    @Query("SELECT * FROM $TABLE_CATEGORY")
    suspend fun getAll(): List<Category>

}