package com.example.myapplication
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BarScreen() {
    Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Bar")
            Spacer(modifier = Modifier.height(16.dp))
            BarTable()
        }
    }
}

@Composable
fun BarTable() {
    LazyColumn {
        item {
            Row(modifier = Modifier.fillMaxWidth()) {
                TableItem("Przekąska")
                Spacer(modifier = Modifier.weight(1f))
                TableItem("Cena")
            }
        }
        // Static data for demonstration purposes
        val snackData = listOf(
            SnackEntry("Popcorn", "25.00"),
            SnackEntry("Nachos", "26.00"),
            SnackEntry("Sok", "8.00"),
            SnackEntry("Cola", "6.00"),
        )
        items(snackData.size) { index ->
            val snack = snackData[index]
            Row(modifier = Modifier.fillMaxWidth()) {
                TableItem(snack.name)
                Spacer(modifier = Modifier.weight(1f))
                TableItem(snack.price)
            }
        }
    }
}

data class SnackEntry(val name: String, val price: String)

@Composable
fun TableItem(text: String) {
    Text(
        text = text,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    )
}

