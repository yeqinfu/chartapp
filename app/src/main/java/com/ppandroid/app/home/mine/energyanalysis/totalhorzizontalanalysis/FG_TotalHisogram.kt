package com.ppandroid.app.home.mine.energyanalysis.totalhorzizontalanalysis

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.ppandroid.app.home.mine.adapter.AD_BaseHorizontalAnalysis
import com.ppandroid.app.home.mine.energyanalysis.horizontalanalysis.FG_BaseHistoramAnalysisPage
import com.ppandroid.app.home.mine.energyanalysis.horizontalanalysis.FG_BaseHorizontalanalysis
import com.ppandroid.app.home.mine.energyanalysis.horizontalanalysis.FG_CateHistogramAnalysisPage

/**
 * Created by yeqinfu on 2017/9/18.
 */
class FG_TotalHisogram : FG_BaseHorizontalanalysis(){
    override fun init() {
    }

    override fun getAdapter(): FragmentStatePagerAdapter {
        return AD_TotalHisogramPage (activity,childFragmentManager,parentId)
    }

    class AD_TotalHisogramPage(ac: Activity, fm: FragmentManager, parentId:String) : AD_BaseHorizontalAnalysis(ac,fm){
        var parentId:String?=null
        init {
            this.parentId=parentId
            initFragment()
        }
        override fun getBundle(index:Int): Bundle {
            return FG_BaseHistoramAnalysisPage.createBundle(index, parentId?:"-1")

        }
        override fun getContentFragment(): FG_BaseHistoramAnalysisPage {
            return FG_CateHistogramAnalysisPage()
        }
    }


}