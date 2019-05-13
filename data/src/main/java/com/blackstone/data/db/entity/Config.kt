package com.blackstone.data.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * @author Evgeny Butov
 * @created 13.05.2019
 */

@Entity(tableName = "config")
data class Config(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val tpForpost: Boolean = false,
    val tpOctal: Boolean = false,
    val statusForpost: Boolean = false,
    val statusOctal: Boolean = false
)
