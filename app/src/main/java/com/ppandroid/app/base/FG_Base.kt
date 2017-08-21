package com.ppandroid.im.base


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ppandroid.app.AC_Login
import com.ppandroid.app.base.AC_ContentFG
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.utils.Utils_SharedPreferences
import com.ppandroid.app.utils.toast.ToastUtil
import org.greenrobot.eventbus.EventBus
import java.io.UnsupportedEncodingException
import java.net.URLEncoder


/**
 * 功能性base 不放业务base逻辑
 */
abstract class FG_Base : Fragment() {
    protected lateinit var sp: Utils_SharedPreferences
    protected abstract fun fgRes(): Int
    protected abstract fun afterViews()
    protected var isNeedEventBus = false
        set(value) {
            field = value
            if (!EventBus.getDefault().isRegistered(this) && isNeedEventBus) {
                EventBus.getDefault().register(this)
            }
        }

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

    protected fun startAC(fragment: String) {
        var it = AC_ContentFG.createIntent(activity, fragment)
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
        return !TextUtils.isEmpty(string)
    }

    protected fun toLogin() {
        var it = Intent()
        it.setClass(activity, AC_Login::class.java)
        startActivity(it)
    }

    protected fun toast(error: ErrorBody?) {
        toast(error?.message ?: "")
    }

    protected fun toast(msg: String?) {
        msg?.let {
            if (!TextUtils.isEmpty(msg)) {
                ToastUtil.toast(activity, it)
            }
        }

    }

    protected fun toast(resId: Int) {
        ToastUtil.toast(activity, resId)
    }

    companion object {
        fun getContentView(context: Activity): View {
            return (context.findViewById(android.R.id.content) as ViewGroup).getChildAt(0)
        }
    }

    override fun onDestroy() {
        if (isNeedEventBus && EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
        System.gc()
        super.onDestroy()

    }
}
