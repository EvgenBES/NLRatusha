package com.blackstone.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.blackstone.data.db.entity.ConfigApp
import io.reactivex.Flowable

/**
 * @author Evgeny Butov
 * @created 18.05.2019
 */

@Dao
interface ConfigDao {

    companion object {
        const val TABLE_NAME = "config"
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(config: ConfigApp)

    @Query("SELECT * FROM $TABLE_NAME LIMIT 1")
    fun getConfig(): Flowable<ConfigApp>
}