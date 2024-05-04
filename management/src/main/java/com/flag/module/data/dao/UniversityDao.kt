package com.flag.module.data.dao
import androidx.room.TypeConverter

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.flag.module.data.models.University

@Dao
interface UniversityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUniversities(universities: MutableList<University>)

    @Query("SELECT * FROM universities")
    suspend fun getAllUniversities(): MutableList<University>
}


