package com.example.myapplication
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

class HomePage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                AuroraCinemaScreenContent()
            }
        }
    }
}

@Composable
fun AuroraCinemaScreenContent() {
    val backgroundColor = Color(R.color.background)
    val logoSize = 100.dp // zmniejszamy rozmiar logo
    val whiteColor = Color(R.color.white)
    val blackColor = Color(R.color.black)

    Surface(modifier = Modifier.fillMaxSize(), color = backgroundColor) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 25.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { /* Akcja po kliknięciu */ },
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp)
                ) {
                    Text(
                        text = "Regulamin",
                        color = Color.White, // Zmiana koloru tekstu na biały
                        fontSize = 12.sp
                    )
                }

                Button(
                    onClick = { /* Akcja po kliknięciu */ },
                    modifier = Modifier.padding(end = 16.dp, top = 16.dp)
                ) {
                    Text(
                        text = "PL/ENG",
                        color = Color.White, // Zmiana koloru tekstu na biały
                        fontSize = 12.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            CinemaHeaderContent(logoSize)
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Regulamin",
                    fontSize = 12.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
                )
                Text(
                    text = "Kontakt",
                    fontSize = 12.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(end = 8.dp, bottom = 8.dp)
                )
            }
        }
    }
}

@Composable
fun CinemaHeaderContent(logoSize: Dp) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(bottom = 250.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "Cinema Logo",
            modifier = Modifier.size(logoSize)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Aurora Cinema",
            fontSize = 40.sp,
            color = Color.White
        )
    }
}
