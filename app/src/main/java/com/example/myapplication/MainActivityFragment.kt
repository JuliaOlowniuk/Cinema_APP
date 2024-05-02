package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R

class MainActivityFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_main_activity, container, false)
        val button = view.findViewById<Button>(R.id.loginbutton)
        button.setOnClickListener{
            findNavController().navigate(R.id.action_mainActivityFragment_to_homePageFragment)
        }
        return view
    }

}