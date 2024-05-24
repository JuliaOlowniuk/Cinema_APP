package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class TicketFragment : Fragment() {

    private lateinit var queue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        queue = Volley.newRequestQueue(requireContext())
    }
    @SuppressLint("SetTextI18n", "MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ticket, container, false)
        val normalnyPriceTextView = view.findViewById<TextView>(R.id.normalny_price)
        val ulgowyPriceTextView = view.findViewById<TextView>(R.id.ulgowy_price)
        val seniorPriceTextView = view.findViewById<TextView>(R.id.senior_price)
        val dzieciecyPriceTextView = view.findViewById<TextView>(R.id.dzieciecy_price)
        val url = "http://10.0.2.2:8081/prices/ticket"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                try {
                    val jsonObject = JSONObject(response)
                    val normalny_price = jsonObject.getString("Normalny")
                    val ulgowy_price = jsonObject.getString("Ulgowy")
                    val senior_price = jsonObject.getString("Dla seniora")
                    val dzieciecy_price = jsonObject.getString("Dzieciecy")
                    normalnyPriceTextView.text = normalny_price
                    ulgowyPriceTextView.text = ulgowy_price
                    seniorPriceTextView.text = senior_price
                    dzieciecyPriceTextView.text = dzieciecy_price
                } catch (e: Exception) {
                    Log.e("BarFragment", "Error parsing JSON: ${e.message}")
                }
            },
            { error ->
                Log.e("BarFragment", "Request error: ${error.message}")
            })

        queue.add(stringRequest)
        return view
    }

}