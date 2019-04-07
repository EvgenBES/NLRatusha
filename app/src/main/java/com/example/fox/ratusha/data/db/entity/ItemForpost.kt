package com.example.fox.ratusha.data.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "forpost")
data class ItemForpost(
        @PrimaryKey (autoGenerate = true)
        val id: Int,
        val itemId: Int,
        val name: String,
        val image: String,
        val countStart: Int,
        val countFinish: Int
)