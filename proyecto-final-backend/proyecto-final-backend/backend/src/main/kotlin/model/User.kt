package com.proyectofinal.backend.model

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true)
    var email: String = "",

    @Column(nullable = false)
    var password: String = "",

    @Column(nullable = true)
    var name: String? = null,

    @Column(nullable = true)
    var age: Int? = null,

    @Column(columnDefinition = "TEXT")
    var imageBase64: String? = null  // ← nombre correcto
)
