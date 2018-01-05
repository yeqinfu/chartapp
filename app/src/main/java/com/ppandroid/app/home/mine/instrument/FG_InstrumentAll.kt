/*
 * Created by yeqinfu on 18-1-2 下午2:27
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home.mine.instrument

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.ppandroid.app.R
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_instrument_all.*
import kotlinx.android.synthetic.main.layout_head_view.*

/**
 * Created by yeqinfu on 2018/1/2.
 */
class FG_InstrumentAll : FG_Base() {
    override fun fgRes(): Int= R.layout.fg_instrument_all

    override fun afterViews() {
        head_view.init(activity)
        head_view.setCenterTitle("仪表检测")
        var adapter = AD_InstrumentAll(childFragmentManager)
        view_pager.adapter = adapter
        view_pager.offscreenPageLimit = 2
        sliding_tabs.setupWithViewPager(view_pager)
        sliding_tabs.tabMode = TabLayout.MODE_FIXED
        adapter.notifyDataSetChanged()
    }

    class AD_InstrumentAll(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        var titles = arrayOf(
                "用电仪表",
                "用水仪表",
                "温湿度仪表"
        )

        override fun getItem(position: Int): Fragment {
            var b= Bundle()
            if (position==0){
                b.putString("energyClassificationId","1")
            }else if (position==1){
                b.putString("energyClassificationId","2")
            }else {
                b.putString("energyClassificationId", "3")
            }
            var fg=FG_InstrumentList()
            fg.arguments=b
            return fg
        }

        override fun getCount(): Int = 3

        override fun getPageTitle(position: Int): CharSequence {
            return titles[position]
        }

    }
}