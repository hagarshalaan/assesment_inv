package com.flag.module.data.repository

import com.flag.module.data.dao.UniversityDao
import com.flag.module.data.models.University
import com.flag.module.network.request.UniversityApiService
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class UniversityRepository @Inject constructor(
    private val universityDao: UniversityDao,
    private val universityApiService: UniversityApiService
) {

    suspend fun searchUniversities(country: String): MutableList<University> {
        return try {
            val universities = universityApiService.searchUniversities(country)
            universityDao.insertUniversities(universities)
            universities
        } catch (e: Exception) {
            universityDao.getAllUniversities()
        }
    }
}
