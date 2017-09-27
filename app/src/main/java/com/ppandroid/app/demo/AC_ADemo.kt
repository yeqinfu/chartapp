/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.demo

import android.support.v4.app.Fragment
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import com.ppandroid.app.R
import com.ppandroid.app.home.mine.energyanalysis.horizontalanalysis.AC_HorChart

class AC_ADemo : AC_HorChart() {
    override fun getTopDrawable(i: Int): Int= R.drawable.zuzt

    override fun init() {
    }

    override fun getTitlePage(i: Int): String="haha"

    override fun getFragmentPage(i: Int): Fragment=FG_Demo01()

    override fun getCountPage(): Int=3

   /* override fun onCreate(savedInstanceState: Bundle?) {
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
    }*/

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
