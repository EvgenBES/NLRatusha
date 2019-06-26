package com.blackstone.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe")
data class Recipe(
        @PrimaryKey (autoGenerate = true)
        val id: Int,
        val recipe: Int,
        val resource: Int,
        val number: Double
)