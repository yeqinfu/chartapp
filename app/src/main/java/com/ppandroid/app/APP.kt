package com.ppandroid.im

import android.app.Application
import android.content.Context
import android.content.Intent
import com.ppandroid.app.AC_Login
import com.ppandroid.app.utils.AppExceptionHandler
import com.ppandroid.app.utils.DebugLog


/**
 * Created by yeqinfu on 2017/8/1.
 */

class APP : Application() {


    companion object {
        var context:Application?= null
        fun getContext(): Context? {
            return context
        }
        fun toLogin(){
            var it= Intent()
            it.setClass(context,AC_Login::class.java)
            context?.startActivity(it)
        }
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        DebugLog.d("+++++++++++++++++++++++++++++++++++++++++++context is"+ context)
        /*****************************************************************
         * 闪退处理
         *****************************************************************/
        val mReportAppError = AppExceptionHandler.getInstance()
        mReportAppError.init(this)
       /* if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }*/

    }



}
