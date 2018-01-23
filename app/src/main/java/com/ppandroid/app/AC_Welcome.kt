/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ppandroid.app.utils.info.Utils_UserInfo

class AC_Welcome : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ac__welcome)


        var it= Intent()

        if (Utils_UserInfo.isLogined(this)){
            it.setClass(this,AC_Main::class.java)
        }else{
            it.setClass(this,AC_Login::class.java)
        }
        startActivity(it)
       finish()

        var d=Intent()
        var re=d?.let{_->
            haha(it)
        }
        val list: MutableList<String> = mutableListOf("A", "B", "C")
        val change: Any
        change = with(list) {
            add("D")
            add("E")
            this.add("F")
            size
        }

    }
    fun haha(i:Intent){}
    inline fun <T, R> T.let(f: (T) -> R): R {
       return  f(this)
    }

    inline fun <T, R> with(receiver: T, f: T.() -> R): R = receiver.f()

}
