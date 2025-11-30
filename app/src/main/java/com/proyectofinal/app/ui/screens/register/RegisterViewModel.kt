package com.proyectofinal.app.ui.screens.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proyectofinal.app.data.repository.UserRepository
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    private val repo = UserRepository()

    fun register(email: String, password: String, onSuccess: () -> Unit, onError: () -> Unit) {
        viewModelScope.launch {
            val response = repo.register(email, password)

            if (response.isSuccessful && response.body() != null) {
                onSuccess()
            } else {
                onError()
            }
        }
    }
}
