package com.example.myapplication
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

class OfferPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OfferPageContent()
        }
    }
}

@Composable
fun OfferPageContent() {
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
                text = "Oferta",
                style = h4,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Button(
                onClick = { /* bilety */ },
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text("Bilety")
            }

            Button(
                onClick = { /* bar */ },
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text("Bar")
            }

            Button(
                onClick = { /* rezerwacja */ },
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text("Rezerwacja")
            }

            Button(
                onClick = { /* karty podarunkowe */ },
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text("Karty Podarunkowe")
            }
        }
    }
}

