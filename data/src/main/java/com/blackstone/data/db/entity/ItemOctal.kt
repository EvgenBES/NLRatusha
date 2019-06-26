package com.blackstone.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "octal")
data class ItemOctal(
        @PrimaryKey (autoGenerate = true)
        val id: Int,
        val itemId: Int,
        val name: String,
        val image: String,
        val countStart: Int,
        val countFinish: Int
)