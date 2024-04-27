import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.Button
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

class RegisterPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RegisterPageContent()
        }
    }
}

@Composable
fun RegisterPageContent() {
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
                text = "Rejestracja",
                style = h4,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            var firstName by remember { mutableStateOf("") }
            var lastName by remember { mutableStateOf("") }
            var email by remember { mutableStateOf("") }
            var phoneNumber by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }

            TextField(
                value = firstName,
                onValueChange = { firstName = it },
                label = { Text("Imię") },
                modifier = Modifier.padding(bottom = 16.dp),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text)
            )

            TextField(
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text("Nazwisko") },
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

            TextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = { Text("Numer telefonu") },
                modifier = Modifier.padding(bottom = 16.dp),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone)
            )

            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Hasło") },
                modifier = Modifier.padding(bottom = 16.dp),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)
            )

            Button(
                onClick = { /* rejestracja */ },
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text("Zarejestruj się")
            }
        }
    }
}
