package com.example.taskbos.repository


import com.example.taskbos.api.ServiceBuilder
import com.example.taskbos.api.TaskBostaService
import javax.inject.Inject


//@Inject

class Repository @Inject constructor(private val api: TaskBostaService) {
    suspend fun getUser() = api.getUser()
    suspend fun getAlbums(userId: Int) = api.getAlbums(userId)
    suspend fun getPhotos(albumId: Int) = api.getPhotos(albumId)

}