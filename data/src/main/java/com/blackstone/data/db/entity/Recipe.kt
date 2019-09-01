package com.blackstone.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.blackstone.data.db.ConstDao.TABLE_RECIPE

@Entity(tableName = TABLE_RECIPE)
data class Recipe(
        @PrimaryKey (autoGenerate = true)
        val id: Int,
        val recipe: Int,
        val resource: Int,
        val number: Double
)