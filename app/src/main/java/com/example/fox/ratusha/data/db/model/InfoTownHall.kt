package com.example.fox.ratusha.data.db.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "townHall")
data class InfoTownHall(
        @PrimaryKey (autoGenerate = true)
        val id: Int,
        val idTown: Int = 0,
        val start: String = " ",
        val finish: String = " ",
        val url: String = " "
)