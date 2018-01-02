/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.im

import android.widget.TextView
import com.ppandroid.app.R
import com.ppandroid.app.bean.ET_Refresh
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.mine.BN_UserInfo
import com.ppandroid.app.home.mine.aboutme.FG_AboutMe
import com.ppandroid.app.home.mine.aboutme.FG_Settings
import com.ppandroid.app.home.mine.energyanalysis.FG_EnergyAnalysis
import com.ppandroid.app.home.mine.instrument.FG_InstrumentAll
import com.ppandroid.app.home.mine.systemsetting.FG_SystemSettingAll
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
        refreshLayout.setOnRefreshListener {
           loadContent()

        }
        var tv=TextView(activity)
        tv.maxEms

        ll_about_me.setOnClickListener {
            startAC(FG_AboutMe::class.java.name)
         //   startAC(FG_Test::class.java.name)
        }
        refreshLayout.isEnableLoadmore=false
        isNeedEventBus=true
        tv_system_setting.setOnClickListener {
            startAC(FG_SystemSettingAll::class.java.name)
        }
        tv_settings.setOnClickListener{
            startAC(FG_Settings::class.java.name)
        }
        tv_instrument_list.setOnClickListener {
            startAC(FG_InstrumentAll::class.java.name)
        }

        rl_userinfo.setOnClickListener {
            startAC(FG_UserInfo::class.java.name)
        }
        tv_team_manager.setOnClickListener {
            startAC(FG_TeamManagement::class.java.name)
        }
        tv_energy_analysis.setOnClickListener {
            startAC(FG_EnergyAnalysis::class.java.name)
        }
        loadContent()

    }

    public fun loadContent(){
        var url="user/personal/get.json"
        Http.get(activity,url, BN_UserInfo::class.java,object :MyCallBack<BN_UserInfo>{
            override fun onResponse(response: BN_UserInfo?) {
                refreshLayout.finishRefresh()

                response?.let {
                    tv_name.text=it.message.employeeEntity.realName
                    tv_server_provide.text=it.message.companyEntity.serviceProvider
                    it.message.employeeEntity.headPhoto?.let {
                        GlideUtils.displayImage(activity,it,iv_head)
                    }
                }

            }

            override fun onError(error: ErrorBody?) {
                refreshLayout.finishRefresh()
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

    override fun refresh() {
        super.refresh()
        loadContent()
    }




}