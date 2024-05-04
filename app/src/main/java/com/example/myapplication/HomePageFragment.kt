package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R

class HomePageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_home_page, container, false)
        val buttonkontakt = view.findViewById<Button>(R.id.button_kontakt)
        buttonkontakt.setOnClickListener{
            findNavController().navigate(R.id.action_homePageFragment_to_contactPageFragment)
        }

        val buttonuser = view.findViewById<Button>(R.id.button_user)
        buttonuser.setOnClickListener{
            findNavController().navigate(R.id.action_homePageFragment_to_userProfileFragment2)
        }

        val buttonoffer = view.findViewById<Button>(R.id.button_offer)
        buttonoffer.setOnClickListener{
            findNavController().navigate(R.id.action_homePageFragment_to_offerFragment)
        }

        val repertuarbutton = view.findViewById<Button>(R.id.button_repertuar)
        repertuarbutton.setOnClickListener{
            findNavController().navigate(R.id.action_homePageFragment_to_repertuarFragment)
        }

        val regulaminbutton = view.findViewById<Button>(R.id.button_regulamin)
        regulaminbutton.setOnClickListener{
            findNavController().navigate(R.id.action_homePageFragment_to_regulaminFragment)
        }
        return view
    }

}