package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class UserProfileFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_user_profile, container, false)
        val buttonloggout = view.findViewById<Button>(R.id.buttonLogout)
        buttonloggout.setOnClickListener{
            findNavController().navigate(R.id.action_userProfileFragment2_to_loginPageFragment2)
        }
        return view
    }

}