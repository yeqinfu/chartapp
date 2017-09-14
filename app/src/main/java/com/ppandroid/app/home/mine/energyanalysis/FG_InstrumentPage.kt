package com.ppandroid.app.home.mine.energyanalysis

import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.mine.energyanalysis.BN_InstrumentPage
import com.ppandroid.app.home.mine.energyanalysis.devicesanalysis.FG_InstrumentDetailAnalysis
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import kotlinx.android.synthetic.main.fg_base_analysis_page.*

/**
 * Created by yeqinfu on 2017/8/31.
 */
class FG_InstrumentPage : FG_BaseAnlysisPage(){

    override fun loadContent() {
        var url="user/energy/analysis/getInstrument.json?dateString=$select"
        if (parentId!="-1"){
            url+="&parentId=$parentId"
        }
        isHaveChild=false
        Http.get(activity, url, BN_InstrumentPage::class.java, object : MyCallBack<BN_InstrumentPage> {
            override fun onResponse(response: BN_InstrumentPage?) {
                response?.let {
                    var k=getList(it)
                    tv_totalKwh.text = it.message.analysisDeviceSum
                    var adapter = AD_List(activity, k, isHaveChild)
                    v_dount_view.startAnim()
                    lv_list.adapter = adapter
                    lv_list.setOnItemClickListener { adapterView, view, i, l ->
                        var item=it.message.analysisInstrumentParamDtoList[i]
                        var bundle= FG_InstrumentDetailAnalysis.createBundle(item.instrumentId,item.instrumentName)
                        startAC(FG_InstrumentDetailAnalysis::class.java.name,bundle)
                    }
                    v_dount_view.charRender(getValues(k))
                }
            }

            override fun onError(error: ErrorBody?) {
                toast(error)
            }

        })
    }

    private fun getList(it: BN_InstrumentPage): List<Model> {
        var list=ArrayList<Model>()
        for (item in it.message.analysisInstrumentParamDtoList){
            var model= Model()
            model.name=item.instrumentName
            model.ratio=item.instrumentRatio
            model.kmh=item.instrumentKwh
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