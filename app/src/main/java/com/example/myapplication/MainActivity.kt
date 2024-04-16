package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val grayColor = ContextCompat.getColor(this, R.color.gray)
        val buttonColor = ContextCompat.getColor(this, R.color.button_color)
        setContent {
            MyApplicationTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = Color(grayColor)) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 25.dp),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Aurora Cinema",
                            fontSize = 40.sp,
                            modifier = Modifier.padding(bottom = 250.dp)
                        )
                    }
                    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                        Button(onClick = { }, colors = ButtonDefaults.buttonColors(Color(buttonColor)),
                            modifier = Modifier.size(width = 200.dp, height = 60.dp).padding(bottom = 16.dp)) {
                            Text("Zaloguj się", fontSize = 20.sp)
                        }
                        Button(onClick = { }, colors = ButtonDefaults.buttonColors(Color(buttonColor)),
                            modifier = Modifier.size(width = 200.dp, height = 60.dp).padding(bottom = 10.dp))  {
                            Text("Zarejestruj się", fontSize = 20.sp)
                        }

                    }
                }
            }
        }
    }
}
