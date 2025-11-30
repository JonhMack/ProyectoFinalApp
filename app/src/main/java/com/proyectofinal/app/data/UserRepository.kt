package com.proyectofinal.app.data

import com.proyectofinal.app.data.remote.RetrofitClient
import com.proyectofinal.app.data.remote.dto.UserRequest

class UserRepository {

    private val api = RetrofitClient.api

    suspend fun login(email: String, password: String) =
        api.login(UserRequest(email = email, password = password))

    suspend fun register(email: String, password: String, name: String?, age: Int?) =
        api.register(UserRequest(
            email = email,
            password = password,
            name = name,
            age = age
        ))

    suspend fun getUser(id: Long) = api.getUser(id)
}
