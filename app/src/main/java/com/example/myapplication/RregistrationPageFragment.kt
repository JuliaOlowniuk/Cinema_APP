package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class RregistrationPageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_rregistration_page, container, false)
        val buttonregister = view.findViewById<Button>(R.id.registerButton)
        buttonregister.setOnClickListener{
            findNavController().navigate(R.id.action_rregistrationPageFragment_to_homePageFragment)
        }
        return view
    }

}