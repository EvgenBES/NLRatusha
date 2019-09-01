package com.blackstone.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.blackstone.data.db.ConstDao.TABLE_META
import java.util.*

/**
 * @author Evgeny Butov
 * @created 30.08.2019
 */
@Entity(tableName = TABLE_META)
data class Meta (
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    val updateDate: Date = Date()
)