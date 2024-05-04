package com.flag.module.di

import com.flag.module.network.request.UniversityApiService
import com.flag.module.network.service.RetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideApiService(): UniversityApiService {
        return RetrofitService.apiService
    }

}