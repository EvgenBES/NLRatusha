package com.blackstone.data.db.converters

import androidx.room.TypeConverter
import java.util.*

/**
 * @author Evgeny Butov
 * @created 30.08.2019
 */
class DateConverters {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date {
        return if (value == null) Date(0) else Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long {
        return date?.time ?: 0
    }
}