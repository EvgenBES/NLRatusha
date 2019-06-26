package com.blackstone.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class Category(
        @PrimaryKey
        val id: Int,
        val name: String,
        val image: String
)