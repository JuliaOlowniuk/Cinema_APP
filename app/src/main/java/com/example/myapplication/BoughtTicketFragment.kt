package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.io.File
import java.io.FileInputStream
import java.io.IOException

class BoughtTicketFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bought_ticket, container, false)
        val ticketDetailsTextView: TextView = view.findViewById(R.id.ticket_details)

        val ticketDetails = readFileContent("selected_movie.txt")
        ticketDetailsTextView.text = ticketDetails

        return view
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
            "Błąd podczas odczytu pliku"
        }
    }
}
