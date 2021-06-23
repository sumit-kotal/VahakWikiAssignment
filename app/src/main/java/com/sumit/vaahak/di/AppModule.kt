package com.sumit.vaahak.di

import android.content.Context
import com.sumit.vaahak.MyApplication
import com.sumit.vaahak.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    val context: Context? = MyApplication.appContext
    private val cacheSize = (5 * 1024 * 1024).toLong()
    val myCache = context?.cacheDir?.let { Cache(it, cacheSize) }

    val okHttpClient = OkHttpClient().newBuilder().readTimeout(120, TimeUnit.SECONDS)
        .cache(myCache)
        .connectTimeout(120, TimeUnit.SECONDS)
        .addInterceptor { chain ->
            val originalRequest = chain.request()
            var builder: Request.Builder? = null
            builder = originalRequest.newBuilder()
                .header("Cache-Control", "public, max-age=" + 180)
                .header("Connection", "keep-alive")
                .header("Accept", "application/json")
            val newRequest = builder.build()
            chain.proceed(newRequest)
        }.build()

    @Provides
    @Singleton
    fun getBaseUrl(): String = "https://en.wikipedia.org/w/"

    @Provides
    @Singleton
    fun getRetrofitBuilder(baseUrl: String): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun getApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

}