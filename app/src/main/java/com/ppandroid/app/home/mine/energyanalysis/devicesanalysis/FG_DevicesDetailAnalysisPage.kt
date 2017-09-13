package com.ppandroid.app.home.mine.energyanalysis.devicesanalysis

import com.ppandroid.app.bean.BN_Vertical
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.mine.energyanalysis.BN_DevicesDetailAnalysisPage
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import kotlinx.android.synthetic.main.fg_base_devices_analysis_page.*
import java.util.*

/**
 * Created by yeqinfu on 2017/9/13.
 */
class FG_DevicesDetailAnalysisPage :FG_BaseDevicesAnlysisPage(){
    override fun init() {

    }

    override fun loadContent() {
        var url="user/energy/analysis/getDeviceDetail.json?deviceId=$parentId&dateString=$select"
        Http.get(activity,url, BN_DevicesDetailAnalysisPage::class.java,object :MyCallBack<BN_DevicesDetailAnalysisPage>{
            override fun onResponse(response: BN_DevicesDetailAnalysisPage?) {
                response?.let {
                    var list = ArrayList<BN_Vertical>()
                    var list2 = ArrayList<Model>()
                    for ((j, i) in it.message.stageKwh.withIndex()) {
                        var bn = BN_Vertical()
                        var item=Model()
                        item.key=i.key
                        item.values=i.value
                        list2.add(item)
                        if (j % 2 == 0) {
                            if (j < 10) {
                                bn.bottomText = "0" + j
                            } else {
                                bn.bottomText = "" + j
                            }
                        }
                        bn.totalHeight = 100f
                        var r = Random()
                        bn.realHeight = r.nextFloat() * 100f
                        list.add(bn)
                    }

                    v_multiple_view.dataSet = list
                    v_multiple_view.startAnim()
                    lv_list.adapter=AD_List(activity,list2)
                }
            }

            override fun onError(error: ErrorBody?) {
                toast(error)
            }

        })

    }

}