package com.ppandroid.im

import com.ppandroid.app.R
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.demo.FG_Demo02
import com.ppandroid.app.home.mine.FG_SystemSetting
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.app.http.Http
import com.ppandroid.app.utils.activitymanager.ActivityManager
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