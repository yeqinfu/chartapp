/*
 * Created by yeqinfu on 17-12-7 下午3:16
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.demo

import android.app.Activity
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.ppandroid.app.R
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_test.*


/**
 * Created by yeqinfu on 2017/12/4.
 */
class FG_Test :FG_Base(){
    override fun fgRes(): Int= R.layout.fg_test

    override fun afterViews() {

        var adapter= AD_Test(activity, childFragmentManager)

        view_pager2.adapter=adapter


    }
    class AD_Test(ac: Activity, fm: FragmentManager) : FragmentStatePagerAdapter(fm){
        override fun getItem(position: Int): Fragment= FG_TestPage()
        override fun getCount(): Int=3
    }
}