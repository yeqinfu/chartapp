/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home.mine.energyanalysis.totalhorzizontalanalysis

import com.ppandroid.app.bean.BN_Vertical
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.mine.energyanalysis.BN_EnergyAnalysis
import com.ppandroid.app.bean.mine.energyanalysis.BN_TotalAnalysis
import com.ppandroid.app.home.mine.energyanalysis.devicesanalysis.FG_BaseDevicesAnlysisPage
import com.ppandroid.app.home.mine.energyanalysis.horizontalanalysis.FG_BaseHistoramAnalysisPage
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.app.utils.Utils_Common
import kotlinx.android.synthetic.main.fg_base_histroam_analysis_page.*
import java.util.*

/**
 * Created by yeqinfu on 2017/9/18.
 */
class FG_HistogramPage : FG_BaseHistoramAnalysisPage(){
    override fun loadContent() {
        /**当index==4时，代表周*/
        if (index==4){
            loadWeekAnalysis()
            return
        }

        //原来日月年全部调用
        var url = "user/energy/analysis/getDeviceAllDetail.json"
        if (index != 3) {//总计
            url += "?dateString=$select"
        }
        Http.get(activity, url, BN_TotalAnalysis::class.java, object : MyCallBack<BN_TotalAnalysis> {
            override fun onResponse(response: BN_TotalAnalysis?) {
                response?.let {
                    /**柱状图*/
                    var list = ArrayList<BN_Vertical>()
                    /**柱状图最大值*/
                    var max = 0.0
                    val numbers = DoubleArray(it.message.stageKwh.size)
                    for ((j, i) in it.message.stageKwh.withIndex()) {
                        var item = FG_BaseDevicesAnlysisPage.Model()
                        item.key = i.key
                        item.values = i.value
                        /**把数组字符串全部转成double 并且找到最大值*/
                        numbers[j] = Utils_Common.parseNumberString(i.value)
                        if (max < numbers[j]) {
                            max = numbers[j]
                        }
                    }
                    max *= 1.2
                    for ((j, item) in numbers.withIndex()) {
                        var bn = BN_Vertical()
                        if (index == 0) {//日
                            if (j % 2 == 0) {
                                if (j < 10) {
                                    bn.bottomText = "0" + j
                                } else {
                                    bn.bottomText = "" + j
                                }
                            }
                        } else if (index == 1) {//月,年
                            var yue = j + 1
                            if (j % 2 == 0) {
                                if (yue < 10) {
                                    bn.bottomText = "0" + yue
                                } else {
                                    bn.bottomText = "" + yue
                                }
                            }
                        } else if (index == 2) {//,年
                            var yue = j + 1
                            if (yue < 10) {
                                bn.bottomText = "0" + yue
                            } else {
                                bn.bottomText = "" + yue
                            }
                        } else {//总的
                            bn.bottomText = select
                        }
                        bn.totalHeight = max.toFloat()
                        bn.realHeight = item.toFloat()
                        list.add(bn)
                    }
                    /**知道我为啥加空安全吗？因为进入这个页面瞬间退出的时候就报空指针了*/
                    tv_averageKwh?.text=it.message.averageKwh
                    setAvg(max,it.message.averageKwh)
                    v_multiple_view?.dataSet = list
                    v_multiple_view?.startAnim()
                    tv_avg?.text = it.message.accurateAverageKwh
                    tv_total?.text = it.message.totalKwh
                    if (index==3){
                        tv_time.text=it.message.dateString
                    }
                }
            }

            override fun onError(error: ErrorBody?) {
                toast(error)
            }

        })

    }

    private fun loadWeekAnalysis() {
        var url="user/energy/analysis/getWeekAnalysis.json"
        Http.get(activity,url, BN_EnergyAnalysis::class.java,object :MyCallBack<BN_EnergyAnalysis>{
            override fun onResponse(response: BN_EnergyAnalysis?) {
                response?.let {
                    /**柱状图*/
                    var list = ArrayList<BN_Vertical>()
                    /**柱状图最大值*/

                    var args=it.message.deviceSumString
                    val aa = DoubleArray(7)
                    aa[0] = java.lang.Double.parseDouble(args[0])
                    aa[1] = java.lang.Double.parseDouble(args[1])
                    aa[2] = java.lang.Double.parseDouble(args[2])
                    aa[3] = java.lang.Double.parseDouble(args[3])
                    aa[4] = java.lang.Double.parseDouble(args[4])
                    aa[5] = java.lang.Double.parseDouble(args[5])
                    aa[6] = java.lang.Double.parseDouble(args[6])
                    var max = aa[0]
                    for ((j, i) in args.withIndex()) {
                        if (max < aa[j]) {
                            max = aa[j]
                        }
                    }
                    max *= 1.2
                    for ((j, item) in args.withIndex()) {
                        var bn = BN_Vertical()
                        when(j){
                            0->  bn.bottomText = "周一"
                            1->  bn.bottomText = "周二"
                            2->  bn.bottomText = "周三"
                            3->  bn.bottomText = "周四"
                            4->  bn.bottomText = "周五"
                            5->  bn.bottomText = "周六"
                            6->  bn.bottomText = "周日"
                        }

                        bn.totalHeight = max.toFloat()
                        bn.realHeight = item.toFloat()
                        list.add(bn)
                    }
                    /**知道我为啥加空安全吗？因为进入这个页面瞬间退出的时候就报空指针了*/
                    tv_averageKwh?.text=it.message.weekAverage
                    setAvg(max,it.message.weekAverage)
                    v_multiple_view?.dataSet = list
                    v_multiple_view?.startAnim()
                    tv_avg?.text = it.message.weekAverage
                    tv_total?.text = it.message.weekSum
                }
            }

            override fun onError(error: ErrorBody?) {
                toast(error)
            }

        })
    }



}