package com.ppandroid.app.home.mine.energyanalysis

import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.mine.energyanalysis.BN_CatePage
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import kotlinx.android.synthetic.main.fg_base_analysis_page.*

/**
 * Created by yeqinfu on 2017/8/31.
 */
class FG_CatePage :FG_BaseAnlysisPage(){

    override fun loadContent() {
        var url="user/energy/analysis/getCate.json?dateString=$select"
        if (parentId!="-1"){
            url+="&parentId=$parentId"
        }
        Http.get(activity,url, BN_CatePage::class.java,object : MyCallBack<BN_CatePage> {
            override fun onResponse(response: BN_CatePage?) {
                response?.let {
                    tv_totalKwh.text=it.message.analysisCateSum
                    var adapter=AD_List(activity,getList(it),isHaveChild)
                    v_dount_view.startAnim()
                    adapter.listener=object :ItemChoosseListener{
                        override fun choose(index: Int) {
                            var b=FG_BaseAnalysis.createBundle(it.message.analysisCateParamDtoList[index].id.toString())
                            startAC(FG_CateAnalysis::class.java.name,b)
                        }
                    }
                    lv_list.adapter=adapter
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
        when(index){
            0->  tv_type.text="今日用电"
            1->  tv_type.text="本月用电"
            2->  tv_type.text="本年用电"
            3->  tv_type.text="总用电"
        }

    }



}