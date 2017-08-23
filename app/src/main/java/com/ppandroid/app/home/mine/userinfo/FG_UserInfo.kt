package com.ppandroid.app.home.mine.userinfo

import com.ppandroid.app.R
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.mine.BN_UserInfo
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.activity_ac__login.*
import kotlinx.android.synthetic.main.fg_user_info.*
import kotlinx.android.synthetic.main.layout_head_view.*

/**
 * Created by yeqinfu on 2017/8/23.
 * 个人信息
 */
class FG_UserInfo:FG_Base(){
    override fun fgRes(): Int= R.layout.fg_user_info

    override fun afterViews() {
        head_view.init(activity)
        head_view.setCenterTitle("个人信息")
        head_view.setRightText("保存"){
            toast("baocun")
        }
        loadContent()
    }

    private fun loadContent(){
        var url="user/personal/get.json"
        Http.get(activity,url, BN_UserInfo::class.java,object : MyCallBack<BN_UserInfo> {
            override fun onResponse(response: BN_UserInfo?) {
                response?.let {
                    et_name.setText(it.message.realName)
                    et_account.setText(it.message.mobile)
                     if (it.message.sex==1){
                         tv_gender.text="男"
                     }else{
                         tv_gender.text="女"
                     }

                }

            }

            override fun onError(error: ErrorBody?) {
                toast(error)
            }

        })

    }


}