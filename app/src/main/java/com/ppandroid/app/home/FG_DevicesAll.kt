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
    companion object {
        fun createBundle(selectTab:Int):Bundle{
            var b=Bundle()
            b.putInt("selectTab",selectTab)
            return b
        }
    }
    override fun fgRes(): Int= R.layout.fg_devices_all

    var selectTab=0
    override fun afterViews() {
        arguments?.let {
            selectTab=it.getInt("selectTab",0)
        }
        var adapter = AD_DevicesAll(childFragmentManager)
        view_pager.adapter = adapter
        view_pager.offscreenPageLimit = 3
        view_pager.currentItem = selectTab
        sliding_tabs.setupWithViewPager(view_pager)
        sliding_tabs.tabMode = TabLayout.MODE_FIXED
        adapter.notifyDataSetChanged()
    }

    class AD_DevicesAll(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        var titles = arrayOf(
                "用电设备",
                "用水设备",
                "温湿度设备"
        )

        override fun getItem(position: Int): Fragment {
            var b= Bundle()
            if (position==0){
                b.putString("energyClassificationId","1")
            }else if (position==1){
                b.putString("energyClassificationId","2")
            }else{
                b.putString("energyClassificationId","3")
            }

            var fg=FG_Devices()
            fg.arguments=b
            return fg
        }

        override fun getCount(): Int = 3

        override fun getPageTitle(position: Int): CharSequence {
            return titles[position]
        }

    }
}