package com.example.myapplication

data class PayPalPayment(
    val intent: String,
    val payer: Payer,
    val transactions: List<Transaction>,
    val redirect_urls: RedirectUrls
)
