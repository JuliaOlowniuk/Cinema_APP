import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import com.example.myapplication.R

class RegulaminFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_regulamin, container, false)

        // Locate the WebView in fragment_regulamin.xml layout
        val webView = view.findViewById<WebView>(R.id.webView)

        // Load local PDF file from assets folder
        webView.loadUrl("file:///android_asset/regulamin_kina_aurora.pdf")

        return view
    }
}
