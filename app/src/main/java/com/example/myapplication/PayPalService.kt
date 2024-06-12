package com.example.myapplication

import android.util.Log
import android.util.Base64
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PayPalService(private val clientId: String, private val clientSecret: String) {
    private val api: PayPalApi = RetrofitClient.instance.create(PayPalApi::class.java)

    fun getAccessToken(callback: (String?) -> Unit) {
        val authHeader = "Basic " + Base64.encodeToString("$clientId:$clientSecret".toByteArray(), Base64.NO_WRAP)
        val body = mapOf("grant_type" to "client_credentials")

        api.getAccessToken(authHeader, body).enqueue(object : Callback<PayPalAccessToken> {
            override fun onResponse(call: Call<PayPalAccessToken>, response: Response<PayPalAccessToken>) {
                if (response.isSuccessful) {
                    callback(response.body()?.access_token)
                } else {
                    Log.e("PayPalService", "Failed to get access token: ${response.errorBody()?.string()}")
                    callback(null)
                }
            }

            override fun onFailure(call: Call<PayPalAccessToken>, t: Throwable) {
                Log.e("PayPalService", "Failed to get access token: ${t.message}")
                callback(null)
            }
        })
    }

    fun createPayment(accessToken: String, payment: PayPalPayment, callback: (PayPalPayment?) -> Unit) {
        val authHeader = "Bearer $accessToken"

        api.createPayment(authHeader, payment).enqueue(object : Callback<PayPalPayment> {
            override fun onResponse(call: Call<PayPalPayment>, response: Response<PayPalPayment>) {
                if (response.isSuccessful) {
                    callback(response.body())
                } else {
                    Log.e("PayPalService", "Payment creation failed: ${response.errorBody()?.string()}")
                    callback(null)
                }
            }

            override fun onFailure(call: Call<PayPalPayment>, t: Throwable) {
                Log.e("PayPalService", "Payment creation failed: ${t.message}")
                callback(null)
            }
        })
    }
}
