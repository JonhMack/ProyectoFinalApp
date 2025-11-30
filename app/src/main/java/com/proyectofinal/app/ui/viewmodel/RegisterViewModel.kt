package com.proyectofinal.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proyectofinal.app.data.UserRepository
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    private val repo = UserRepository()

    fun register(
        email: String,
        password: String,
        name: String?,
        age: Int?,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                repo.register(email, password, name, age)
                onSuccess()
            } catch (e: Exception) {
                onError("Error al registrar usuario")
            }
        }
    }
}
