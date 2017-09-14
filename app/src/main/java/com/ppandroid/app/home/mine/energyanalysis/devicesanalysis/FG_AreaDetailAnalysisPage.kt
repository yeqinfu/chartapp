package com.ppandroid.app.home.mine.energyanalysis.devicesanalysis

import com.ppandroid.app.bean.BN_Vertical
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.mine.energyanalysis.BN_AreaDetailAnalysisPage
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.app.utils.Utils_Common
import kotlinx.android.synthetic.main.fg_base_devices_analysis_page.*
import java.util.*

/**
 * Created by yeqinfu on 2017/9/13.
 */
class FG_AreaDetailAnalysisPage : FG_BaseDevicesAnlysisPage() {
    override fun init() {

    }

    override fun loadContent() {
        var url = "user/energy/analysis/getDeviceAreaDetail.json?areaId=$parentId"
        if (index != 3) {//总计
            url += "&dateString=$select"
        }
        Http.get(activity, url, BN_AreaDetailAnalysisPage::class.java, object : MyCallBack<BN_AreaDetailAnalysisPage> {
            override fun onResponse(response: BN_AreaDetailAnalysisPage?) {
                response?.let {
                    /**柱状图*/
                    var list = ArrayList<BN_Vertical>()
                    /**底层列表*/
                    var list2 = ArrayList<Model>()
                    /**柱状图最大值*/
                    var max = 0.0
                    val numbers = DoubleArray(it.message.stageKwh.size)
                    for ((j, i) in it.message.stageKwh.withIndex()) {
                        var item = Model()
                        item.key = i.key
                        item.values = i.value
                        list2.add(item)
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

                    tv_averageKwh.text=it.message.averageKwh
                    v_multiple_view.dataSet = list
                    v_multiple_view.startAnim()
                    lv_list.adapter = AD_List(activity, list2)
                }
            }

            override fun onError(error: ErrorBody?) {
                toast(error)
            }

        })

    }

}