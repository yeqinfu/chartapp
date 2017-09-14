package com.ppandroid.app.home.mine.energyanalysis.devicesanalysis

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.view.PagerAdapter
import com.ppandroid.app.home.mine.adapter.AD_BaseDevicesAnalysis
import com.ppandroid.app.home.mine.energyanalysis.FG_BaseAnalysis
import com.ppandroid.app.home.mine.energyanalysis.FG_BaseAnlysisPage

/**
 * Created by yeqinfu on 2017/9/13.
 * 单一分项柱状图分析
 */
class FG_CateDetailAnalysis : FG_BaseAnalysis(){

    companion object {
        fun createBundle(parentId:String,title:String): Bundle {
            var b = Bundle()
            b.putString("parentId",parentId)
            b.putString("title",title)
            return b
        }
    }
    override fun init() {
    }

    override fun getAdapter(): PagerAdapter =AD_DeviceAnalysis(activity,childFragmentManager,parentId)

    override fun getTitle(): String{
        var title=""
        arguments?.let {
            title=it.getString("title","")
        }
        return title
    }
    class AD_DeviceAnalysis(ac: Activity, fm: FragmentManager, parentId:String) : AD_BaseDevicesAnalysis(ac,fm){
        var parentId:String?=null
        init {
            this.parentId=parentId
            initFragment()
        }
        override fun getBundle(index:Int): Bundle {
            return FG_BaseAnlysisPage.createBundle(index, parentId ?: "-1")

        }
        override fun getContentFragment(): FG_BaseDevicesAnlysisPage {
            return FG_CateDetailAnalysisPage()
        }
    }

}