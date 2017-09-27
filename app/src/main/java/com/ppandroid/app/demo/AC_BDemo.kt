/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import com.ppandroid.app.R
import org.jetbrains.anko.find

class AC_BDemo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ac__bdemo)

        var url="http://m.berui8.com/zbinfo/181426.html?isApp=1"
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
            Log.d("yeqinfu","BBBBBBBBBBBB========onPageFinished")
            super.onPageFinished(view, url)
        }


    }

    internal inner class MyWebChromeClient : WebChromeClient() {
        override fun onProgressChanged(view: WebView, newProgress: Int) {
            Log.d("yeqinfu","BBBBBBBBBBBB========newProgress"+newProgress)
        }

    }

}
