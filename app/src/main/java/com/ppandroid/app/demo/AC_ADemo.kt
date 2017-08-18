package com.ppandroid.app.demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import com.ppandroid.app.R
import org.jetbrains.anko.find

class AC_ADemo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ac__ademo)

        var url="file:///android_asset/dd.html"
        var web=find<WebView>(R.id.webview)
        val settings = web.getSettings()
        settings.javaScriptEnabled = true// 设置支持js
        settings.domStorageEnabled = true
        val appCachePath = applicationContext.cacheDir.absolutePath
        settings.setAppCachePath(appCachePath)
        settings.allowFileAccess = true
        settings.setAppCacheEnabled(true)
        web.setWebViewClient(MyWebViewClient())
        web.setWebChromeClient(MyWebChromeClient())
        web.loadUrl(url)
    }

    internal inner class MyWebViewClient : WebViewClient() {



        // 拦截非超链接
        override fun onLoadResource(view: WebView, url: String) {
            super.onLoadResource(view, url)
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            Log.d("yeqinfu","AAAAAAAAAAAAAAAAAAAAA========onPageFinished")
            super.onPageFinished(view, url)
        }


    }

    internal inner class MyWebChromeClient : WebChromeClient() {
        override fun onProgressChanged(view: WebView, newProgress: Int) {
            Log.d("yeqinfu","AAAAAAAAAAAAAAAAAAAAA========newProgress"+newProgress)
        }

    }


}
