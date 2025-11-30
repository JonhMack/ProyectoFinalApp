package com.proyectofinal.app.ui.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.proyectofinal.app.navigation.Screens
import com.proyectofinal.app.ui.viewmodel.LoginViewModel
import com.proyectofinal.app.navigation.AppNavigation
@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = viewModel()) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Text("Login", style = MaterialTheme.typography.headlineMedium)

        Spacer(Modifier.height(20.dp))

        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("Contraseña") })

        Spacer(Modifier.height(20.dp))

        Button(
            onClick = {
                viewModel.login(
                    email, password,
                    onSuccess = { id ->
                        navController.navigate(Screens.Profile.createRoute(id))
                    },
                    onError = { error = it }
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Iniciar sesión")
        }

        Spacer(Modifier.height(20.dp))

        Text(error, color = MaterialTheme.colorScheme.error)

        TextButton(onClick = { navController.navigate(Screens.Register.route) }) {
            Text("Crear cuenta")
        }
    }
}
