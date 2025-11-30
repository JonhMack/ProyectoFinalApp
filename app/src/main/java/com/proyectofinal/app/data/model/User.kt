package com.proyectofinal.app.data

data class User(
    val id: Long = 0,
    val email: String = "",
    val password: String = "",
    val name: String? = null,
    val age: Int? = null,
    val profileImage: String? = null
)
