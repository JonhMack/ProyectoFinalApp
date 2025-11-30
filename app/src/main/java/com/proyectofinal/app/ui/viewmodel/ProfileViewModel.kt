package com.proyectofinal.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proyectofinal.app.data.UserRepository
import com.proyectofinal.app.data.remote.dto.UserResponse
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    private val repo = UserRepository()

    var user: UserResponse? = null
        private set

    fun loadUser(id: Long) {
        viewModelScope.launch {
            user = repo.getUser(id)
        }
    }
}
