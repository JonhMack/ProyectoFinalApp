package com.proyectofinal.backend.service

import com.proyectofinal.backend.model.User
import com.proyectofinal.backend.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

    fun register(email: String, password: String): User? {
        // Verificar si ya existe
        if (userRepository.findByEmail(email) != null) {
            return null
        }

        // Crear usuario base (sin nombre, edad ni foto al inicio)
        val user = User(
            email = email,
            password = password,
            name = "",
            age = 0,
            imageBase64 = null
        )

        return userRepository.save(user)
    }

    fun login(email: String, password: String): User? {
        val user = userRepository.findByEmail(email) ?: return null
        return if (user.password == password) user else null
    }

    fun updateUser(id: Long, name: String?, age: Int?, email: String?): User? {
        val user = userRepository.findById(id).orElse(null) ?: return null

        user.name = name ?: user.name
        user.age = age ?: user.age
        user.email = email ?: user.email

        return userRepository.save(user)
    }

    fun updateProfileImage(id: Long, imageBase64: String): User? {
        val user = userRepository.findById(id).orElse(null) ?: return null

        user.imageBase64 = imageBase64

        return userRepository.save(user)
    }

    fun getUser(id: Long): User? {
        return userRepository.findById(id).orElse(null)
    }
}
