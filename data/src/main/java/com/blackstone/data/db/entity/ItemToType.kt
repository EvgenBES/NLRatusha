package com.blackstone.data.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

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