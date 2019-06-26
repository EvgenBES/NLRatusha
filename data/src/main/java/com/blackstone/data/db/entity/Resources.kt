package com.blackstone.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "resources")
data class Resources(
        @PrimaryKey
        val id: Int,
        val name: String,
        val image: String,
        val price: Int
)