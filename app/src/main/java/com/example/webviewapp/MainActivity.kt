package com.example.webviewapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.webkit.WebSettings
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var webView: WebView
    private lateinit var btnBluetooth: Button
    private lateinit var btnBeacon: Button
    private lateinit var btnAuth: Button

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 버튼 초기화
        btnBluetooth = findViewById(R.id.btnBluetooth)
        btnBeacon = findViewById(R.id.btnBeacon)
        btnAuth = findViewById(R.id.btnAuth)

        // 웹뷰 초기화
        webView = findViewById(R.id.webview)
        setupWebView()

        // 버튼 클릭 이벤트 설정
        setupButtonClickListeners()
    }

    private fun setupButtonClickListeners() {
        btnBluetooth.setOnClickListener {
            webView.loadUrl("https://my-webview-n49h8gmk4-mindongs-projects.vercel.app/ccc")
        }

        btnBeacon.setOnClickListener {
            webView.loadUrl("https://my-webview-n49h8gmk4-mindongs-projects.vercel.app/bbb")
        }

        btnAuth.setOnClickListener {
            webView.loadUrl("https://my-webview-n49h8gmk4-mindongs-projects.vercel.app/aaa")
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        webView.apply {
            settings.apply {
                javaScriptEnabled = true
                domStorageEnabled = true
                setSupportZoom(true)
                builtInZoomControls = true
                displayZoomControls = false
                cacheMode = WebSettings.LOAD_NO_CACHE
            }

            webViewClient = object : WebViewClient() {
                override fun onReceivedError(
                    view: WebView?,
                    request: WebResourceRequest?,
                    error: WebResourceError?
                ) {
                    super.onReceivedError(view, request, error)
                    Toast.makeText(
                        this@MainActivity,
                        "페이지 로딩 중 오류가 발생했습니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    // 페이지 로딩 완료 후 처리
                }

                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                    url?.let { view?.loadUrl(it) }
                    return true
                }
            }
        }
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
