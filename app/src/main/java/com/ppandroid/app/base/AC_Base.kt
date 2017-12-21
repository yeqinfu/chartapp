/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.im.base


import android.content.res.Configuration
import android.os.Bundle
import com.ppandroid.app.base.AC_SwipeBase
import com.ppandroid.app.base.SampleApplicationLike
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.utils.DebugLog
import com.ppandroid.app.utils.activitymanager.ActivityManager
import com.ppandroid.app.utils.info.Utils_UserInfo
import com.ppandroid.app.utils.toast.ToastUtil
import com.ppandroid.app.utils.upgrade.UpdateManager


/**
 * Created by Administrator on 2017/3/13. 所有通用页面的基类
 */

open class AC_Base : AC_SwipeBase() {
    protected var token: String? = null
    var isNeedLogin = false

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        // Checks the orientation of the screen
        if (newConfig.orientation === Configuration.ORIENTATION_LANDSCAPE) {//横屏
            //你要执行的操作，可以不写

        } else if (newConfig.orientation === Configuration.ORIENTATION_PORTRAIT) {//竖屏
            //<span style="font-family: Arial, Helvetica, sans-serif;">你要执行的操作，可以不写</span>

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            DebugLog.d("intent", this.javaClass.name)
        } catch (e: Exception) {
            DebugLog.d("intent", "=print class name exception===========")
        }
        if (!isLoading){//check login status
            SampleApplicationLike.toLogin()
            finish()
        }

    }


    /**

     * @return isLoading 是否为登录状态
     */
    val isLoading: Boolean
        get() {
            return Utils_UserInfo.isLogined(this)
        }

    override fun onResume() {
        super.onResume()
        ActivityManager.getActivityManager().pushActivity(this)
        /**如果还没有检查过版本，这边进行检查版本*/
        if (!UpdateManager.getUpdateManager(this@AC_Base).checkFlag) {
            UpdateManager.getUpdateManager(this@AC_Base).checkAppUpdate(true)
        }


    }

    override fun onPause() {
        super.onPause()

    }


    override fun onDestroy() {
        DebugLog.d("ActivityManager---------->$packageName+$localClassName")
        ActivityManager.getActivityManager().popActivity(this)
        super.onDestroy()
        System.gc()
    }

    protected fun toast(msg: String) {
        ToastUtil.toast(this,msg)
    }

    protected fun toast(resId: Int) {
        ToastUtil.toast(this,resId)
    }
    protected fun toast(error: ErrorBody?) {
        toast(error?.message ?: "")
    }

}
