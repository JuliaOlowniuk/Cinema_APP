package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class RepertuarFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_repertuar, container, false)
        val buttonkontakt = view.findViewById<Button>(R.id.button_choose_movie)
        buttonkontakt.setOnClickListener{
            findNavController().navigate(R.id.action_repertuarFragment_to_reservationFragment)
        }
        return view
    }

}