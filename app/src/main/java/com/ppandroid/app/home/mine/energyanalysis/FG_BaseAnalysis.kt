/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home.mine.energyanalysis

import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import com.ppandroid.app.R
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_base_analysis.*
import kotlinx.android.synthetic.main.layout_head_view.*

/**
 * Cread by yeqinfu on 2017/8/31.
 */
open abstract class FG_BaseAnalysis :FG_Base(){
    protected var parentId="-1"
    protected var energyClassificationId="1"
    companion object {
        fun createBundle(energyClassificationId:String,parentId:String): Bundle {
            var b =Bundle()
            b.putString("parentId",parentId)
            b.putString("energyClassificationId",energyClassificationId)
            return b
        }
    }
    override fun fgRes(): Int= R.layout.fg_base_analysis

    override fun afterViews() {
        arguments?.let {
            parentId=it.getString("parentId","-1")
            energyClassificationId=it.getString("energyClassificationId","1")
        }
        init()
        head_view.init(activity)
        head_view.setCenterTitle(getTitle())
        var adapter =getAdapter()
        view_pager.adapter = adapter
        adapter.notifyDataSetChanged()
        title_indicator.setViewPager(view_pager)
        val density = resources.displayMetrics.density
        title_indicator.setTabSelectedTextColorResource(R.color.color_01)
        title_indicator.setIndicatorColorResource(android.R.color.transparent)
        title_indicator.setTypeface(null, Typeface.NORMAL)
        title_indicator.textSize = (14 * density).toInt()
        title_indicator.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {

            }

        })
    }

    abstract fun init()

    abstract fun getAdapter(): PagerAdapter


    abstract fun getTitle(): String?


}