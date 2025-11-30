package com.proyectofinal.backend.controller

import com.proyectofinal.backend.model.User
import com.proyectofinal.backend.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = ["*"])
class UserController(private val userService: UserService) {

    // =====================
    //   REGISTRO
    // =====================
    @PostMapping("/auth/register")
    fun register(@RequestBody request: RegisterRequest): User? {
        return userService.register(request.email, request.password)
    }

    // =====================
    //   LOGIN
    // =====================
    @PostMapping("/auth/login")
    fun login(@RequestBody request: LoginRequest): User? {
        return userService.login(request.email, request.password)
    }

    // =====================
    //   OBTENER USUARIO POR ID
    // =====================
    @GetMapping("/users/{id}")
    fun getUser(@PathVariable id: Long): User? {
        return userService.getUser(id)
    }

    // =====================
    //   ACTUALIZAR DATOS (nombre, edad, email)
    // =====================
    @PutMapping("/users/{id}")
    fun updateUser(
        @PathVariable id: Long,
        @RequestBody request: UpdateUserRequest
    ): User? {
        return userService.updateUser(id, request.name, request.age, request.email)
    }

    // =====================
    //   SUBIR IMAGEN BASE64
    // =====================
    @PostMapping("/users/{id}/image")
    fun uploadImage(
        @PathVariable id: Long,
        @RequestBody request: ImageRequest
    ): User? {
        return userService.updateProfileImage(id, request.imageBase64)
    }

}

// DTOs (Data Transfer Objects)

data class RegisterRequest(
    val email: String,
    val password: String
)

data class LoginRequest(
    val email: String,
    val password: String
)

data class UpdateUserRequest(
    val name: String?,
    val age: Int?,
    val email: String?
)

data class ImageRequest(
    val imageBase64: String
)
