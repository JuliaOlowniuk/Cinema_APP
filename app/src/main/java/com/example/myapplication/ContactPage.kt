import android.os.Bundle
import android.webkit.WebView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView

class ContactPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContactPageContent()
        }
    }
}

@Composable
fun ContactPageContent() {
    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val h4 = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                letterSpacing = 0.15.sp,
            )

            Text(
                text = "Kontakt",
                style = h4,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = "ul. Filmowa 1\n04-935 Warszawa",
                style = TextStyle(fontSize = 16.sp),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "Numer telefonu kina: 123456789",
                style = TextStyle(fontSize = 16.sp),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "Numer telefonu pomocy technicznej: 987654321",
                style = TextStyle(fontSize = 16.sp),
                modifier = Modifier.padding(bottom = 32.dp)
            )

            Text(
                text = "Znajdź nas!",
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            WebViewContent(
                url = "https://www.google.com/maps/embed/v1/place?q=ul.+Filmowa+1,+04-935+Warszawa&key=YOUR_API_KEY"
            )
        }
    }
}

@Composable
fun WebViewContent(url: String) {
    var webView: WebView? by remember { mutableStateOf(null) }

    DisposableEffect(key1 = webView) {
        onDispose {
            webView?.destroy()
        }
    }

    AndroidView(
        factory = { context ->
            WebView(context).apply {
                webView = this
                loadUrl(url)
            }
        }
    )
}