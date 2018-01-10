/*
 * Created by yeqinfu on 18-1-8 下午4:55
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home.devices

import com.ppandroid.app.R
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.devices.BN_TemperatureMonitor
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_temperature_monitor.*

/**
 * Created by yeqinfu on 2018/1/8.
 */
class FG_TemperatureMonitor:FG_Base(){
    override fun fgRes(): Int= R.layout.fg_temperature_monitor
    private var id: String = "-1"
    override fun afterViews() {
        var b = arguments
        b?.let {
            id = it.getString("id", "-1")
        }
        loadContent()
    }

    private fun loadContent() {
        var url="user/device/record/temperature.json?id=$id"
        Http.get(activity,url, BN_TemperatureMonitor::class.java,object :MyCallBack<BN_TemperatureMonitor>{
            override fun onResponse(response: BN_TemperatureMonitor?) {
                response?.let {
                    if (!isAdded){
                        return
                    }
                    tv_humidity.text=it.message.humidity.toString()
                    tv_temperature.text=it.message.temperature.toString()
                    tv_lastModifyTime.text=it.message.lastModifyTime.toString()
                }
            }

            override fun onError(error: ErrorBody?) {
                toast(error)
            }
        })
    }

}