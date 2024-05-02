package com.example.myapplication
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class CinemaRoomActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CinemaRoomContent()
        }
    }
}

@Composable
fun CinemaRoomContent() {
    Surface(
        color = Color.Black,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Ekran",
                color = Color.White,
                modifier = Modifier
                    .padding(8.dp)
                    .background(Color.Gray)
                    .padding(16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            CinemaSeats()
        }
    }
}

@Composable
fun CinemaSeats() {
    val selectedSeats by remember { mutableStateOf(setOf<Int>()) }

    GridForLoop(rows = 8, cellsInRow = 10) { rowIndex, cellIndex ->
        val seatNumber = rowIndex * 10 + cellIndex + 1
        val isSelected = selectedSeats.contains(seatNumber)

        Box(
            modifier = Modifier
                .size(40.dp)
                .padding(2.dp)
                .background(if (isSelected) Color.Red else Color.White, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = seatNumber.toString(),
                color = if (isSelected) Color.White else Color.Black
            )
        }
    }
}

@Composable
fun GridForLoop(rows: Int, cellsInRow: Int, content: @Composable (rowIndex: Int, cellIndex: Int) -> Unit) {
    Column {
        repeat(rows) { rowIndex ->
            Row {
                repeat(cellsInRow) { cellIndex ->
                    content(rowIndex, cellIndex)
                }
            }
        }
    }
}
