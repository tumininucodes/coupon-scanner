package coupon.scanen

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.*
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ScanActivity : AppCompatActivity() {

    lateinit var webView: WebView
    lateinit var progressBar: ProgressBar
    lateinit var scanAgain: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)

        scanAgain = findViewById(R.id.scanAgain)

        scanAgain.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        val url = intent.getStringExtra(SCANNER)

        webView = findViewById(R.id.wbView)
        progressBar = findViewById(R.id.progressBar)

        val webSettings = webView.settings
        if (url != null) {
            webView.loadUrl(url)
        }
        webSettings.javaScriptEnabled = true
        webView.webViewClient = WebClient()

    }

    inner class WebClient : WebViewClient() {

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)

            progressBar.visibility = View.GONE
        }


        override fun onReceivedError(
            view: WebView?,
            request: WebResourceRequest?,
            error: WebResourceError?
        ) {
            super.onReceivedError(view, request, error)

            Toast.makeText(
                this@ScanActivity,
                "Something went wrong, connect to the internet and try again",
                Toast.LENGTH_LONG
            )
                .show()

        }

        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
//            return super.shouldOverrideUrlLoading(view, url)
            if (url != null) {
                view?.loadUrl(url)
            }
            return true
        }

    }
}