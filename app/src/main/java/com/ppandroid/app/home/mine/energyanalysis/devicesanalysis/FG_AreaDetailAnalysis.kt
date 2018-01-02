/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home.mine.energyanalysis.devicesanalysis

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.view.PagerAdapter
import com.ppandroid.app.R
import com.ppandroid.app.home.mine.adapter.AD_BaseDevicesAnalysis
import com.ppandroid.app.home.mine.energyanalysis.FG_BaseAnalysis
import com.ppandroid.app.home.mine.energyanalysis.FG_BaseAnlysisPage
import com.ppandroid.app.home.mine.energyanalysis.horizontalanalysis.AC_AreaHorChart
import kotlinx.android.synthetic.main.layout_head_view.*

/**
 * Created by yeqinfu on 2017/9/13.
 * 单一区域柱状图分析
 */
class FG_AreaDetailAnalysis : FG_BaseAnalysis(){

    companion object {
        fun createBundle(energyClassificationId:String,parentId:String,title:String): Bundle {
            var b = Bundle()
            b.putString("energyClassificationId",energyClassificationId)
            b.putString("parentId",parentId)
            b.putString("title",title)
            return b
        }
    }
    override fun init() {
        //横向分析图
        head_view.setIvRight(R.drawable.ic_fanzhuan){
            var it= Intent()
            it.setClass(activity, AC_AreaHorChart::class.java)
            startActivity(it)
        }
    }

    override fun getAdapter(): PagerAdapter =AD_DeviceAnalysis(energyClassificationId,activity,childFragmentManager,parentId)

    override fun getTitle(): String{
        var title=""
        arguments?.let {
            title=it.getString("title","")
        }
        return title
    }
    class AD_DeviceAnalysis(energyClassificationId:String,ac: Activity, fm: FragmentManager, parentId:String) : AD_BaseDevicesAnalysis(ac,fm){
        private var energyClassificationId=energyClassificationId
        var parentId:String?=null
        init {
            this.parentId=parentId
            initFragment()
        }
        override fun getBundle(index:Int): Bundle {
            return FG_BaseAnlysisPage.createBundle(energyClassificationId,index, parentId ?: "-1")

        }
        override fun getContentFragment(): FG_BaseDevicesAnlysisPage {
            return FG_AreaDetailAnalysisPage()
        }
    }

}