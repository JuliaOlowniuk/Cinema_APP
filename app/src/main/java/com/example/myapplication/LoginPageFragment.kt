package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class LoginPageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_login_page, container, false)
        val buttonlogin = view.findViewById<Button>(R.id.loginButton)
        buttonlogin.setOnClickListener{
            findNavController().navigate(R.id.action_loginPageFragment2_to_homePageFragment)
        }
        return view
    }

}