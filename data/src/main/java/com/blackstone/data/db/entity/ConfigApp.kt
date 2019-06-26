package com.blackstone.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Evgeny Butov
 * @created 13.05.2019
 */

@Entity(tableName = "config")
data class ConfigApp(
    @PrimaryKey
    val id: Int,
    val tpForpost: Boolean = false,
    val tpOctal: Boolean = false,
    val statusForpost: Boolean = false,
    val statusOctal: Boolean = false
)
