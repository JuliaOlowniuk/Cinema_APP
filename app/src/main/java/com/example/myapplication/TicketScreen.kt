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
fun TicketScreen() {
    Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Bilety")
            Spacer(modifier = Modifier.height(16.dp))
            TicketTable()
        }
    }
}

@Composable
fun TicketTable() {
    LazyColumn {
        item {
            Row(modifier = Modifier.fillMaxWidth()) {
                TableCell("Rodzaj biletu")
                Spacer(modifier = Modifier.weight(1f))
                TableCell("Cena")
            }
        }
        // Static data for demonstration purposes
        val ticketData = listOf(
            TicketEntry("Normalny", "10.00"),
            TicketEntry("Ulgowy", "8.00"),
            TicketEntry("Senior", "7.00"),
            TicketEntry("Dziecięcy", "5.00"),
        )
        items(ticketData.size) { index ->
            val ticket = ticketData[index]
            Row(modifier = Modifier.fillMaxWidth()) {
                TableCell(ticket.type)
                Spacer(modifier = Modifier.weight(1f))
                TableCell(ticket.price)
            }
        }
    }
}

data class TicketEntry(val type: String, val price: String)

@Composable
fun TableCell(text: String) {
    Text(
        text = text,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    )
}