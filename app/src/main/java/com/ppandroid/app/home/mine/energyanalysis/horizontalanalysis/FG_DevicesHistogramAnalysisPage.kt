/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home.mine.energyanalysis.horizontalanalysis

import com.ppandroid.app.bean.BN_Vertical
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.mine.energyanalysis.BN_DevicesDetailAnalysisPage
import com.ppandroid.app.home.mine.energyanalysis.devicesanalysis.FG_BaseDevicesAnlysisPage
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.app.utils.Utils_Common
import kotlinx.android.synthetic.main.fg_base_histroam_analysis_page.*
import java.util.*

/**
 * Created by yeqinfu on 2017/9/16.
 */
class FG_DevicesHistogramAnalysisPage : FG_BaseHistoramAnalysisPage(){


    override fun loadContent() {
        var url =if (energyClassificationId=="1"){
            "user/energy/analysis/getDeviceDetail.json?deviceId=$parentId"
        }else{
            "user/water/analysis/getDeviceDetail.json?deviceId=$parentId"
        }
        if (index != 3) {//总计
            url += "&dateString=$select"
        }
        Http.get(activity, url, BN_DevicesDetailAnalysisPage::class.java, object : MyCallBack<BN_DevicesDetailAnalysisPage> {
            override fun onResponse(response: BN_DevicesDetailAnalysisPage?) {
                response?.let {
                    if (!isAdded){
                        return
                    }
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
                }
            }

            override fun onError(error: ErrorBody?) {
                toast(error)
            }

        })

    }


}