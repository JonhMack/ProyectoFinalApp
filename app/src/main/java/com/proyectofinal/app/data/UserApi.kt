package com.proyectofinal.app.data

import com.proyectofinal.app.model.User
import retrofit2.Response
import retrofit2.http.*

interface UserApi {

    @POST("users/register")
    suspend fun register(@Body user: User): Response<User>

    @POST("users/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<User>

    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: Long): Response<User>

    @PUT("users/{id}")
    suspend fun updateUser(@Path("id") id: Long, @Body user: User): Response<User>

    @PUT("users/{id}/profile-image")
    suspend fun updateProfileImage(@Path("id") id: Long, @Body imageRequest: ImageRequest): Response<User>
}

data class LoginRequest(
    val email: String,
    val password: String
)

data class ImageRequest(
    val imageBase64: String
)
