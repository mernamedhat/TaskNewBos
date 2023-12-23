package com.example.taskbos.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.taskbos.api.ServiceBuilder
import com.example.taskbos.api.TaskBostaService
import com.example.taskbos.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val Repo: Repository) : ViewModel() {


    fun getHome() = liveData {
        emit(
            Repo.getUser()
        )
    }

    fun getAlbums(
        userId: Int,

    ) = liveData {
        emit(
            Repo.getAlbums(userId)
        )
    }
}