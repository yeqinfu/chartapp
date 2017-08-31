package com.ppandroid.app.home.mine.energyanalysis

import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.mine.energyanalysis.BN_DevicePage
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import kotlinx.android.synthetic.main.fg_base_analysis_page.*

/**
 * Created by yeqinfu on 2017/8/31.
 */
class FG_DevicesPage : FG_BaseAnlysisPage(){

    override fun loadContent() {
        var url="user/energy/analysis/getDevice.json?dateString=$select"
        if (parentId!="-1"){
            url+="&parentId=$parentId"
        }
        isHaveChild=false
        Http.get(activity, url, BN_DevicePage::class.java, object : MyCallBack<BN_DevicePage> {
            override fun onResponse(response: BN_DevicePage?) {
                response?.let {
                    tv_totalKwh.text = it.message.analysisDeviceSum
                    var adapter = AD_List(activity, getList(it), isHaveChild)
                    v_dount_view.startAnim()
                    lv_list.adapter = adapter
                }
            }

            override fun onError(error: ErrorBody?) {
                toast(error)
            }

        })
    }

    private fun getList(it: BN_DevicePage): List<Model> {
        var list=ArrayList<Model>()
        for (item in it.message.analysisDeviceParamDtoList){
            var model= Model()
            model.name=item.deviceName
            model.ratio=item.deviceRatio
            model.kmh=item.deviceKwh
            model.isLeaf=false
            list.add(model)
        }
        return list
    }

    override fun init() {
        when(index){
            0->  tv_type.text="今日用电"
            1->  tv_type.text="本月用电"
            2->  tv_type.text="本年用电"
            3->  tv_type.text="总用电"
        }

    }



}