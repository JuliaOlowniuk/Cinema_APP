package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import android.graphics.Color
import android.widget.Toast
import com.android.volley.toolbox.StringRequest
import java.io.File
import java.io.FileOutputStream
import java.io.PrintWriter

class BuyTicketFragment : Fragment() {

    private lateinit var moviesLayout: LinearLayout
    private lateinit var queue: RequestQueue
    private var selectedMovie: JSONObject? = null
    private lateinit var payPalPaymentProcessor: PayPalPaymentProcessor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_buy_ticket, container, false)

        val buttonChooseMovie = view.findViewById<Button>(R.id.button_choose_movie)
        val buttonpaypal = view.findViewById<Button>(R.id.button_pay_with_paypal)
        val typeEditText = view.findViewById<EditText>(R.id.editText_movie_input)
        moviesLayout = view.findViewById(R.id.movies_layout)
        queue = Volley.newRequestQueue(requireContext())
        payPalPaymentProcessor = PayPalPaymentProcessor(requireContext())

        buttonpaypal.setOnClickListener {
            if (selectedMovie != null) {
                // Procesowanie płatności PayPal
                payPalPaymentProcessor.processPayment(selectedMovie,
                    onSuccess = {
                        // Wyświetlenie komunikatu o udanej płatności
                        Toast.makeText(requireContext(), "Płatność przebiegła pomyślnie", Toast.LENGTH_SHORT).show()
                    },
                    onFailure = {
                        // Obsługa niepowodzenia płatności PayPal
                        Log.e("PayPal", "Payment failed")
                        Toast.makeText(requireContext(), "Payment failed", Toast.LENGTH_SHORT).show()
                    })
            } else {
                Toast.makeText(requireContext(), "Wybierz film", Toast.LENGTH_SHORT).show()
            }
        }

        buttonChooseMovie.setOnClickListener {
            if (selectedMovie != null) {
                val type = typeEditText.text.toString()
                saveSelectedMovieToFile(selectedMovie!!, type)
                // Dodawanie punktów użytkownika
                val url_ = "http://10.0.2.2:8081/user/points/add"
                val stringRequest = StringRequest(
                    Request.Method.GET, url_,
                    { response ->
                        Log.d("Response", "Response: $response")
                        Toast.makeText(
                            requireContext(),
                            "Added 10 points to the current user",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    { error ->
                        // Obsługa błędu
                        Log.e("Volley", "Error: ${error.message}")
                        Toast.makeText(
                            requireContext(),
                            "Error adding points",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                )
                queue.add(stringRequest)
                // Przekierowanie do fragmentu rezerwacji po kliknięciu przycisku "Wybierz film"
                findNavController().navigate(R.id.action_buyTicketFragment_to_reservationFragment)
            } else {
                // Wyświetlanie komunikatu o wyborze filmu
                Toast.makeText(requireContext(), "Wybierz film i dokonaj płatności", Toast.LENGTH_SHORT).show()
            }
        }

        fetchMovies()

        return view
    }

    @SuppressLint("SetTextI18n")
    private fun fetchMovies() {
        val url = "http://10.0.2.2:8081/shows/repertuar"
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                try {
                    for (i in 0 until response.length()) {
                        val movie = response.getJSONObject(i)
                        val movieName = movie.getString("movieName")
                        val showDate = movie.getString("showDate")
                        val showTime = movie.getString("showTime")

                        val movieTextView = TextView(requireContext()).apply {
                            text = "$movieName - $showDate, $showTime"
                            textSize = 20f
                            setPadding(0, 10, 0, 0)
                        }

                        val chooseButton = Button(requireContext()).apply {
                            text = "Wybierz"
                            setOnClickListener {
                                selectedMovie = movie
                                for (index in 0 until moviesLayout.childCount) {
                                    val childView = moviesLayout.getChildAt(index)

                                    if (childView is Button) {
                                        if (childView != this) {
                                            childView.setBackgroundColor(Color.WHITE)
                                        } else {
                                            childView.setBackgroundColor(Color.GREEN)
                                        }
                                    }
                                }
                            }
                        }

                        moviesLayout.addView(movieTextView)
                        moviesLayout.addView(chooseButton)
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            { error ->
                error.printStackTrace()
            })

        queue.add(jsonArrayRequest)
    }

    private fun saveSelectedMovieToFile(movie: JSONObject, type: String) {
        val fileName = "selected_movie.txt"
        val file = File(requireContext().filesDir, fileName)
        Log.d("SaveFile", "Saving movie details to ${file.absolutePath}")
        try {
            FileOutputStream(file, false).use { fos ->
                PrintWriter(fos).use { writer ->
                    writer.println("Movie Name: ${movie.getString("movieName")}")
                    writer.println("Show Date: ${movie.getString("showDate")}")
                    writer.println("Show Time: ${movie.getString("showTime")}")
                    writer.println("Ticket Type: $type")
                    writer.println("-----------------------------")
                }
            }
            Log.d("SaveFile", "Movie details saved successfully")
        } catch (e: Exception) {
            Log.e("SaveFile", "Error saving movie details", e)
        }
    }
}
