package com.blackstone.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.blackstone.data.db.entity.Items
import io.reactivex.Flowable

/**
 * @author Evgeny Butov
 * @created 09.03.2019
 */

@Dao
interface ItemsDao {

    @Query("SELECT * FROM items WHERE categoryID = :id ORDER BY name")
    fun getItemsCategory(id: Int): Flowable<List<Items>>

    @Query("SELECT * FROM items WHERE id = :id")
    fun getItem(id: Int): Flowable<Items>

}