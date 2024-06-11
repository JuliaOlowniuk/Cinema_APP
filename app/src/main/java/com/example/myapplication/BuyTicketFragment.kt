package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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
import com.android.volley.toolbox.JsonObjectRequest
import java.io.File
import java.io.FileOutputStream
import java.io.PrintWriter

class BuyTicketFragment : Fragment() {

    private lateinit var moviesLayout: LinearLayout
    private lateinit var queue: RequestQueue
    private var selectedMovie: JSONObject? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_buy_ticket, container, false)

        val buttonChooseMovie = view.findViewById<Button>(R.id.button_choose_movie)
        moviesLayout = view.findViewById(R.id.movies_layout)
        queue = Volley.newRequestQueue(requireContext())

        buttonChooseMovie.setOnClickListener {
            if (selectedMovie != null) {
                saveSelectedMovieToFile(selectedMovie!!)
                findNavController().navigate(R.id.action_buyTicketFragment_to_reservationFragment)
                Log.d("dane", selectedMovie!!.toString())

                data class TicketDetails(
                    val movie: String,
                    val seatNumber: Int,
                    val ticketType: String
                )
                val movie = selectedMovie!!.getString("movieName")
                val seatNumber = 5
                val ticketType = "Adult"
                val ticketDetails = TicketDetails(movie, seatNumber, ticketType)

                val url = "http://10.0.2.2:8081/tickets/add"
                val ticketJson = JSONObject().apply {
                    put("movie", ticketDetails.movie)
                    put("seatNumber", ticketDetails.seatNumber)
                    put("ticketType", ticketDetails.ticketType)
                }
                val jsonObjectRequest = JsonObjectRequest(
                    Request.Method.POST, url, ticketJson,
                    { response ->
                        Toast.makeText(requireContext(), "Ticket added successfully", Toast.LENGTH_SHORT).show()
                    },
                     { error ->
                        Log.e("Volley", "Error: ${error.message}")
                        Toast.makeText(requireContext(), "Error adding ticket", Toast.LENGTH_SHORT).show()
                    }
                )

                queue.add(jsonObjectRequest)

            } else {
                Toast.makeText(requireContext(), "Wybierz film", Toast.LENGTH_SHORT).show()
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
                    Log.d("Response", "Response: $response")
                    for (i in 0 until response.length()) {
                        val movie = response.getJSONObject(i)
                        val movieName = movie.getString("movieName")
                        val showDate = movie.getString("showDate")
                        val showTime = movie.getString("showTime")
                        Log.d("dane", movieName)

                        val movieTextView = TextView(requireContext()).apply {
                            text = "$movieName - $showDate, $showTime"
                            textSize = 25f
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

    private fun saveSelectedMovieToFile(movie: JSONObject) {
        val fileName = "selected_movie.txt"
        val file = File(requireContext().filesDir, fileName)
        Log.d("SaveFile", "Saving movie details to ${file.absolutePath}")
        try {
            FileOutputStream(file, false).use { fos ->
                PrintWriter(fos).use { writer ->
                    writer.println("Movie Name: ${movie.getString("movieName")}")
                    writer.println("Show Date: ${movie.getString("showDate")}")
                    writer.println("Show Time: ${movie.getString("showTime")}")
                    writer.println("-----------------------------")
                }
            }
            Log.d("SaveFile", "Movie details saved successfully")
        } catch (e: Exception) {
            Log.e("SaveFile", "Error saving movie details", e)
        }
    }


}
