package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class RregistrationPageFragment : Fragment() {

    private lateinit var queue: RequestQueue

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_rregistration_page, container, false)

        queue = Volley.newRequestQueue(requireContext())

        val firstNameEditText = view.findViewById<EditText>(R.id.firstNameEditText)
        val lastNameEditText = view.findViewById<EditText>(R.id.lastNameEditText)
        val emailEditText = view.findViewById<EditText>(R.id.emailEditText)
        val phoneNumberEditText = view.findViewById<EditText>(R.id.phoneNumberEditText)
        val passwordEditText = view.findViewById<EditText>(R.id.passwordEditText)
        val confirmPasswordEditText = view.findViewById<EditText>(R.id.confirmpasswordEditText)


        val registerButton = view.findViewById<Button>(R.id.registerButton)

        registerButton.setOnClickListener {
            val firstName = firstNameEditText.text.toString()
            val lastName = lastNameEditText.text.toString()
            val email = emailEditText.text.toString()
            val phoneNumber = phoneNumberEditText.text.toString()
            val password = passwordEditText.text.toString()
            val confirmPassword = confirmPasswordEditText.text.toString()

            val url = "http://10.0.2.2:8081/register"

            val registrationRequest = object : StringRequest(
                Request.Method.POST, url,
                { response ->
                    Log.d("RregistrationPageFragment", "Response: $response")
                    if (response == "Użytkownik zarejestrowany pomyślnie.") {
                        findNavController().navigate(R.id.action_rregistrationPageFragment_to_homePageFragment)
                    } else {
                        val registerFailed = view.findViewById<TextView>(R.id.registerfailed)
                        registerFailed.text = "nieudana rejestracja"
                    }
                },
                { error ->
                    Log.e("RregistrationPageFragment", "Request error: ${error.message}")
                    val registerFailed = view.findViewById<TextView>(R.id.registerfailed)
                    registerFailed.text = "Nieudana rejestracja!"
                }) {

                override fun getParams(): Map<String, String> {
                    val params = HashMap<String, String>()
                    params["email"] = email
                    params["password"] = password
                    params["confirmPassword"] = confirmPassword
                    params["imie"] = firstName
                    params["nazwisko"] = lastName
                    params["nr_tel"] = phoneNumber
                    return params
                }
            }

            queue.add(registrationRequest)
        }

        return view
    }
}
