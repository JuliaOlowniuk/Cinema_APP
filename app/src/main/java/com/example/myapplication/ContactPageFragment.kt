package com.example.myapplication
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import java.net.URLEncoder
class ContactPageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_contact_page, container, false)
        val webView: WebView = rootView.findViewById(R.id.webView)

        // Włącz obsługę JavaScript w WebView
        webView.settings.javaScriptEnabled = true

        // Ustaw klienta WebView, aby strony były wyświetlane w samym WebView, a nie w przeglądarce zewnętrznej
        webView.webViewClient = WebViewClient()

        // Konstruuj URL z adresem kina
        val address = "Filmowa 1, 04-935 Warszawa"
        val encodedAddress = URLEncoder.encode(address, "UTF-8")
        val url = "https://www.google.com/maps?q=$encodedAddress"

        // Załaduj URL mapy w WebView
        webView.loadUrl(url)

        return rootView
    }
}