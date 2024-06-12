package com.example.myapplication

import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.sandbox.paypal.com"
    private const val CLIENT_ID = "AbKQFxx6bJd0aJr1secOycWDOQ40ah0FGhxkKYI7c4fNtA22r4A6YfsObKNaEkzpqEyWV18Yb35B4yvy"
    private const val CLIENT_SECRET = "EFSCJ5pibKqY497C_26h-rmWg6yPgzjxBwhYls4SWiDfAHdxSj8JJx4VqQ__GvEYaFQ8MEtxF7GoOv8H"

    private val client = OkHttpClient.Builder().addInterceptor { chain ->
        val original = chain.request()
        val builder = original.newBuilder()
            .header("Authorization", Credentials.basic(CLIENT_ID, CLIENT_SECRET))
        val request = builder.build()
        chain.proceed(request)
    }.build()

    val instance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: PayPalApi by lazy {
        instance.create(PayPalApi::class.java)
    }
}
