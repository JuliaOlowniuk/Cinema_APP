package com.example.myapplication

data class PayPalAccessToken(
    val scope: String,
    val access_token: String,
    val token_type: String,
    val app_id: String,
    val expires_in: Int,
    val nonce: String
)
