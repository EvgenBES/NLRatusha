package com.example.fox.ratusha.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.fox.ratusha.data.db.entity.*
import com.example.fox.ratusha.data.db.requests.*

@Database(entities = [ItemForpost::class, ItemOctal::class, InfoTownHall::class,
                      Category::class, Items::class, Recipe::class, Resurs::class  ], version = 2)
abstract class AppDataBase : RoomDatabase() {

    companion object {
     private const val DATABASE_NAME = "db_ratusha"

        fun getInstance(context: Context): AppDataBase {
            return RoomAssetHelper.databaseBuilder(context, AppDataBase::class.java, DATABASE_NAME)
//                    .fallbackToDestructiveMigration() //if version was edit - deleted bd and created new
                    .build()
        }
    }

    abstract fun getForpDao(): ItemForpDao
    abstract fun getOctDao(): ItemOctDao
    abstract fun getTownHallDao(): TownHallDao
    abstract fun getCategoryDao(): CategoryDao
    abstract fun getItemsDao(): ItemsDao
}
