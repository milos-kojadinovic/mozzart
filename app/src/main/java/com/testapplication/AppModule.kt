package com.testapplication

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.testapplication.data.DateTypeAdapter
import com.testapplication.data.Repository
import com.testapplication.data.ServiceApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Date
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val TIMEOUT = 15L

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun httpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun createApi(okHttpClient: OkHttpClient): ServiceApi {
        val gson: Gson = GsonBuilder()
            .registerTypeAdapter(Date::class.java, DateTypeAdapter())
            .create()
        return Retrofit.Builder().client(okHttpClient)
            .baseUrl("https://api.opap.gr/draws/v3.0/1100/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(ServiceApi::class.java)
    }

    @Singleton
    @Provides
    fun createRepository(api: ServiceApi): Repository {
        return Repository(apiServiceApi = api)
    }

}