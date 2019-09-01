package com.blackstone.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.blackstone.data.db.ConstDao.TABLE_ITEMS

@Entity(tableName = TABLE_ITEMS)
data class Items(
        @PrimaryKey
        val id: Int,
        val name: String,
        val categoryID: Int,
        val image: String,
        val price: Int,
        val reputation: Double,
        val countItemRep: Int
)