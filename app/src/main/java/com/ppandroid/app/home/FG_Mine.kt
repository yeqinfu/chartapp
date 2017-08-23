package com.ppandroid.im

import com.ppandroid.app.R
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.mine.BN_UserInfo
import com.ppandroid.app.demo.FG_Demo02
import com.ppandroid.app.home.mine.FG_SystemSetting
import com.ppandroid.app.home.mine.instrument.FG_InstrumentList
import com.ppandroid.app.home.mine.userinfo.FG_UserInfo
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.app.utils.activitymanager.ActivityManager
import com.ppandroid.app.utils.glide.GlideUtils
import com.ppandroid.app.utils.info.Utils_UserInfo
import com.ppandroid.im.base.FG_Base
import com.ppandroid.im.bean.BaseBody
import kotlinx.android.synthetic.main.fg_mine.*

/**
 * Created by yeqinfu on 2017/7/28.
 */
class FG_Mine: FG_Base() {
    override fun fgRes(): Int= R.layout.fg_mine

    override fun afterViews() {
        tv_system_setting.setOnClickListener {
            startAC(FG_SystemSetting::class.java.name)
        }
        tv_settings.setOnClickListener{
            startAC(FG_Demo02::class.java.name)
        }
        tv_instrument_list.setOnClickListener {
            startAC(FG_InstrumentList::class.java.name)
        }

        rl_userinfo.setOnClickListener {
            startAC(FG_UserInfo::class.java.name)
        }
        loadContent()

    }

    private fun loadContent(){
        var url="user/personal/get.json"
        Http.get(activity,url, BN_UserInfo::class.java,object :MyCallBack<BN_UserInfo>{
            override fun onResponse(response: BN_UserInfo?) {
                response?.let {
                    tv_name.text=it.message.realName
                    it.message.headPhoto?.let {
                        GlideUtils.displayImage(activity,it,iv_head)
                    }
                }

            }

            override fun onError(error: ErrorBody?) {
                toast(error)
            }

        })

    }



    private fun loginOut() {
        if (!isLogined()){
            toast("您还没登录")
            return
        }
        var url="user/logout.json?id="+Utils_UserInfo.getUserId(activity)+"&md5="+Utils_UserInfo.getUserMD5(activity)
        Http.get(activity,url,BaseBody::class.java,object :MyCallBack<BaseBody>{
            override fun onResponse(response: BaseBody?) {
                response?.let {
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
                toast(error?.message)
            }

        })

    }


}