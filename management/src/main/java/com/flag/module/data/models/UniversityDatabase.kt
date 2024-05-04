package com.flag.module.data.models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.flag.module.data.dao.UniversityDao


@Database(entities = [University::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class UniversityDatabase : RoomDatabase() {
    abstract fun universityDao(): UniversityDao
}
class Converters {
    @TypeConverter
    fun fromStringList(value: String?): List<String>? {
        return value?.split(",")
    }

    @TypeConverter
    fun toStringList(list: List<String>?): String? {
        return list?.joinToString(",")
    }
}