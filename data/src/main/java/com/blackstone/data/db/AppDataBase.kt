package com.blackstone.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.blackstone.data.db.converters.DateConverters
import com.blackstone.data.db.dao.*
import com.blackstone.data.db.entity.*
import com.blackstone.data.db.entity.Meta

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
        Meta::class
    ], version = 2, exportSchema = false
)
@TypeConverters(value = [DateConverters::class])
abstract class AppDataBase : RoomDatabase() {

    companion object {
        private const val DATABASE_NAME = "db_ratusha"
        private const val DATABASE_FILE_PATH = "database/db_ratusha.db"

        fun getInstance(context: Context): AppDataBase {
            return Room
                .databaseBuilder(context, AppDataBase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .createFromAsset(DATABASE_FILE_PATH)
                .build()
        }
    }

    abstract fun getForpDao(): ItemForpDao
    abstract fun getOctDao(): ItemOctDao
    abstract fun getTownHallDao(): TownHallDao
    abstract fun getCategoryDao(): CategoryDao
    abstract fun getItemsDao(): ItemsDao
    abstract fun getRecipeDao(): RecipeDao
    abstract fun getMetaDao():MetaDao
}
