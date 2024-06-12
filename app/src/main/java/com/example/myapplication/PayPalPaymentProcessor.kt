package com.example.myapplication

import android.content.Context
import android.widget.Toast
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class PayPalPaymentProcessor(private val context: Context) {

    private lateinit var payPalService: PayPalService

    init {
        // Inicjalizacja PayPalService z danymi konta sandbox
        payPalService = PayPalService(
            "AbKQFxx6bJd0aJr1secOycWDOQ40ah0FGhxkKYI7c4fNtA22r4A6YfsObKNaEkzpqEyWV18Yb35B4yvy",
            "EFSCJ5pibKqY497C_26h-rmWg6yPgzjxBwhYls4SWiDfAHdxSj8JJx4VqQ__GvEYaFQ8MEtxF7GoOv8H"
        )
    }

    fun processPayment(selectedMovie: JSONObject?, onSuccess: () -> Unit, onFailure: () -> Unit) {
        if (selectedMovie != null) {
            payPalService.getAccessToken { accessToken ->
                if (accessToken != null) {
                    val payment = createSamplePayment(selectedMovie)
                    payPalService.createPayment(accessToken, payment) { paymentResponse ->
                        if (paymentResponse != null) {
                            onSuccess()
                        } else {
                            onFailure()
                        }
                    }
                } else {
                    onFailure()
                }
            }
        } else {
            Toast.makeText(context, "Wybierz film", Toast.LENGTH_SHORT).show()
        }
    }

    private fun createSamplePayment(selectedMovie: JSONObject): PayPalPayment {
        val amount = Amount("10.00", "USD") // Kwota płatności
        val description = "Bilet na film ${selectedMovie.getString("movieName")}" // Opis płatności

        // Tworzymy płatność PayPal
        return PayPalPayment(
            "sale",
            Payer("paypal"),
            listOf(Transaction(amount, description)),
            RedirectUrls("https://example.com/return", "https://example.com/cancel")
        )
    }
}
