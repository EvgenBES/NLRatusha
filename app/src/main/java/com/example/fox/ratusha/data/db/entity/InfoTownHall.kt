package com.example.fox.ratusha.data.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "townHall")
data class InfoTownHall(
        @PrimaryKey
        val idTown: Int,
        val start: String = " ",
        val finish: String = " ",
        val url: String = " "
)