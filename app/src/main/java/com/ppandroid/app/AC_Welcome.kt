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

    }

    override fun onResume() {
        super.onResume()
    }
}
