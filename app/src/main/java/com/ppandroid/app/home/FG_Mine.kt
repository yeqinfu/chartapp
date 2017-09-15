package com.ppandroid.im

import android.content.Intent
import com.ppandroid.app.R
import com.ppandroid.app.bean.ET_Refresh
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.mine.BN_UserInfo
import com.ppandroid.app.demo.AC_HorChart
import com.ppandroid.app.home.mine.energyanalysis.FG_EnergyAnalysis
import com.ppandroid.app.home.mine.instrument.FG_InstrumentList
import com.ppandroid.app.home.mine.systemsetting.FG_SystemSetting
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
        refreshLayout.isEnableLoadmore=false
        isNeedEventBus=true
        tv_system_setting.setOnClickListener {
            startAC(FG_SystemSetting::class.java.name)
        }
        tv_settings.setOnClickListener{
            var it= Intent()
            it.setClass(activity, AC_HorChart::class.java)
            startActivity(it)
           // startAC(FG_Settings::class.java.name)
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