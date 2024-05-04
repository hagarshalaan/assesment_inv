package com.flag.module.network.service

import com.flag.management.BuildConfig
import com.flag.module.network.request.UniversityApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitService {

    private var timeOutSec: Long = 20

    private val retrofit: Retrofit by lazy {
        val httpClient = OkHttpClient.Builder()
        httpClient.callTimeout(timeOutSec, TimeUnit.SECONDS)
        httpClient.readTimeout(timeOutSec, TimeUnit.SECONDS)
        httpClient.connectTimeout(timeOutSec, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(logging)
        }

        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
    }

    val apiService: UniversityApiService by lazy {
        retrofit.create(UniversityApiService::class.java)
    }
}
