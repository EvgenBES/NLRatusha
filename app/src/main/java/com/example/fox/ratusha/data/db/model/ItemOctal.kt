package com.example.fox.ratusha.data.db.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "octal")
data class ItemOctal(
        @PrimaryKey (autoGenerate = true)
        val id: Int,
        val itemId: Int,
        val name: String = " ",
        val image: String = " ",
        val countStart: Int = 0,
        val countFinish: Int = 0,
        val startOrder: String = " ",
        val finishOrder: String = " "
)