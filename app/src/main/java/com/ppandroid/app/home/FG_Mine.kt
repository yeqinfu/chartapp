package com.ppandroid.im

import com.ppandroid.app.R
import com.ppandroid.app.bean.BN_Vertical
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.app.http.OKUtils
import com.ppandroid.app.utils.activitymanager.ActivityManager
import com.ppandroid.app.utils.info.Utils_UserInfo
import com.ppandroid.im.base.FG_Base
import com.ppandroid.im.bean.BaseBody
import kotlinx.android.synthetic.main.fg_mine.*
import kotlinx.android.synthetic.main.yellowchartview.*
import java.util.*

/**
 * Created by yeqinfu on 2017/7/28.
 */
class FG_Mine: FG_Base() {
    override fun fgRes(): Int= R.layout.fg_mine

    override fun afterViews() {
        btn_login_out.setOnClickListener {
            loginOut()
        }
        var list = ArrayList<BN_Vertical>()
        for (i in 0..23) {
            var bn = BN_Vertical()
            if (i % 2 == 0) {
                if (i < 10) {
                    bn.bottomText = "0" + i
                } else {
                    bn.bottomText = "" + i
                }

            }
            bn.totalHeight = 100f
            var r = Random()
            bn.realHeight = r.nextFloat() * 100f
            list.add(bn)
        }

        v_multiple_view.dataSet = list
        btn_multi.setOnClickListener {
            v_multiple_view.startAnim()

        }

        btn_start_anim.setOnClickListener {
            v_yellow_chart.startAnim()
        }


    }

    private fun loginOut() {
        if (!isLogined()){
            toast("您还没登录")
        }
        var url="user/logout.json?id="+Utils_UserInfo.getUserId(activity)+"&md5="+Utils_UserInfo.getUserMD5(activity)
        OKUtils.get(activity,url,BaseBody::class.java,object :MyCallBack<BaseBody>{
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