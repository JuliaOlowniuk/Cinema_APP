package com.example.myapplication
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

class UserProfilePage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UserProfilePageContent()
        }
    }
}

@Composable
fun UserProfilePageContent() {
    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val h4 = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                letterSpacing = 0.15.sp,
            )

            Text(
                text = "Profil użytkownika",
                style = h4,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            var login by remember { mutableStateOf("") }
            var email by remember { mutableStateOf("") }

            TextField(
                value = login,
                onValueChange = { login = it },
                label = { Text("Login") },
                modifier = Modifier.padding(bottom = 16.dp),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text)
            )

            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.padding(bottom = 16.dp),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email)
            )

            Row(
                modifier = Modifier.padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Punkty: ",
                    style = TextStyle(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = "100", // Tutaj dynamicznie pobrana ilość punktów użytkownika
                    style = TextStyle(fontWeight = FontWeight.Bold),
                )
            }

            Button(
                onClick = { /* historia */ },
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text("Historia")
            }

            Button(
                onClick = { /* ocena */ },
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text("Ocena")
            }
        }
    }
}
