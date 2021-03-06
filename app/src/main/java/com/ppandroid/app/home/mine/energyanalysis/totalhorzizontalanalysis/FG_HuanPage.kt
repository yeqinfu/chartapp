/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home.mine.energyanalysis.totalhorzizontalanalysis

import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.mine.energyanalysis.BN_HuanPage
import com.ppandroid.app.bean.mine.energyanalysis.BN_HuanPageWeek
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.app.utils.Utils_Common
import java.util.*

/**
 * Created by yeqinfu on 2017/9/21.
 */
class FG_HuanPage:FG_BaseLineChart(){
    override fun getKeyStr(): String="环"

    override fun afterViews() {
        super.afterViews()
        loadContent()
    }
    private fun loadContent(){
        if (index==1){
            loadWeekContent()
            return
        }
        var url=if (energyClassificationId=="1"){
            "user/energy/analysis/getDeviceAllDetailHuanbi.json?dateString=$select"
        }else{
            "user/water/analysis/getDeviceAllDetailHuanbi.json?dateString=$select"
        }
        Http.get(activity,url,BN_HuanPage::class.java,object :MyCallBack<BN_HuanPage>{
            override fun onResponse(response: BN_HuanPage?) {
                response?.let {
                    if (!isAdded){
                        return
                    }
                    var sum=it.message.thisAnalysisDeviceDetailDto.totalKwh?.toString()?:""
                    var radio=it.message.huanbiRatio
                    var avg=it.message.thisAnalysisDeviceDetailDto.accurateAverageKwh
                    setSumRadioAvg(sum,radio,Utils_Common.parseNumberString(avg))
                    value.putAll(setValues(it.message.huanbiAnalysisDeviceDetailDto))
                    value2.putAll(setValues(it.message.thisAnalysisDeviceDetailDto))
                    setValueAll()

                }
            }

            override fun onError(error: ErrorBody?) {
                toast(error)
            }

        })
    }



    private fun setValues(list: BN_HuanPage.MessageBean.AnalysisDeviceDetailDtoBean?): Map<out String, Double> {
        var result= HashMap<String, Double>()
        var data=list?.stageKwh
        data?.let {
            var need=false
            if (xValue.size<it.size){//横坐标的刻度长取决于两组数据的大的那一个
                xValue.clear()
                need=true
            }
            for (item in it){
                if (need){//添加x轴数据
                    xValue.add(Utils_Common.findNumberFromStr(item.key))
                }
                result.put(Utils_Common.findNumberFromStr(item.key),Utils_Common.parseNumberString(item.value))

            }
        }
        return result
    }

    private fun loadWeekContent() {
        var url=if (energyClassificationId=="1"){
            "user/energy/analysis/getWeekAnalysisHuanbi.json"
        }else{
            "user/water/analysis/getWeekAnalysisHuanbi.json"
        }
        Http.get(activity,url, BN_HuanPageWeek::class.java,object :MyCallBack<BN_HuanPageWeek>{
            override fun onResponse(response: BN_HuanPageWeek?) {
                response?.let {
                    if (!isAdded){
                        return
                    }
                    var sum=it.message.thisAnalysisWeekParamDto.weekSum?.toString()?:""
                    var radio=it.message.huanbiRatio
                    var avg=it.message.thisAnalysisWeekParamDto.weekAverage
                    setSumRadioAvg(sum,radio,Utils_Common.parseNumberString(avg))
                    xValue.clear()
                    xValue.add("周一")
                    xValue.add("周二")
                    xValue.add("周三")
                    xValue.add("周四")
                    xValue.add("周五")
                    xValue.add("周六")
                    xValue.add("周日")
                    value.putAll(setValues2(it.message.huanbiAnalysisWeekParamDto))
                    value2.putAll(setValues2(it.message.thisAnalysisWeekParamDto))
                    setValueAll()
                }
            }

            override fun onError(error: ErrorBody?) {
                toast(error)
            }

        })


    }

    private fun setValues2(list: BN_HuanPageWeek.MessageBean.AnalysisWeekParamDtoBean?): Map<out String, Double> {

        var result= HashMap<String, Double>()
        var data=list?.deviceSumString
        data?.let {
            for ((j,item) in it.withIndex()){
                result.put(xValue[j],Utils_Common.parseNumberString(item))

            }
        }
        return result
    }
}