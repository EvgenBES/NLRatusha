package com.example.fox.ratusha.data.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

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