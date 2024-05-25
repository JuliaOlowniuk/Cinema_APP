package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import android.widget.Toast

class RepertuarFragment : Fragment() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_repertuar, container, false)

        val webView = view.findViewById<WebView>(R.id.webview_repertuar)
        val buttonChooseMovie = view.findViewById<Button>(R.id.button_choose_movie)

        // Konfiguracja WebView
        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
        webView.addJavascriptInterface(WebAppInterface(), "Android")

        // Załaduj stronę z repertuarem
        webView.loadUrl("https://www.google.com/search?q=repertuar+filmowy")

        buttonChooseMovie.setOnClickListener {
            findNavController().navigate(R.id.action_repertuarFragment_to_reservationFragment)
        }

        return view
    }

    private inner class WebAppInterface {
        @JavascriptInterface
        fun onMovieSelected(movieTitle: String) {
            activity?.runOnUiThread {
                Toast.makeText(activity, "Selected movie: $movieTitle", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
