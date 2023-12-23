package com.example.taskbos.api

import com.example.taskbos.model.AlbumsResponse
import com.example.taskbos.model.PhotosResponse
import com.example.taskbos.model.UserResponse
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Query


interface TaskBostaService {


    @GET("users")
    suspend fun getUser(): Response<List<UserResponse>>


    @GET("albums")
    suspend fun getAlbums(
        @Query("userId") userId: Int
    ): Response<List<AlbumsResponse>>


    @GET("photos")
    suspend fun getPhotos(
        @Query("albumId") albumId: Int
    ): Response<List<PhotosResponse>>



}
