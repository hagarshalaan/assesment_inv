package com.flag.module.network.request

import com.flag.module.data.models.University
import retrofit2.http.GET
import retrofit2.http.Query

interface UniversityApiService {
    @GET("search")
    suspend fun searchUniversities(
        @Query("country") country: String
    ): MutableList<University>
}