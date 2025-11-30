package com.proyectofinal.app.data.repository

import com.proyectofinal.app.data.api.ApiClient
import com.proyectofinal.app.data.api.ApiService
import com.proyectofinal.app.data.model.*

class UserRepository {

    private val api = ApiClient.instance.create(ApiService::class.java)

    suspend fun login(email: String, password: String) =
        api.login(LoginRequest(email, password))

    suspend fun register(email: String, password: String) =
        api.register(RegisterRequest(email, password))

    suspend fun getUser(id: Long) =
        api.getUser(id)

    suspend fun updateUser(id: Long, name: String?, age: Int?, email: String?) =
        api.updateUser(id, UpdateUserRequest(name, age, email))

    suspend fun updateImage(id: Long, base64: String) =
        api.updateProfileImage(id, UpdateImageRequest(base64))
}
