package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.graphics.Color
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.GridLayout
import android.widget.Toast
import androidx.compose.ui.graphics.toArgb
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.PrintWriter

class ReservationFragment : Fragment() {
    private lateinit var queue: RequestQueue

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_reservation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        queue = Volley.newRequestQueue(requireContext())
        val buttonChooseMovie = view.findViewById<Button>(R.id.buttonDalej)
        val seatnumberEditText = view.findViewById<EditText>(R.id.place_textinput)
        val buttonRefresh = view.findViewById<Button>(R.id.buttonRefresh)
        buttonRefresh.setOnClickListener {
            val seatnumber = seatnumberEditText.text.toString()
            saveSelectedMovieToFile(seatnumber)
            val fileName = "selected_movie.txt"
            val fileContent = readFileContent(fileName)

            val ticketDetails = parseTicketDetails(fileContent)

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
            findNavController().navigate(R.id.action_reservationFragment_self)
        }

        buttonChooseMovie.setOnClickListener {

            findNavController().navigate(R.id.action_reservationFragment_to_boughtTicketFragment)
        }


        val gridLayout: GridLayout = view.findViewById(R.id.gridLayout)



        fetchOccupiedSeats() { occupiedSeats ->
            populateCinemaSeats(gridLayout, occupiedSeats)
        }


    }

    private fun populateCinemaSeats(gridLayout: GridLayout, occupiedSeats: List<Int>) {
        val rows = 8
        val cellsInRow = 10

        for (rowIndex in 0 until rows) {
            for (cellIndex in 0 until cellsInRow) {
                val seatNumber = rowIndex * cellsInRow + cellIndex + 1
                val seatView = createSeatView()

                // Sprawdzamy, czy miejsce jest zajęte
                if (occupiedSeats.contains(seatNumber)) {
                    seatView.setBackgroundColor(Color.RED)
                } else {
                    seatView.setBackgroundColor(Color.WHITE)
                }

                gridLayout.addView(seatView)
            }
        }
    }

    private fun createSeatView(): View {
        val seatView = View(requireContext())
        val sizeInPx = 150
        val layoutParams = ViewGroup.MarginLayoutParams(sizeInPx, sizeInPx)
        layoutParams.setMargins(8, 8, 8, 8)
        seatView.layoutParams = layoutParams
        return seatView
    }

    private fun saveSelectedMovieToFile(number: String) {
        val fileName = "selected_movie.txt"
        val file = File(requireContext().filesDir, fileName)
        Log.d("SaveFile", "Saving movie details to ${file.absolutePath}")
        try {
            FileOutputStream(file, true).use { fos ->
                PrintWriter(fos).use { writer ->
                    writer.println("Seat Number: $number")
                }
            }
            Log.d("SaveFile", "Movie details saved successfully")
        } catch (e: Exception) {
            Log.e("SaveFile", "Error saving movie details", e)
        }
    }
    private fun parseTicketDetails(fileContent: String): TicketDetails {
        val lines = fileContent.split("\n")
        var movie = ""
        var seatNumber = 0
        var ticketType = ""

        for (line in lines) {
            val parts = line.split(":")
            if (parts.size == 2) {
                val key = parts[0].trim()
                val value = parts[1].trim()

                when (key) {
                    "Movie Name" -> movie = value
                    "Seat Number" -> seatNumber = value.toInt()
                    "Ticket Type" -> ticketType = value
                }
            }
        }

        return TicketDetails(movie, seatNumber, ticketType)
    }
    fun fetchOccupiedSeats(callback: (List<Int>) -> Unit) {
        val fileName = "selected_movie.txt"
        var movie = "ssss"
        val fileContent = readFileContent(fileName)
        val lines = fileContent.split("\n")
        for (line in lines) {
            val parts = line.split(":")
            if (parts.size == 2) {
                val key = parts[0].trim()
                val value = parts[1].trim()

                when (key) {
                    "Movie Name" -> movie = value
                }
            }
        }

        val url = "http://10.0.2.2:8081/sale?movie=$movie"
        Log.d("link", url)


        val requestQueue: RequestQueue = Volley.newRequestQueue(context)

        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                val occupiedSeats = mutableListOf<Int>()
                Log.d("seats", "$response")
                for (i in 0 until response.length()) {
                    val seat = response.getJSONObject(i)
                    occupiedSeats.add(seat.getInt("seatNumber"))
                }
                callback(occupiedSeats)
            },
            { error ->
                error.printStackTrace()
            }
        )

        requestQueue.add(jsonArrayRequest)
    }
    private fun readFileContent(fileName: String): String {
        return try {
            val file = File(requireContext().filesDir, fileName)
            FileInputStream(file).use { fis ->
                val bytes = ByteArray(file.length().toInt())
                fis.read(bytes)
                String(bytes)
            }
        } catch (e: IOException) {
            e.printStackTrace()
            "Error reading file"
        }
    }
    data class TicketDetails(
        val movie: String,
        val seatNumber: Int,
        val ticketType: String,
    )
}
