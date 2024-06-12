package com.example.myapplication

import com.example.myapplication.PayPalAccessToken
import com.example.myapplication.PayPalPayment
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface PayPalApi {
    @POST("v1/oauth2/token")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    fun getAccessToken(@Header("Authorization") authHeader: String, @Body body: Map<String, String>): Call<PayPalAccessToken>

    @POST("v1/payments/payment")
    fun createPayment(@Header("Authorization") authHeader: String, @Body payment: PayPalPayment): Call<PayPalPayment>
}