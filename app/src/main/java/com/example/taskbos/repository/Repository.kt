package com.example.taskbos.repository


import com.example.taskbos.api.ServiceBuilder
import com.example.taskbos.api.TaskBostaService


//@Inject
class Repository  constructor(private val retrofit: TaskBostaService = ServiceBuilder.buildService()) {
    suspend fun getUser() = retrofit.getUser()
    suspend fun getAlbums(userId: Int) = retrofit.getAlbums(userId)
    suspend fun getPhotos(albumId: Int) = retrofit.getPhotos(albumId)

}