package com.example.fox.ratusha.data.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "recipe")
data class Recipe(
        @PrimaryKey
        val id: Int,
        val item1: Int,
        val count1: Double,
        val item2: Int,
        val count2: Double,
        val item3: Int,
        val count3: Double,
        val item4: Int,
        val count4: Double,
        val item5: Int,
        val count5: Double,
        val item6: Int,
        val count6: Double,
        val item7: Int,
        val count7: Double
)