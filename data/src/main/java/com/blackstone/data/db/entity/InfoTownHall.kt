package com.blackstone.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.blackstone.data.db.ConstDao.TABLE_TOWNHALL

@Entity(tableName = TABLE_TOWNHALL)
data class InfoTownHall(
        @PrimaryKey
        val idTown: Int,
        val start: String,
        val finish: String,
        val url: String
)