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
fun GiftCardScreen() {
    Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Karty")
            Text(text = "podarunkowe")
            Text(text = "Aktywne pół roku od zakupu !")
            Spacer(modifier = Modifier.height(16.dp))
            GiftCardTable()
        }
    }
}

@Composable
fun GiftCardTable() {
    LazyColumn {
        item {
            Row(modifier = Modifier.fillMaxWidth()) {
                CardItem("Rodzaj karty")
                Spacer(modifier = Modifier.weight(1f))
                CardItem("Cena")
            }
        }
        // Static data for demonstration purposes
        val cardData = listOf(
            GiftCardEntry("Standardowa", "50.00"),
            GiftCardEntry("VIP", "100.00"),
            GiftCardEntry("Dla dzieci", "25.00"),
            GiftCardEntry("Dla seniorów", "30.00"),
        )
        items(cardData.size) { index ->
            val card = cardData[index]
            Row(modifier = Modifier.fillMaxWidth()) {
                CardItem(card.type)
                Spacer(modifier = Modifier.weight(1f))
                CardItem(card.price)
            }
        }
    }
}

data class GiftCardEntry(val type: String, val price: String)

@Composable
fun CardItem(text: String) {
    Text(
        text = text,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    )
}

