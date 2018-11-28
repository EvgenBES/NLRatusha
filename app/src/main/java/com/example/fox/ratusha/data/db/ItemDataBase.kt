package com.example.fox.ratusha.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.fox.ratusha.data.db.model.octal.ItemOctDAO
import com.example.fox.ratusha.data.db.model.octal.ItemOctResponce

@Database(entities = [ItemOctResponce::class], version = 1)
abstract class ItemDataBase : RoomDatabase() {

    companion object {
        val DATABASE_NAME = "octal.db"


//        fun getInstance(context: Context): ItemDataBase {
//            return Room.databaseBuilder<ItemDataBase>(context, ItemDataBase::class.java, DATABASE_NAME)
//                    .fallbackToDestructiveMigration()
//                    .build()
//        }

        @JvmStatic
        fun getInstance(context: Context): ItemDataBase {
            return Room.databaseBuilder<ItemDataBase>(context, ItemDataBase::class.java, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
        }
    }

    abstract fun getOctDAO(): ItemOctDAO
}
