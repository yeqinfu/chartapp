package com.ppandroid.app.home.mine.energyanalysis

import android.content.Intent
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
    override fun fgRes(): Int= R.layout.fg_energy_analysis
    override fun afterViews() {
        head_view.init(activity)
        head_view.setCenterTitle("能耗分析")
        head_view.setRightText("历史"){
            startAC(FG_History::class.java.name)
        }
        v_yellow_chart.setOnClickListener {
            var intent=Intent()
            intent.setClass(activity,AC_TotalHorzintalanalysis::class.java)
            startActivity(intent)

        }
        tv_cate.setOnClickListener {
            startAC(FG_CateAnalysis::class.java.name)
        }
        tv_area.setOnClickListener {
            startAC(FG_AreaAnalysis::class.java.name)
        }
        tv_devices.setOnClickListener {
            startAC(FG_DeviceAnalysis::class.java.name)
        }
        tv_instrument.setOnClickListener {
            startAC(FG_InstrumentAnalysis::class.java.name)
        }
        loadContent()
    }
    private fun loadContent(){
        var url="user/energy/analysis/getWeekAnalysis.json"
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