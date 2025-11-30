package com.proyectofinal.app.ui.register

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.proyectofinal.app.navigation.AppNavigation
import com.proyectofinal.app.ui.viewmodel.RegisterViewModel
import com.proyectofinal.app.navigation.Screens
@Composable
fun RegisterScreen(navController: NavController, viewModel: RegisterViewModel = viewModel()) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(20.dp)) {

        Text("Registro", style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("Contraseña") })
        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Nombre") })
        OutlinedTextField(value = age, onValueChange = { age = it }, label = { Text("Edad") })

        Spacer(Modifier.height(20.dp))

        Button(
            onClick = {
                viewModel.register(
                    email, password, name, age.toIntOrNull(),
                    onSuccess = { navController.navigate(Screens.Login.route) },
                    onError = { error = it }
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Crear Cuenta")
        }

        Text(error, color = MaterialTheme.colorScheme.error)
    }
}
