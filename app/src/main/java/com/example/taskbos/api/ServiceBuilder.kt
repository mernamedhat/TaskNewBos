package com.example.taskbos.api

import com.example.taskbos.repository.Repository
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceBuilder {

    @Singleton
    @Provides
    fun buildService(): Retrofit {

        val gson = GsonBuilder()
            .setLenient()
            .create()

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS)


        val httpClient: OkHttpClient = OkHttpClient.Builder().addInterceptor(logging).build()

        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()



        return retrofit


    }
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): TaskBostaService =
        retrofit.create(TaskBostaService::class.java)



}


