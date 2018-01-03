/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home.mine.energyanalysis.horizontalanalysis

import android.support.v4.app.Fragment
import com.ppandroid.app.R
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.mine.energyanalysis.BN_AreaList
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack

/**
 * Created by yeqinfu on 2017/9/16.
 */
class AC_InstrumentHorChart : AC_HorChart(){
    override fun getTopDrawable(i: Int): Int= R.drawable.zuzt
    override fun init() {
        loadContent()
    }

    private var list: List<BN_AreaList.MessageBean>? = null
    private fun loadContent() {
        var url=if (energyClassificationId=="1"){
            "user/energy/analysis/getInstrumentList.json"
        }else{
            "user/water/analysis/getInstrumentList.json"
        }
        Http.get(this, url, BN_AreaList::class.java, object : MyCallBack<BN_AreaList> {
            override fun onResponse(response: BN_AreaList?) {
                response?.let {
                    list = it.message
                    defaultCountPage = it.message.size
                    setContent()
                }
            }

            override fun onError(error: ErrorBody?) {
                toast(error)
            }

        })
    }

    override fun getTitlePage(i: Int): String {
        list?.let {
            return it[i].name
        }
        return ""
    }


    override fun getFragmentPage(i: Int): Fragment {
        var fg= FG_InstrumentHistogramAnalysis()
        list?.let {
            var b= FG_BaseHorizontalanalysis.createBundle(energyClassificationId,it[i].id.toString())
            fg.arguments=b
        }
        return fg
    }

    private var defaultCountPage=0
    /**
     * 返回右边栏的总数
     */
    override fun getCountPage(): Int=defaultCountPage

}