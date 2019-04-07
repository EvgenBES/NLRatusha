package com.example.fox.ratusha.data.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

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