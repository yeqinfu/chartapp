/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ppandroid.app.utils.info.Utils_UserInfo
import com.ppandroid.app.utils.upgrade.UpdateManager

class AC_Welcome : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ac__welcome)
        /*把本地更新标志置为未检查*/
        UpdateManager.getUpdateManager(this@AC_Welcome).checkFlag(false)
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
        /**如果还没有检查过版本，这边进行检查版本 */
        if (!UpdateManager.getUpdateManager(this@AC_Welcome).checkFlag) {
            UpdateManager.getUpdateManager(this@AC_Welcome).checkAppUpdate(true)
        }
    }
}
