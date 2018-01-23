/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home.devices

import android.os.Bundle
import com.ppandroid.app.R
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.devices.BN_DevicesMonitor
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_devices_monitor.*

/**
 * Created by yeqinfu on 2017/9/11.
 */
class FG_DevicesMonitor :FG_Base(){
    override fun afterViews() {
        var b = arguments
        b?.let {
            id = it.getString("id", "-1")
        }
        loadContent()
    }

    override fun fgRes(): Int= R.layout.fg_devices_monitor


    companion object {
        fun createBundle(id: String): Bundle {
            var b = Bundle()
            b.putString("id", id)
            return b
        }
    }

    private var id: String = "-1"

    fun loadContent(){
        var url="user/device/recordElectricity.json?id=$id"
        Http.get(activity,url,BN_DevicesMonitor::class.java,object :MyCallBack<BN_DevicesMonitor>{
            override fun onResponse(response: BN_DevicesMonitor?) {
                response?.safeRun {

                    var body = it.message
                    body?.let {
                        tv_lastModifyTime.text = "最后更新时间：" + it.lastModifyTime
                        tv_Ia.text = it.ia.toString()
                        tv_Ib.text = it.ib.toString()
                        tv_Ic.text = it.ic.toString()

                        tv_Ua.text = it.uab.toString()
                        tv_Ub.text = it.ubc.toString()
                        tv_Uc.text = it.uca.toString()


                        tv_Ps.text = it.ps.toString()
                        tv_Qs.text = it.qs.toString()
                        tv_PFs.text = it.pFs.toString()
                        tv_Ep.text = it.pKwh.toString()
                    }
                }
            }

            override fun onError(error: ErrorBody?) {
                toast(error)
            }

        })
    }

}