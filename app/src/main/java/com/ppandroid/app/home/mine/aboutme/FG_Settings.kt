/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home.mine.aboutme

import android.support.v7.app.AlertDialog
import com.ppandroid.app.R
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.app.utils.Utils_Common
import com.ppandroid.app.utils.activitymanager.ActivityManager
import com.ppandroid.app.utils.info.Utils_UserInfo
import com.ppandroid.im.base.FG_Base
import com.ppandroid.im.bean.BaseBody
import com.tencent.bugly.beta.Beta
import kotlinx.android.synthetic.main.fg_settings.*
import kotlinx.android.synthetic.main.layout_head_view.*

/**
 * Created by yeqinfu on 2017/8/24.
 * 设置
 */
class FG_Settings:FG_Base(){
    override fun fgRes(): Int= R.layout.fg_settings

    override fun afterViews() {
        head_view.init(activity)
        head_view.setCenterTitle("设置")
        tv_cache_value.text=Utils_Common.getTotalCacheSize(activity)
        tv_modified_password.setOnClickListener {
            startAC(FG_ModifiedPassword::class.java.name)
        }
        ll_clear_cache.setOnClickListener{
            Utils_Common.clearAllCache(activity)
            toast("清除缓存成功")
            tv_cache_value.text=Utils_Common.getTotalCacheSize(activity)
        }
        tv_logout.setOnClickListener {
            if (!isLogined()){
                toast("您还没登录")

            }else{
                showConfirmLogoutDialog()
            }
        }
        tv_check_update.setOnClickListener {
            //主动检查更新
            Beta.checkUpgrade()
        }
        tv_about_app.setOnClickListener {
            startAC(FG_AboutMe::class.java.name)
        }

    }

    private fun showConfirmLogoutDialog() {
        val builder = AlertDialog.Builder(activity).setMessage("确定退出吗？").setCancelable(false)
        builder.setPositiveButton("确定") { _, _ ->
            loginOut()
        }
        builder.setNegativeButton("取消") { _, _ ->
            builder.create().dismiss()
        }
        builder.create().show()
    }


    private fun loginOut() {
        var url="user/logout.json?id="+ Utils_UserInfo.getUserId(activity)+"&md5="+ Utils_UserInfo.getUserMD5(activity)
        Http.get(activity,url, BaseBody::class.java,object : MyCallBack<BaseBody> {
            override fun onResponse(response: BaseBody?) {
                response?.let {
                    if (!isAdded){
                        return
                    }
                    if (it.isSuccess){
                        toast("退出成功")
                        Utils_UserInfo.clearUserInfo(activity)
                        toLogin()
                        ActivityManager.getActivityManager().popAllActivity()
                        finish()

                    }else{
                        toast(it.title)
                    }
                }
            }

            override fun onError(error: ErrorBody?) {
                Utils_UserInfo.clearUserInfo(activity)
                toLogin()
                ActivityManager.getActivityManager().popAllActivity()
                finish()
                toast(error?.message)
            }

        })

    }

}