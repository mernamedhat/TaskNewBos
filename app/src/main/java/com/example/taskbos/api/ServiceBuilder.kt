package com.example.taskbos.api

import com.google.gson.GsonBuilder

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//@Module
//@InstallIn(SingletonComponent::class)
object ServiceBuilder {

//    @Provides
    fun buildService(): TaskBostaService {

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



        return retrofit.create(TaskBostaService::class.java)


    }

}

