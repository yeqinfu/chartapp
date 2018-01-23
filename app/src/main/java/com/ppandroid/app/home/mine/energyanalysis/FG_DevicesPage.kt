/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home.mine.energyanalysis

import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.mine.energyanalysis.BN_DevicePage
import com.ppandroid.app.home.mine.energyanalysis.devicesanalysis.FG_DeviceDetailAnalysis
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.app.utils.Devices
import kotlinx.android.synthetic.main.fg_base_analysis_page.*

/**
 * Created by yeqinfu on 2017/8/31.
 */
class FG_DevicesPage : FG_BaseAnlysisPage() {

    override fun loadContent() {
        var url = if (energyClassificationId == Devices.ELECTRIC) {
            "user/energy/analysis/getDevice.json?dateString=$select"
        } else {
            "user/water/analysis/getDevice.json?dateString=$select"
        }
        if (parentId != "-1") {
            url += "&parentId=$parentId"
        }
        isHaveChild = false
        Http.get(activity, url, BN_DevicePage::class.java, object : MyCallBack<BN_DevicePage> {
            override fun onResponse(response: BN_DevicePage?) {
                response?.safeRun {
                    tv_totalKwh?.text = it.message.analysisDeviceSum
                    var k = getList(it)
                    var adapter = AD_List(activity, k, isHaveChild)
                    v_dount_view.startAnim()
                    lv_list.adapter = adapter
                    lv_list.setOnItemClickListener { adapterView, view, i, l ->
                        var item = it.message.analysisDeviceParamDtoList[i]
                        var bundle = FG_DeviceDetailAnalysis.createBundle(energyClassificationId, item.deviceId, item.deviceName)
                        startAC(FG_DeviceDetailAnalysis::class.java.name, bundle)

                    }
                    v_dount_view.charRender(getValues(k))
                    if (index == 3) {
                        tv_time.text = it.message.dateString
                    }
                }
            }

            override fun onError(error: ErrorBody?) {
                toast(error)
            }

        })
    }

    private fun getList(it: BN_DevicePage): List<Model> {
        var list = ArrayList<Model>()
        for (item in it.message.analysisDeviceParamDtoList) {
            var model = Model()
            model.name = item.deviceName
            model.ratio = item.deviceRatio
            model.kmh = item.deviceKwh
            model.isLeaf = false
            list.add(model)
        }
        return list
    }

    override fun init() {
        setText()

    }


}