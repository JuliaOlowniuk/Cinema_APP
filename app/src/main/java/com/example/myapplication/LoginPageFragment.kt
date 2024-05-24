package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class LoginPageFragment : Fragment() {
    private lateinit var queue: RequestQueue
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_login_page, container, false)
        val loginEditText = view.findViewById<EditText>(R.id.loginOrEmailEditText)
        val passwordEditText = view.findViewById<EditText>(R.id.passwordEditText)
        val buttonlogin = view.findViewById<Button>(R.id.loginButton)
        queue = Volley.newRequestQueue(requireContext())

        loginEditText.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        passwordEditText.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        buttonlogin.setOnClickListener{
            val login = loginEditText.text.toString()
            val haslo = passwordEditText.text.toString()

            val url = "http://10.0.2.2:8081/login"
            val credentialRequest = @SuppressLint("MissingInflatedId")
            object : StringRequest(
                Request.Method.POST, url,
                { response ->
                    try {
                        Log.d("LoginPageFragment", "Response: $response")

                        if (response == "Zalogowano pomyślnie.") {
                            findNavController().navigate(R.id.action_loginPageFragment2_to_homePageFragment)
                        } else {
                            Log.e("LoginPageFragment", "Login failed: $response")
                            val loginfailed = view.findViewById<Button>(R.id.loginfailed)
                            loginfailed.text = "Bledne haslo!"
                        }
                    } catch (e: Exception) {
                        Log.e("LoginPageFragment", "Error parsing JSON: ${e.message}")
                    }
                },
                { error ->
                    Log.e("LoginPageFragment", "Request error: ${error.message}")
                    if (error.networkResponse != null && error.networkResponse.statusCode == 401) {
                        val loginFailedButton = view.findViewById<Button>(R.id.loginfailed)
                        loginFailedButton.text = "Błędne hasło!"
                    }
                }) {
                override fun getParams(): Map<String, String> {
                    val params = HashMap<String, String>()
                    params["username"] = login
                    params["password"] = haslo
                    return params
                }
            }

            queue.add(credentialRequest)
        }

        return view
    }

}