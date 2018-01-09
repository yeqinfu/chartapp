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
import com.ppandroid.app.utils.Devices

/**
 * Created by yeqinfu on 2017/8/31.
 * 区域用电
 */
class FG_AreaAnalysis : FG_BaseAnalysis(){


    override fun init() {

    }

    override fun getAdapter(): PagerAdapter =AD_Cate(energyClassificationId,activity,childFragmentManager,parentId)

    override fun getTitle(): String{
        return if (energyClassificationId== Devices.ELECTRIC){
            "区域用电"
        }else{
            "区域用水"
        }
    }

    class AD_Cate(energyClassificationId:String,ac: Activity, fm: FragmentManager, parentId:String) : AD_BaseAnalysis(ac,fm){
        var parentId:String?=null
        private var energyClassificationId=energyClassificationId
        init {
            this.parentId=parentId
            initFragment()
        }
        override fun getBundle(index:Int): Bundle {
            return FG_BaseAnlysisPage.createBundle(energyClassificationId,index, parentId?:"-1")

        }
        override fun getContentFragment(): FG_BaseAnlysisPage {
            return FG_AreaPage()
        }
    }

}