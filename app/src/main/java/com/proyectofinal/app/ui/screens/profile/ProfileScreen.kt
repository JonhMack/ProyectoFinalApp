package com.proyectofinal.app.ui.profile

import android.net.Uri
import android.util.Base64
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.proyectofinal.app.data.ImageRequest
import com.proyectofinal.app.data.RetrofitInstance
import com.proyectofinal.app.data.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.InputStream

@Composable
fun ProfileScreen(userId: Long, navController: NavController) {
    var user by remember { mutableStateOf<User?>(null) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current

    // Cargar datos del usuario desde backend
    LaunchedEffect(userId) {
        try {
            val response = RetrofitInstance.api.getUser(userId)
            if (response.isSuccessful) {
                user = response.body()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // Lanzador para seleccionar imagen desde galería
    val launcherGallery = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
        uri?.let { uploadImage(it, userId) }
    }

    Column(modifier = Modifier.padding(16.dp)) {

        user?.let { u ->
            // Mostrar imagen de perfil
            if (imageUri != null) {
                Image(
                    painter = rememberAsyncImagePainter(imageUri),
                    contentDescription = "Foto de perfil",
                    modifier = Modifier.size(150.dp)
                )
            } else if (u.profileImage != null) {
                Image(
                    painter = rememberAsyncImagePainter("data:image/png;base64,${u.profileImage}"),
                    contentDescription = "Foto de perfil",
                    modifier = Modifier.size(150.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text("Nombre: ${u.name ?: ""}")
            Text("Edad: ${u.age ?: ""}")
            Text("Email: ${u.email ?: ""}")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            launcherGallery.launch("image/*")
        }) {
            Text("Seleccionar imagen de galería")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            // Aquí puedes agregar la funcionalidad para tomar foto con cámara
        }) {
            Text("Tomar foto con cámara")
        }
    }
}

private fun uploadImage(uri: Uri, userId: Long) {
    CoroutineScope(Dispatchers.IO).launch {
        try {
            val inputStream: InputStream? =
                LocalContext.current.contentResolver.openInputStream(uri)
            val bytes = inputStream?.readBytes()
            val base64 = Base64.encodeToString(bytes, Base64.DEFAULT)

            val response = RetrofitInstance.api.updateProfileImage(
                id = userId,
                imageRequest = ImageRequest(imageBase64 = base64)
            )

            if (response.isSuccessful) {
                println("Imagen subida correctamente")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
