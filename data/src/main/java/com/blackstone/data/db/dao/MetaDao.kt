package com.blackstone.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.blackstone.data.db.ConstDao.TABLE_META
import com.blackstone.data.db.entity.Meta
import com.blackstone.domain.entity.MetaInfo

/**
 * @author Evgeny Butov
 * @created 18.05.2019
 */

@Dao
interface MetaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(metaInfo: Meta)

    @Query("SELECT updateDate FROM $TABLE_META")
    fun getMeta(): LiveData<MetaInfo>
}