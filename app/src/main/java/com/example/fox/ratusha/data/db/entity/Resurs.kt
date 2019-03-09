package com.example.fox.ratusha.data.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "resurs")
data class Resurs(
        @PrimaryKey
        val id: Int,
        val name: String,
        val price: Int
)