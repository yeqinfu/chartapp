/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home.mine.energyanalysis.horizontalanalysis

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.ppandroid.app.home.mine.adapter.AD_BaseHorizontalAnalysis

/**
 * Created by yeqinfu on 2017/9/15.
 * 横向柱状图分析
 */
class FG_InstrumentHistogramAnalysis : FG_BaseHorizontalanalysis(){
    override fun init() {
    }

    override fun getAdapter(): FragmentStatePagerAdapter {
        return AD_HistogramAnalysis(energyClassificationId,activity,childFragmentManager,parentId)
    }

    class AD_HistogramAnalysis(energyClassificationId:String,ac: Activity, fm: FragmentManager, parentId:String) : AD_BaseHorizontalAnalysis(ac,fm){
        var parentId:String?=null
        var energyClassificationId:String=energyClassificationId
        init {
            this.parentId=parentId
            initFragment()
        }
        override fun getBundle(index:Int): Bundle {
            return FG_BaseHistoramAnalysisPage.createBundle(energyClassificationId,index, parentId ?: "-1")

        }
        override fun getContentFragment(): FG_BaseHistoramAnalysisPage {
            return FG_InstrumentHistogramAnalysisPage()
        }
    }

}