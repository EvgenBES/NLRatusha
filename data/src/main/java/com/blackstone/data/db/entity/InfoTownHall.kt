package com.blackstone.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "townHall")
data class InfoTownHall(
        @PrimaryKey
        val idTown: Int,
        val start: String,
        val finish: String,
        val url: String
)