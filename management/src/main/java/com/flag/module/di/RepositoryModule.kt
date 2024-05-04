package com.flag.module.di

import com.flag.module.data.dao.UniversityDao
import com.flag.module.data.repository.UniversityRepository
import com.flag.module.network.request.UniversityApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideUniversityRepository(
        api: UniversityApiService,
        universityDao: UniversityDao
    ) = UniversityRepository(universityDao,api)

}