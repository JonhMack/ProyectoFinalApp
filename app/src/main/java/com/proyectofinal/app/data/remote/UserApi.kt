package com.proyectofinal.app.data.remote

import com.proyectofinal.app.data.remote.dto.UserRequest
import com.proyectofinal.app.data.remote.dto.UserResponse
import retrofit2.http.*

interface UserApi {

    @POST("users/register")
    suspend fun register(@Body request: UserRequest): UserResponse

    @POST("users/login")
    suspend fun login(@Body request: UserRequest): UserResponse

    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: Long): UserResponse
}
