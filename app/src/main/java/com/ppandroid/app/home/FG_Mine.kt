package com.ppandroid.im

import com.ppandroid.app.R
import com.ppandroid.app.bean.ET_Refresh
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.mine.BN_UserInfo
import com.ppandroid.app.home.mine.systemsetting.FG_SystemSetting
import com.ppandroid.app.home.mine.aboutme.FG_Settings
import com.ppandroid.app.home.mine.instrument.FG_InstrumentList
import com.ppandroid.app.home.mine.teammanagement.FG_TeamManagement
import com.ppandroid.app.home.mine.userinfo.FG_UserInfo
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.app.utils.glide.GlideUtils
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_mine.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by yeqinfu on 2017/7/28.
 */
class FG_Mine: FG_Base() {
    override fun fgRes(): Int= R.layout.fg_mine

    override fun afterViews() {
        isNeedEventBus=true
        tv_system_setting.setOnClickListener {
            startAC(FG_SystemSetting::class.java.name)
        }
        tv_settings.setOnClickListener{
            startAC(FG_Settings::class.java.name)
        }
        tv_instrument_list.setOnClickListener {
            startAC(FG_InstrumentList::class.java.name)
        }

        rl_userinfo.setOnClickListener {
            startAC(FG_UserInfo::class.java.name)
        }
        tv_team_manager.setOnClickListener {
            startAC(FG_TeamManagement::class.java.name)
        }
        loadContent()

    }

    private fun loadContent(){
        var url="user/personal/get.json"
        Http.get(activity,url, BN_UserInfo::class.java,object :MyCallBack<BN_UserInfo>{
            override fun onResponse(response: BN_UserInfo?) {
                response?.let {
                    tv_name.text=it.message.employeeEntity.realName
                    it.message.employeeEntity.headPhoto?.let {
                        GlideUtils.displayImage(activity,it,iv_head)
                    }
                }

            }

            override fun onError(error: ErrorBody?) {
                toast(error)
            }

        })

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: ET_Refresh) {
        if (event.taskId === ET_Refresh.TASKID_REFRESH_MINE) {
            loadContent()
        }

    }




}