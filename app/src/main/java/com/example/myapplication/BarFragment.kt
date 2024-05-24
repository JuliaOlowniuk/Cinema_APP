package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class BarFragment : Fragment() {

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
        val view = inflater.inflate(R.layout.fragment_bar, container, false)
        val popcornPriceTextView = view.findViewById<TextView>(R.id.popcorn_price)
        val nachosPriceTextView = view.findViewById<TextView>(R.id.nachos_price)
        val sokPriceTextView = view.findViewById<TextView>(R.id.sok_price)
        val colaPriceTextView = view.findViewById<TextView>(R.id.cola_price)
        val url = "http://10.0.2.2:8081/prices/bar"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                try {
                    val jsonObject = JSONObject(response)
                    val pricepopcorn = jsonObject.getString("popcorn")
                    val pricenachos = jsonObject.getString("nachos")
                    val pricecola = jsonObject.getString("cola")
                    val pricesok = jsonObject.getString("sok")
                    popcornPriceTextView.text = pricepopcorn
                    nachosPriceTextView.text = pricenachos
                    colaPriceTextView.text = pricecola
                    sokPriceTextView.text = pricesok
                } catch (e: Exception) {
                    Log.e("BarFragment", "Error parsing JSON: ${e.message}")
                    popcornPriceTextView.text = "Error parsing JSON!"
                }
            },
            { error ->
                Log.e("BarFragment", "Request error: ${error.message}")
                popcornPriceTextView.text = "That didn't work!"
            })

        queue.add(stringRequest)
        return view
    }
}
