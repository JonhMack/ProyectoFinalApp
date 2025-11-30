package com.proyectofinal.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proyectofinal.app.data.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val repo = UserRepository()

    var state = LoginState()
        private set

    fun login(email: String, password: String, onSuccess: (Long) -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val user = repo.login(email, password)
                onSuccess(user.id)
            } catch (e: Exception) {
                onError("Credenciales incorrectas")
            }
        }
    }
}

data class LoginState(
    val email: String = "",
    val password: String = ""
)
