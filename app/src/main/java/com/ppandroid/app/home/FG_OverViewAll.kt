/*
 * Created by yeqinfu on 17-12-8 下午3:49
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home

import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.ppandroid.app.R
import com.ppandroid.app.home.overview.FG_WaterPage
import com.ppandroid.app.utils.info.Utils_UserInfo
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_over_view_all.*

/**
 * Created by yeqinfu on 2017/12/8.
 * 新的overview
 */

class FG_OverViewAll : FG_Base() {
    override fun fgRes(): Int = R.layout.fg_over_view_all

    override fun afterViews() {
        tv_company_name.text= Utils_UserInfo.getCompanyName(activity)
        var adapter = AD_OverView(childFragmentManager)
        view_pager.adapter = adapter
        view_pager.offscreenPageLimit=3
        sliding_tabs.setupWithViewPager(view_pager)
        sliding_tabs.tabMode=TabLayout.MODE_FIXED
        adapter.notifyDataSetChanged()

    }

    class AD_OverView(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        var titles = arrayOf(
                "能耗信息",
                "煤气用量",
                "生活用水"
        )

        override fun getItem(position: Int): Fragment {
            if (position == 0) {
                return FG_OverView()
            } else {
                return FG_WaterPage()
            }
        }

        override fun getCount(): Int = 3

        override fun getPageTitle(position: Int): CharSequence {
            return titles[position]
        }

    }

}
