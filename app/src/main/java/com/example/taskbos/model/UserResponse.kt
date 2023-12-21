package com.example.taskbos.model

data class UserResponse( val id: Long,
                         val name: String,
                         val username: String,
                         val email: String,
                         val address: Address,
                         val phone: String,
                         val website: String,
                        )
