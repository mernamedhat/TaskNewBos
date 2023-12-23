package com.example.taskbos.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.taskbos.repository.Repository


class ProfileViewModel : ViewModel() {
//    private val _userData = MutableLiveData<UserResponse>()
//    val userData: LiveData<UserResponse> get() = _userData
//
//    fun getUserData() {
//        viewModelScope.launch {
//            val user = repository.getUser()
//            _userData.postValue(user)
//        }
//    }

    fun getHome() = liveData {
        emit(
            Repository().getUser()
        )
    }

    fun getAlbums(
        userId: Int,

    ) = liveData {
        emit(
            Repository().getAlbums(userId)
        )
    }
}