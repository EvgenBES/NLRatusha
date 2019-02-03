package com.example.fox.ratusha.data.db.model.octal

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "octal")
data class ItemOctResponce(
        @PrimaryKey
        val id: Int,
        val name: String = " ",
        val image: String = " ",
        val countStart: Int = 0,
        val countFinish: Int = 0,
        val startOrder: String = " ",
        val finishOrder: String = " "
)