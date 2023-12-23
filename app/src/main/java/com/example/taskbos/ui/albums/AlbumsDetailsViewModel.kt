package com.example.taskbos.ui.albums

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.taskbos.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class AlbumsDetailsViewModel  @Inject constructor(private val Repository: Repository)  : ViewModel() {


    fun getPhotos(
        albumId: Int,

        ) = liveData {
        emit(
            Repository.getPhotos(albumId)
        )
    }
}