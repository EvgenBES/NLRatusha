package com.blackstone.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.blackstone.data.db.ConstDao.TABLE_OCTAL

@Entity(tableName = TABLE_OCTAL)
data class ItemOctal(
        @PrimaryKey (autoGenerate = true)
        val id: Int,
        val itemId: Int,
        val name: String,
        val image: String,
        val countStart: Int,
        val countFinish: Int
)