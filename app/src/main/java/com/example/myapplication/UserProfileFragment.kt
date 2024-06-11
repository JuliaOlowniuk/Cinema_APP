package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import android.annotation.SuppressLint
import android.util.Log
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class UserProfileFragment: Fragment() {
    private lateinit
    var queue: RequestQueue
    override fun onCreate(savedInstanceState: Bundle ? ) {
        super.onCreate(savedInstanceState)
        queue = Volley.newRequestQueue(requireContext())
    }

    @SuppressLint("SetTextI18n", "MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup ? ,
        savedInstanceState : Bundle ?
    ): View ? {
        val view = inflater.inflate(R.layout.fragment_user_profile, container, false)
        val pointsTextView = view.findViewById < TextView > (R.id.textViewPoints)
        val loginTextView = view.findViewById < TextView > (R.id.editTextLogin)
        val emailTextView = view.findViewById < TextView > (R.id.editTextEmail)
        val url_points = "http://10.0.2.2:8081/user/points"
        val stringRequest = StringRequest(
            Request.Method.GET, url_points, {
                    response ->
                try {
                    val jsonObject = JSONObject(response)
                    val points = jsonObject.getString("points")

                    pointsTextView.text = points

                } catch (e: Exception) {
                    Log.e("BarFragment", "Error parsing JSON: ${e.message}")

                }
            }, {
                    error ->
                Log.e("BarFragment", "Request error: ${error.message}")
            })

        queue.add(stringRequest)
        val url_credentials = "http://10.0.2.2:8081/user/profile"
        val credentialRequest = StringRequest(
            Request.Method.GET, url_credentials, {
                    response ->
                try {
                    val jsonObject = JSONObject(response)
                    val login = jsonObject.getString("name")
                    val email = jsonObject.getString("email")

                    loginTextView.text = login
                    emailTextView.text = email

                } catch (e: Exception) {
                    Log.e("BarFragment", "Error parsing JSON: ${e.message}")

                }
            }, {
                    error ->
                Log.e("BarFragment", "Request error: ${error.message}")
            })

        queue.add(credentialRequest)
        val buttonloggout = view.findViewById < Button > (R.id.buttonLogout)
        buttonloggout.setOnClickListener {
            val url_logout = "http://10.0.2.2:8081/logout"
            val logoutRequest =  StringRequest(
                Request.Method.POST, url_logout, {
                        response ->
                    Log.d("Logout", "Response: $response")
                    if (response == "Wylogowano pomyślnie.") {
                        findNavController().navigate(R.id.action_userProfileFragment2_to_loginPageFragment2)
                    }
                    else {
                        Log.e("Logout", "Request error")
                    }
                },
                {
                        error ->
                    Log.e("Logout", "Request error: ${error.message}")
                })
            queue.add(logoutRequest)
        }

        val buttonhistory = view.findViewById < Button > (R.id.buttonHistory)
        buttonhistory.setOnClickListener {
            findNavController().navigate(R.id.action_userProfileFragment2_to_historyFragment)
        }

        return view
    }

}