package com.blackstone.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Evgeny Butov
 * @created 06.04.2019
 */
@Entity(tableName = "item_to_type")
data class ItemToType (
    @PrimaryKey
    val id: Int,
    val type_id: Int
)