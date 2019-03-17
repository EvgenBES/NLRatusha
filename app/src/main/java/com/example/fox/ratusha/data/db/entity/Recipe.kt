package com.example.fox.ratusha.data.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "recipe")
data class Recipe(
        @PrimaryKey (autoGenerate = true)
        val id: Int,
        val recipe: Int,
        val resource: Int,
        val number: Double
)