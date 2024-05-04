package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController



class MainActivityFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_main_activity, container, false)
        val buttonlogin = view.findViewById<Button>(R.id.loginbutton)
        buttonlogin.setOnClickListener{
            findNavController().navigate(R.id.action_MainActivityFragment_to_loginPageFragment2)
        }
        val buttonregister = view.findViewById<Button>(R.id.registerbutton)
        buttonregister.setOnClickListener{
            findNavController().navigate(R.id.action_MainActivityFragment_to_rregistrationPageFragment)
        }
        return view
    }

}