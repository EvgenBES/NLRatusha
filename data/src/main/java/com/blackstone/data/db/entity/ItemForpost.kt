package com.blackstone.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "forpost")
data class ItemForpost(
        @PrimaryKey (autoGenerate = true)
        val id: Int,
        val itemId: Int,
        val name: String,
        val image: String,
        val countStart: Int,
        val countFinish: Int
)