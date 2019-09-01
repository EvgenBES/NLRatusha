package com.blackstone.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.blackstone.data.db.ConstDao.TABLE_FORPOST

@Entity(tableName = TABLE_FORPOST)
data class ItemForpost(
        @PrimaryKey (autoGenerate = true)
        val id: Int,
        val itemId: Int,
        val name: String,
        val image: String,
        val countStart: Int,
        val countFinish: Int
)