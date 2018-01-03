/*
 * Created by yeqinfu on 18-1-3 上午9:20
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.ppandroid.app.R
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_devices_all.*

/**
 * Created by yeqinfu on 2018/1/3.
 */
class FG_DevicesAll:FG_Base() {
    override fun fgRes(): Int= R.layout.fg_devices_all

    override fun afterViews() {
        var adapter = AD_DevicesAll(childFragmentManager)
        view_pager.adapter = adapter
        view_pager.offscreenPageLimit = 3
        sliding_tabs.setupWithViewPager(view_pager)
        sliding_tabs.tabMode = TabLayout.MODE_FIXED
        adapter.notifyDataSetChanged()
    }

    class AD_DevicesAll(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        var titles = arrayOf(
                "用电设备",
                "用水设备"
        )

        override fun getItem(position: Int): Fragment {
            var b= Bundle()
            if (position==0){
                b.putString("energyClassificationId","1")
            }else{
                b.putString("energyClassificationId","2")
            }

            var fg=FG_Devices()
            fg.arguments=b
            return fg
        }

        override fun getCount(): Int = 2

        override fun getPageTitle(position: Int): CharSequence {
            return titles[position]
        }

    }
}