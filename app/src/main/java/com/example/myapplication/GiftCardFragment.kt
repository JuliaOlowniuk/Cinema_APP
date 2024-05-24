package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.annotation.SuppressLint
import android.util.Log
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject


class GiftCardFragment : Fragment() {

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
        val view = inflater.inflate(R.layout.fragment_gift_card, container, false)
        val standardPriceTextView = view.findViewById<TextView>(R.id.standardowa_price)
        val VIPPriceTextView = view.findViewById<TextView>(R.id.vip_price)
        val dladzieciPriceTextView = view.findViewById<TextView>(R.id.dladzieci_price)
        val dlaseniorowPriceTextView = view.findViewById<TextView>(R.id.dlasieniorow_price)
        val url = "http://10.0.2.2:8081/prices/giftcard"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                try {
                    val jsonObject = JSONObject(response)
                    val standardowa_price = jsonObject.getString("Standardowa")
                    val vip_price = jsonObject.getString("VIP")
                    val dladzieci_price = jsonObject.getString("Dla dzieci")
                    val dlasieniorow_price = jsonObject.getString("Dla seniorow")
                    standardPriceTextView.text = standardowa_price
                    VIPPriceTextView.text = vip_price
                    dladzieciPriceTextView.text = dladzieci_price
                    dlaseniorowPriceTextView.text = dlasieniorow_price
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