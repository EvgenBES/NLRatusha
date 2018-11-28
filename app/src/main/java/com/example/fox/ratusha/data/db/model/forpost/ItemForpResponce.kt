package com.example.fox.ratusha.data.db.model.forpost

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "forpost")
data class ItemForpResponce(
        @PrimaryKey
        val id: Int,
        val name: String?,
        val image: String?,
        val countStart: Int?,
        val countFinish: Int?,
        val startOrder: String?,
        val finishOrder: String?,
        val countItem: Int?
)