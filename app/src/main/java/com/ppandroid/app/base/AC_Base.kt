package com.ppandroid.im.base


import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import com.ppandroid.app.utils.DebugLog
import com.ppandroid.app.utils.SnackbarUtils

/**
 * Created by Administrator on 2017/3/13. 所有通用页面的基类
 */

open class AC_Base : AppCompatActivity() {
    protected var token: String? = null
    var isNeedLogin = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            DebugLog.d("intent", this.javaClass.getName())
        } catch (e: Exception) {
            DebugLog.d("intent", "=print class name exception===========")
        }

    }


    /**

     * @return isLoading 是否为登录状态
     */
    val isLoading: Boolean
        get() {
            val loading = false

            return loading
        }

    override fun onResume() {
        super.onResume()


    }


    override fun onDestroy() {
        super.onDestroy()
        System.gc()
    }

    protected fun toast(msg: String) {
        if (!TextUtils.isEmpty(msg)) {
            SnackbarUtils.with(getContentView(this)).setMessage(msg).show()
        }
    }

    protected fun toast(resId: Int) {
        SnackbarUtils.with(getContentView(this)).setMessage(getString(resId)).show()
    }

    companion object {
        fun getContentView(context: Activity): View {
            return (context.findViewById(android.R.id.content) as ViewGroup).getChildAt(0)
        }
    }
}
