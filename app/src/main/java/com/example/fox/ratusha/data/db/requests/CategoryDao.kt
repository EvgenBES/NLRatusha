package com.example.fox.ratusha.data.db.requests

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.example.fox.ratusha.data.db.entity.Category
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