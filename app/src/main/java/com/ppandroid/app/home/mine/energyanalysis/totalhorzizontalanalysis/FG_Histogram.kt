package com.ppandroid.app.home.mine.energyanalysis.totalhorzizontalanalysis

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.ppandroid.app.R
import com.ppandroid.app.home.mine.adapter.AD_BaseHorizontalAnalysis
import com.ppandroid.app.home.mine.energyanalysis.horizontalanalysis.FG_BaseHistoramAnalysisPage
import com.ppandroid.app.home.mine.energyanalysis.horizontalanalysis.FG_BaseHorizontalanalysis

/**
 * Created by yeqinfu on 2017/9/18.
 */
class FG_Histogram : FG_BaseHorizontalanalysis(){
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
            return FG_HistogramPage()
        }

        override fun initFragment() {
            this.arrays_title = mContext.resources.getStringArray(R.array.fg_base_h_analysis2)
            fragments = arrayOfNulls(arrays_title.size)
            fragments[0] = contentFragment//日
            fragments[0].arguments = getBundle(0)
            fragments[1] = contentFragment//周
            fragments[1].arguments = getBundle(4)
            fragments[2] = contentFragment//月
            fragments[2].arguments = getBundle(1)
            fragments[3] = contentFragment//年
            fragments[3].arguments = getBundle(2)
            fragments[4] = contentFragment//总
            fragments[4].arguments = getBundle(3)
        }
    }


}