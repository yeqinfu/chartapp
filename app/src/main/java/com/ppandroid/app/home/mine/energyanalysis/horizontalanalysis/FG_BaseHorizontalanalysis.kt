package com.ppandroid.app.home.mine.energyanalysis.horizontalanalysis

import android.graphics.Typeface
import android.support.v4.view.PagerAdapter
import com.ppandroid.app.R
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_base_horizontal_analysis.*

/**
 * Created by yeqinfu on 2017/9/15.
 * 横向页面基本框架
 */
open abstract class FG_BaseHorizontalanalysis:FG_Base(){
    override fun fgRes(): Int= R.layout.fg_base_horizontal_analysis

    override fun afterViews() {
        init()
        var adapter =getAdapter()
        view_pager.adapter = adapter
        adapter.notifyDataSetChanged()
        title_indicator.setViewPager(view_pager)
        val density = resources.displayMetrics.density
        title_indicator.setTabSelectedTextColorResource(R.color.color_01)
        title_indicator.setIndicatorColorResource(R.color.color_01)
        title_indicator.setTypeface(null, Typeface.NORMAL)
        title_indicator.textSize = (14 * density).toInt()
    }
    abstract fun init()

    abstract fun getAdapter(): PagerAdapter


}