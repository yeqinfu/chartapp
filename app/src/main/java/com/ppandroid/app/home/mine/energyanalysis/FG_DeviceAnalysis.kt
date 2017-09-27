/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home.mine.energyanalysis

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.view.PagerAdapter
import com.ppandroid.app.home.mine.adapter.AD_BaseAnalysis

/**
 * Created by yeqinfu on 2017/8/31.
 * 设备用电
 */
class FG_DeviceAnalysis : FG_BaseAnalysis(){

    override fun init() {

    }

    override fun getAdapter(): PagerAdapter {
        return AD_Cate(activity,childFragmentManager,parentId)
    }

    override fun getTitle(): String="设备用电"

    class AD_Cate(ac: Activity, fm: FragmentManager, parentId:String) : AD_BaseAnalysis(ac,fm){
        var parentId:String?=null
        init {
            this.parentId=parentId
            initFragment()
        }
        override fun getBundle(index:Int): Bundle {
            return FG_BaseAnlysisPage.createBundle(index, parentId ?: "-1")

        }
        override fun getContentFragment(): FG_BaseAnlysisPage {
            return FG_DevicesPage()
        }
    }

}