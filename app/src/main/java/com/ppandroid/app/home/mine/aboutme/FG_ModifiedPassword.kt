/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home.mine.aboutme

import android.text.TextUtils
import com.ppandroid.app.R
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.app.utils.SecurityUtils
import com.ppandroid.im.base.FG_Base
import com.ppandroid.im.bean.BaseBody
import kotlinx.android.synthetic.main.fg_modified_password.*
import kotlinx.android.synthetic.main.layout_head_view.*
import java.util.*

/**
 * Created by yeqinfu on 2017/9/1.
 */
class FG_ModifiedPassword:FG_Base(){
    override fun fgRes(): Int= R.layout.fg_modified_password
    override fun afterViews() {
        head_view.init(activity)
        head_view.setCenterTitle("修改密码")
        btn_confirm.setOnClickListener {
            postInfo()
        }
    }

    private fun postInfo() {
        var old=et_old.text.toString()
        if (TextUtils.isEmpty(old)){
            toast("请输入旧密码")
            return
        }
        var newPassword=et_new.text.toString()
        if (TextUtils.isEmpty(newPassword)){
            toast("请输入新密码")
            return
        }
        var confirmPasswrod=et_confirm.text.toString()
        if (TextUtils.isEmpty(confirmPasswrod)){
            toast("请输入确认密码")
            return
        }
        if (newPassword.length<6||newPassword.length>16){
            toast("新密码长度不符合")
            return
        }
        if (confirmPasswrod!=newPassword){
            toast("两次输入密码不一致")
            return
        }
        var url="user/personal/updPassword.json"
        var map=TreeMap<String,String>()
        map.apply {
            put("oldPassword",SecurityUtils.decode(old))
            put("newPassword",SecurityUtils.decode(newPassword))
        }
        Http.post(activity,url,map,BaseBody::class.java,object :MyCallBack<BaseBody>{
            override fun onResponse(response: BaseBody?) {
                response?.let {
                    if (it.isSuccess){
                        toast("修改成功")
                        finish()
                    }
                }
            }

            override fun onError(error: ErrorBody?) {
                toast(error)
            }

        })
    }

}