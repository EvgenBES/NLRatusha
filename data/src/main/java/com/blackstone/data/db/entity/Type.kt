package com.blackstone.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Evgeny Butov
 * @created 06.04.2019
 */
@Entity(tableName = "type")
class Type(
        @PrimaryKey
        val id: Int,
        val name: String
)