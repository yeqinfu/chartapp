/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home.mine.energyanalysis.totalhorzizontalanalysis

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.ppandroid.app.R
import com.ppandroid.app.home.mine.adapter.AD_BaseHuanTong
import com.ppandroid.app.home.mine.energyanalysis.horizontalanalysis.FG_BaseHorizontalanalysis

/**
 * Created by yeqinfu on 2017/9/18.
 */
class FG_Tong : FG_BaseHorizontalanalysis(){
    override fun init() {
    }

    override fun getAdapter(): FragmentStatePagerAdapter {
        return AD_TongPage (activity,childFragmentManager,parentId)
    }

    class AD_TongPage(ac: Activity, fm: FragmentManager, parentId:String) : AD_BaseHuanTong(ac,fm){
        override fun initFragment() {
            this.arrays_title = mContext.resources.getStringArray(R.array.fg_base_h_analysis4)
            fragments = arrayOfNulls(arrays_title.size)
            fragments[0] = contentFragment//日
            fragments[0].arguments = getBundle(0)
            fragments[1] = contentFragment//周
            fragments[1].arguments = getBundle(1)
            fragments[2] = contentFragment//月
            fragments[2].arguments = getBundle(2)
        }

        override fun getContentFragment(): FG_BaseLineChart=FG_TongPage()

        var parentId=parentId
        init {
            initFragment()
        }
        override fun getBundle(index:Int): Bundle {
            return FG_BaseLineChart.createBundle(index)

        }



    }



}