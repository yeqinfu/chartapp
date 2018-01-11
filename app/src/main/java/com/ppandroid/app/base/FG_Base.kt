/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

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
import com.ppandroid.app.bean.ET_ReSend
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.utils.Utils_SharedPreferences
import com.ppandroid.app.utils.toast.ToastUtil
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.io.UnsupportedEncodingException
import java.net.URLEncoder


/**
 * 功能性base 不放业务base逻辑
 * README:
 * 在很多请求代码中callback会有一个判断
 *   if (!isAdded){return}
 *   为什么会增加这个判断呢，首先这个判断是非常的愚蠢的，但是又找不到代替方法
 *   因为网络请求需要时间，然而如果这个callback在执行回调的时候，fragment已经
 *   detath了，那么activity这个变量（getActivity方法）返回就为空了。kt代码中
 *   activity似乎是当成一个不会空的对象了。所以增加这个判断保证不会抛出
 *   activity==null的异常如果维护者找到合适的替代方法，全局替换或者删除这个代
 *   码就行了。很多东西都是设置了TODO，但是却不会去todo。 &( ^___^ )&
 *   在这边举一个例子：分项用电有四个tab，都是fragment，快速切换1，4tab，那么
 *   fragment会被回收和创造，而网络却不及时，可以重现问题。
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
    protected fun startAC(fragment: String,bundle: Bundle) {
        var it = AC_ContentFG.createIntent(activity, fragment,bundle)
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
                ToastUtil.toast(it)
            }
        }

    }

    protected fun toast(resId: Int) {
        ToastUtil.toast(resId)
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
    /**页面请求重发 当md5刷新token成功时候会发一个消息给这个方法，refresh方法在子类重写，调用loadContent方法，进行消息重发
     * 目前FG_DEVICES页面作为首页在用
     * */
    @Subscribe(threadMode = ThreadMode.MAIN)
    open fun onMessageEvent(event: ET_ReSend) {
        if (event.taskId === ET_ReSend.TASKID_RESEND) {
            refresh()
        }

    }

    protected open fun refresh() {
    }
}
