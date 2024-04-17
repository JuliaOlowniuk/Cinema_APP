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
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "auroraapp") {
                composable("auroraapp") {
                    AuroraCinemaScreen(navController)
                }
                composable("home") {
                    HomePage()
                }
            }
        }
    }
}


@Composable
fun AuroraCinemaScreen(navController: NavController) {
    val backgroundColor = Color(R.color.background)
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
            CinemaHeader()
            Spacer(modifier = Modifier.weight(1f))
            CinemaButton(
                text = "Zaloguj się",
                color = whiteColor,
                textColor = blackColor
            ) {
                navController.navigate("home")
            }

            Spacer(modifier = Modifier.height(10.dp))

            CinemaButton(
                text = "Zarejestruj się",
                color = whiteColor,
                textColor = blackColor
            ) {
                navController.navigate(R.id.action_auroraapp_to_home)
            }

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
            modifier = Modifier.size(210.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Aurora Cinema",
            fontSize = 40.sp,
            color = Color.White
        )
    }
}

@Composable
fun CinemaButton(text: String, color: Color, textColor: Color, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(contentColor = textColor),
        modifier = Modifier
            .size(width = 250.dp, height = 70.dp)
            .padding(bottom = 0.dp)
    ) {
        Text(text = text, fontSize = 20.sp)
    }
}

