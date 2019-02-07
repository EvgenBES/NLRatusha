package com.example.fox.ratusha.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.fox.ratusha.data.db.model.*

@Database(entities = [ItemForpost::class, ItemOctal::class, InfoTownHall::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "ratushaDB"

        fun getInstance(context: Context): AppDataBase {

            return Room
                    .databaseBuilder(context, AppDataBase::class.java, DATABASE_NAME)
                    .fallbackToDestructiveMigration() //if version was edit - deleted bd and created new
                    .build()
        }
    }

    abstract fun getForpDao(): ItemForpDao
    abstract fun getOctDao(): ItemOctDao
    abstract fun getTownHallDao(): TownHallDao
}
