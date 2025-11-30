package com.proyectofinal.app.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proyectofinal.app.data.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val repo = UserRepository()

    var uiState = LoginUiState()
        private set

    fun login(email: String, password: String, onSuccess: (Long) -> Unit, onError: () -> Unit) {
        viewModelScope.launch {
            val response = repo.login(email, password)

            if (response.isSuccessful && response.body() != null) {
                onSuccess(response.body()!!.id!!)
            } else {
                onError()
            }
        }
    }
}

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val loading: Boolean = false
)
