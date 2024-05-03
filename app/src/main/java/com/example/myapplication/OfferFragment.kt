package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class OfferFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_offer, container, false)
        val ticketbutton = view.findViewById<Button>(R.id.button_Tickets)
        ticketbutton.setOnClickListener{
            findNavController().navigate(R.id.action_offerFragment_to_ticketFragment)
        }

        val barbutton = view.findViewById<Button>(R.id.button_Bar)
        barbutton.setOnClickListener{
            findNavController().navigate(R.id.action_offerFragment_to_barFragment)
        }


        val giftcardbutton = view.findViewById<Button>(R.id.button_GiftCards)
        giftcardbutton.setOnClickListener{
            findNavController().navigate(R.id.action_offerFragment_to_giftCardFragment)
        }
        return view
    }

}