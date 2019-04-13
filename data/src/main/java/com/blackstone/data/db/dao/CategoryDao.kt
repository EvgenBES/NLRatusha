package com.blackstone.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.blackstone.data.db.entity.Category
import io.reactivex.Flowable

/**
 * @author Evgeny Butov
 * @created 08.03.2019
 */

@Dao
interface CategoryDao {

    @Query("SELECT * FROM category")
    fun getAll(): Flowable<List<Category>>

}