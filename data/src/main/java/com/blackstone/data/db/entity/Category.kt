package com.blackstone.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.blackstone.data.db.ConstDao.TABLE_CATEGORY

@Entity(tableName = TABLE_CATEGORY)
data class Category(
        @PrimaryKey
        val id: Int,
        val name: String,
        val image: String
)