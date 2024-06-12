package com.example.myapplication
import com.example.myapplication.PayPalApi
import com.example.myapplication.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Base64

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
                    callback(null)
                }
            }

            override fun onFailure(call: Call<PayPalAccessToken>, t: Throwable) {
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
                    callback(null)
                }
            }

            override fun onFailure(call: Call<PayPalPayment>, t: Throwable) {
                callback(null)
            }
        })
    }
}
