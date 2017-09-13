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
 * 单一设备柱状图分析
 */
class FG_DeviceDetailAnalysis :FG_BaseAnalysis(){
    override fun init() {
    }

    override fun getAdapter(): PagerAdapter=AD_DeviceAnalysis(activity,childFragmentManager,parentId)

    override fun getTitle(): String="设备分析"
    class AD_DeviceAnalysis(ac: Activity, fm: FragmentManager, parentId:String) : AD_BaseDevicesAnalysis(ac,fm){
        var parentId:String?=null
        init {
            this.parentId=parentId
            initFragment()
        }
        override fun getBundle(index:Int): Bundle {
            return FG_BaseAnlysisPage.createBundle(index, parentId?:"-1")

        }
        override fun getContentFragment(): FG_BaseDevicesAnlysisPage {
            return FG_DevicesDetailAnalysisPage()
        }
    }

}