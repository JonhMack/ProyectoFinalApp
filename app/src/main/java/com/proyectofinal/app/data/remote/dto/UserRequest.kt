package com.proyectofinal.app.data.remote.dto

data class UserRequest(
    val email: String,
    val password: String? = null,
    val name: String? = null,
    val age: Int? = null,
    val profileImage: String? = null
)
