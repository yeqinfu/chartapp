// (c)2016 Flipboard Inc, All Rights Reserved.

package com.ppandroid.im.base


import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ppandroid.app.base.AC_ContentFG
import com.ppandroid.app.utils.SnackbarUtils
import com.ppandroid.app.utils.Utils_SharedPreferences
import java.io.UnsupportedEncodingException
import java.net.URLEncoder


/**
 * 功能性base 不放业务base逻辑
 */
abstract class FG_Base : Fragment() {
    protected lateinit var sp: Utils_SharedPreferences
    protected abstract fun fgRes():Int
    protected abstract fun afterViews()
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(fgRes(), container, false)
        sp = Utils_SharedPreferences(activity)
        return view
    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        afterViews()
    }


    protected fun finish() {
        activity.finish()
    }

    protected fun startAC(fragment:String){
        var it= AC_ContentFG.createIntent(activity,fragment)
        startActivity(it)
    }


    /**
     * 将string 数据转换成网络请求格式
     */
    protected fun String2UI8(string: String): String {
        var string = string
        try {
            string = URLEncoder.encode(string, "utf-8")
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }

        return string
    }



    /**
     * @return  是否为登录状态
     */
    protected fun isLogined(): Boolean {
        val string = sp.getString("Token", null)
        return TextUtils.isEmpty(string)
    }


    protected fun toast(msg: String) {
        if (!TextUtils.isEmpty(msg)) {
            SnackbarUtils.with(getContentView(activity)).setMessage(msg).show()
        }
    }

    protected fun toast(resId: Int) {
        SnackbarUtils.with(getContentView(activity)).setMessage(getString(resId)).show()
    }

    companion object {
        fun getContentView(context: Activity): View {
            return (context.findViewById(android.R.id.content) as ViewGroup).getChildAt(0)
        }
    }
}
