/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home.mine.energyanalysis

import android.content.Intent
import android.os.Bundle
import com.ppandroid.app.R
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.mine.energyanalysis.BN_EnergyAnalysis
import com.ppandroid.app.home.mine.energyanalysis.totalhorzizontalanalysis.AC_TotalHorzintalanalysis
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_energy_analysis.*
import kotlinx.android.synthetic.main.layout_head_view.*
import kotlinx.android.synthetic.main.yellowchartview.*

/**
 * Created by yeqinfu on 2017/8/29.
 * 能耗分析
 */
class FG_EnergyAnalysis :FG_Base(){
    private var energyClassificationId="1"
    override fun fgRes(): Int= R.layout.fg_energy_analysis
    fun createBundle(): Bundle {
        val b = Bundle()
        b.putString("energyClassificationId", energyClassificationId)
        return b
    }
    override fun afterViews() {
        arguments?.let {
            energyClassificationId=it.getString("energyClassificationId","1")
        }
        head_view.init(activity)
        head_view.setCenterTitle("能耗分析")
        head_view.setRightText("历史"){
            startAC(FG_History::class.java.name,createBundle())
        }
        var title=if (energyClassificationId == "1"){
             "用电分析"
        }else{
             "用水分析"
        }
        v_yellow_chart.setTitle(title)
        v_yellow_chart.setOnClickListener {
            var intent=Intent()
            intent.putExtra("energyClassificationId",energyClassificationId)
            intent.setClass(activity,AC_TotalHorzintalanalysis::class.java)
            startActivity(intent)
        }
        var b=createBundle()
        tv_cate.setOnClickListener {
            startAC(FG_CateAnalysis::class.java.name,b)
        }
        tv_area.setOnClickListener {
            startAC(FG_AreaAnalysis::class.java.name,b)
        }
        tv_devices.setOnClickListener {
            startAC(FG_DeviceAnalysis::class.java.name,b)
        }
        tv_instrument.setOnClickListener {
            startAC(FG_InstrumentAnalysis::class.java.name,b)
        }
        loadContent()
    }
    private fun loadContent(){
        var url: String = if (energyClassificationId.equals("1")){//电
            "user/energy/analysis/getWeekAnalysis.json"
        }else{//水
            "user/water/analysis/getWeekAnalysis.json"
        }
        url+="?energyClassificationId="+energyClassificationId
        Http.get(activity,url, BN_EnergyAnalysis::class.java,object :MyCallBack<BN_EnergyAnalysis>{
            override fun onResponse(response: BN_EnergyAnalysis?) {
                response?.let {
                    v_yellow_chart?.setSumAndAverage(it.message.weekSum,it.message.weekAverage)
                    v_yellow_chart?.startAnim(it.message.deviceSumString)

                }
            }

            override fun onError(error: ErrorBody?) {
                toast(error)
            }

        })
    }

}