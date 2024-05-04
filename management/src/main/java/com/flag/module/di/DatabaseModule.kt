package com.flag.module.di

import android.app.Application
import androidx.room.Room.databaseBuilder
import com.flag.module.data.dao.UniversityDao
import com.flag.module.data.models.UniversityDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideUniversityDatabase(application: Application?): UniversityDatabase {
        return databaseBuilder(application!!, UniversityDatabase::class.java, "university_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideUniversityDao(database: UniversityDatabase): UniversityDao {
        return database.universityDao()
    }
}

