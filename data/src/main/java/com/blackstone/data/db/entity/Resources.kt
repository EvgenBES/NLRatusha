package com.blackstone.data.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "resources")
data class Resources(
        @PrimaryKey
        val id: Int,
        val name: String,
        val image: String,
        val price: Int
)