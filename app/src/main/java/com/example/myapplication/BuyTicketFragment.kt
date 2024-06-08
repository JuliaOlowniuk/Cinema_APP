package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BuyTicketFragment : Fragment() {

    private lateinit var moviesLayout: LinearLayout
    private val apiKey = "b86b2f78b9fa1efd17365a41927922e1"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_buy_ticket, container, false)
        moviesLayout = view.findViewById(R.id.movies_layout)
        val buttonChooseMovie = view.findViewById<Button>(R.id.button_choose_movie)


        buttonChooseMovie.setOnClickListener {
            findNavController().navigate(R.id.action_buyTicketFragment_to_reservationFragment)
        }

        return view
    }


}