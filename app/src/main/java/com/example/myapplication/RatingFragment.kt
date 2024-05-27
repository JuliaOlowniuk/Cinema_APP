package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.annotation.SuppressLint
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class RatingFragment : Fragment() {

    private lateinit var queue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        queue = Volley.newRequestQueue(requireContext())
    }
    @SuppressLint("SetTextI18n", "InflateParams")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_rating, container, false)
        val filmContainer = view.findViewById<LinearLayout>(R.id.filmContainer)
        val url = "http://10.0.2.2:8081/user/ratings"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                try {
                    val jsonObject = JSONObject(response)
                    for (i in 1..jsonObject.length()) {
                        val filmObject = jsonObject.getJSONObject(i.toString())
                        val filmName = filmObject.getString("name")
                        val dateAdded = filmObject.getString("dateAdded")
                        val rating = filmObject.getString("rating")

                        val filmTextView = TextView(requireContext()).apply {
                            text = filmName
                            textSize = 25f
                            setPadding(0, 10, 0, 0)
                        }

                        val dateTextView = TextView(requireContext()).apply {
                            text = "Data obejrzenia: $dateAdded"
                            textSize = 20f
                            setPadding(0, 0, 0, 20)
                        }
                        val ratingTextView = TextView(requireContext()).apply {
                            text = "Ocena: $rating"
                            textSize = 20f
                            setPadding(0, 0, 0, 20)
                        }

                        filmContainer.addView(filmTextView)
                        filmContainer.addView(dateTextView)
                        filmContainer.addView(ratingTextView)
                    }
                } catch (e: Exception) {
                    Log.e("HistoryFragment", "Error parsing JSON: ${e.message}")
                }
            },
            { error ->
                Log.e("HistoryFragment", "Request error: ${error.message}")
            })

        queue.add(stringRequest)
        return view
    }

}