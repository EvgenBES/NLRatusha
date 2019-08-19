package com.blackstone.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.blackstone.data.db.entity.ConfigApp
import com.blackstone.domain.entity.Config

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

    @Query("SELECT tpForpost, tpOctal, statusForpost, statusOctal  FROM $TABLE_NAME LIMIT 1")
    fun getConfig(): LiveData<Config>
}