package com.proyectofinal.app.data.api

import com.proyectofinal.app.data.model.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    // Registro
    @POST("/auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<User?>

    // Login
    @POST("/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<User?>

    // Obtener usuario por ID
    @GET("/users/{id}")
    suspend fun getUser(@Path("id") id: Long): Response<User?>

    // Actualizar datos
    @PUT("/users/{id}")
    suspend fun updateUser(
        @Path("id") id: Long,
        @Body request: UpdateUserRequest
    ): Response<User?>

    // Actualizar foto
    @PUT("/users/{id}/profile-image")
    suspend fun updateProfileImage(
        @Path("id") id: Long,
        @Body request: UpdateImageRequest
    ): Response<User?>
}
