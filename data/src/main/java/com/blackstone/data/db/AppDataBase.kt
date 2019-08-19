package com.blackstone.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.blackstone.data.db.dao.*
import com.blackstone.data.db.entity.*

@Database(
    entities = [
        ItemForpost::class,
        ItemOctal::class,
        InfoTownHall::class,
        Category::class,
        Items::class,
        Recipe::class,
        Resources::class,
        ItemToType::class,
        Type::class,
        ConfigApp::class], version = 1
)
abstract class AppDataBase : RoomDatabase() {

    companion object {
        private const val DATABASE_NAME = "db_ratusha"

        fun getInstance(context: Context): AppDataBase {
            return Room
                    .databaseBuilder(context, AppDataBase::class.java, DATABASE_NAME)
                    .createFromAsset("database/db_ratusha.db")
                    .build()

//            return RoomAssetHelper.databaseBuilder(context, AppDataBase::class.java, DATABASE_NAME)
////                    .fallbackToDestructiveMigration() //if version was edit - deleted bd and created new
//                    .build()
        }
    }

    abstract fun getForpDao(): ItemForpDao
    abstract fun getOctDao(): ItemOctDao
    abstract fun getTownHallDao(): TownHallDao
    abstract fun getCategoryDao(): CategoryDao
    abstract fun getItemsDao(): ItemsDao
    abstract fun getRecipeDao(): RecipeDao
    abstract fun getConfigDao():ConfigDao
}
