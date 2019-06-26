package com.blackstone.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class Items(
        @PrimaryKey
        val id: Int,
        val name: String,
        val categoryID: Int,
        val image: String,
        val price: Int,
        val reputation: Double,
        val countItemRep: Int
)