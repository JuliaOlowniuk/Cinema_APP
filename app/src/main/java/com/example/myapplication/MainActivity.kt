package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                AuroraCinemaScreen()
            }
        }
    }
}

@Composable
fun AuroraCinemaScreen() {
    val backgroundColor = ContextCompat.getColor(LocalContext.current, R.color.background)
    val whiteColor = ContextCompat.getColor(LocalContext.current, R.color.white)
    val blackColor = ContextCompat.getColor(LocalContext.current, R.color.black)

    Surface(modifier = Modifier.fillMaxSize(), color = Color(backgroundColor)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 25.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CinemaHeader()
            Spacer(modifier = Modifier.weight(1f))
            CinemaButton(text = "Zaloguj się", color = whiteColor, text_color = blackColor)
            Spacer(modifier = Modifier.height(10.dp))
            CinemaButton(text = "Zarejestruj się", color = whiteColor, text_color = blackColor)
        }
    }
}

@Composable
fun CinemaHeader() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(bottom = 250.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "Cinema Logo",
            modifier = Modifier.size(230.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Aurora Cinema",
            fontSize = 40.sp
        )
    }
}


@Composable
fun CinemaButton(text: String, color: Int, text_color: Int) {
    Button(
        onClick = { },
        colors = ButtonDefaults.buttonColors(Color(color)),

        modifier = Modifier
            .size(width = 250.dp, height = 70.dp)
            .padding(bottom = 0.dp)
    ) {
        Text(text = text, fontSize = 20.sp, color = Color(text_color))
    }
}

