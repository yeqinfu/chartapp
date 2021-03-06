/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home.mine.energyanalysis

import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.mine.energyanalysis.BN_CatePage
import com.ppandroid.app.home.mine.energyanalysis.devicesanalysis.FG_CateDetailAnalysis
import com.ppandroid.app.home.mine.energyanalysis.devicesanalysis.FG_DeviceDetailAnalysis
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.app.utils.Devices
import kotlinx.android.synthetic.main.fg_base_analysis_page.*

/**
 * Created by yeqinfu on 2017/8/31.
 */
class FG_CatePage :FG_BaseAnlysisPage(){

    override fun loadContent() {
        var url=if (energyClassificationId==Devices.ELECTRIC){
            "user/energy/analysis/getCate.json?dateString=$select"
        }else{
            "user/water/analysis/getCate.json?dateString=$select"
        }

        if (parentId!="-1"){
            url+="&parentId=$parentId"
        }
        Http.get(activity,url, BN_CatePage::class.java,object : MyCallBack<BN_CatePage> {
            override fun onResponse(response: BN_CatePage?) {
                response?.let {
                    if (!isAdded){
                        return
                    }
                    tv_totalKwh?.text=it.message.analysisCateSum
                    var k=getList(it)
                    var adapter=AD_List(activity,k,isHaveChild)
                    v_dount_view?.startAnim()
                    adapter.listener=object :ItemChoosseListener{
                        override fun choose(index: Int) {
                            var b=FG_BaseAnalysis.createBundle(energyClassificationId,it.message.analysisCateParamDtoList[index].cateId.toString())
                            startAC(FG_CateAnalysis::class.java.name,b)
                        }
                    }
                    lv_list?.adapter=adapter
                    lv_list?.setOnItemClickListener { adapterView, view, i, l ->
                        var item=it.message.analysisCateParamDtoList[i]
                        var bundle= FG_DeviceDetailAnalysis.createBundle(energyClassificationId,item.cateId,item.cateName)
                        startAC(FG_CateDetailAnalysis::class.java.name,bundle)
                    }

                    v_dount_view?.charRender(getValues(k))
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

    private fun getList(it: BN_CatePage): List<Model> {
        var list=ArrayList<Model>()
        for (item in it.message.analysisCateParamDtoList){
            var model=Model()
            model.name=item.cateName
            model.ratio=item.cateRatio
            model.kmh=item.cateKwh
            model.isLeaf=item.isLeaf
            list.add(model)
        }
        return list
    }

    override fun init() {
       setText()


    }



}