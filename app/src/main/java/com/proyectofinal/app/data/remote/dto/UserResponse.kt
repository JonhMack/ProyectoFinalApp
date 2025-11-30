package com.proyectofinal.app.data.remote.dto

data class UserResponse(
    val id: Long,
    val email: String,
    val name: String?,
    val age: Int?,
    val profileImage: String?
)
