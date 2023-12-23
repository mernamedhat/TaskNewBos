package com.example.taskbos.ui.albums

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.taskbos.repository.Repository

class AlbumsDetailsViewModel : ViewModel() {


    fun getPhotos(
        albumId: Int,

        ) = liveData {
        emit(
            Repository().getPhotos(albumId)
        )
    }
}