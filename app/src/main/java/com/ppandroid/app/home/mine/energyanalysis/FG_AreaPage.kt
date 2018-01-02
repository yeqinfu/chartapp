/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home.mine.energyanalysis

import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.mine.energyanalysis.BN_AreaPage
import com.ppandroid.app.home.mine.energyanalysis.devicesanalysis.FG_AreaDetailAnalysis
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import kotlinx.android.synthetic.main.fg_base_analysis_page.*

/**
 * Created by yeqinfu on 2017/8/31.
 */
class FG_AreaPage : FG_BaseAnlysisPage(){

    override fun loadContent() {
        var url=if (energyClassificationId=="1"){
            "user/energy/analysis/getArea.json?dateString=$select"
        }else{
            "user/water/analysis/getArea.json?dateString=$select"
        }
        if (parentId!="-1"){
            url+="&parentId=$parentId"
        }
        Http.get(activity, url, BN_AreaPage::class.java, object : MyCallBack<BN_AreaPage> {
            override fun onResponse(response: BN_AreaPage?) {
                response?.let {
                    tv_totalKwh.text = it.message.analysisAreaSum
                    var k=getList(it)
                    var adapter = AD_List(activity, k, isHaveChild)
                    v_dount_view.startAnim()
                    adapter.listener = object : ItemChoosseListener {
                        override fun choose(index: Int) {
                            var b=FG_BaseAnalysis.createBundle(energyClassificationId,it.message.analysisAreaParamDtoList[index].areaId.toString())
                            startAC(FG_AreaAnalysis::class.java.name,b)

                        }
                    }
                    lv_list.adapter = adapter
                    lv_list.setOnItemClickListener { adapterView, view, i, l ->
                        var item=it.message.analysisAreaParamDtoList[i]
                        var bundle= FG_AreaDetailAnalysis.createBundle(energyClassificationId,item.areaId,item.areaName)
                        startAC(FG_AreaDetailAnalysis::class.java.name,bundle)
                    }
                    v_dount_view.charRender(getValues(k))
                    if (index==3){
                        tv_time.text = it.message.dateString
                    }
                }
            }

            override fun onError(error: ErrorBody?) {
                toast(error)
            }

        })
    }

    private fun getList(it: BN_AreaPage): List<Model> {
        var list=ArrayList<Model>()
        for (item in it.message.analysisAreaParamDtoList){
            var model= Model()
            model.name=item.areaName
            model.ratio=item.areaRatio
            model.kmh=item.areaKwh
            model.isLeaf=item.isLeaf
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