package com.ppandroid.im

import android.app.Application
import android.content.Context
import com.ppandroid.app.utils.AppExceptionHandler
import org.greenrobot.eventbus.EventBus


/**
 * Created by yeqinfu on 2017/8/1.
 */

class APP : Application() {


    companion object {
        var context:Application?= null
        fun getContext(): Context? {
            return context
        }
    }

    override fun onCreate() {
        context = this@APP
        /*****************************************************************
         * 闪退处理
         *****************************************************************/
        val mReportAppError = AppExceptionHandler.getInstance()
        mReportAppError.init(this)
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
        super.onCreate()
    }


}
