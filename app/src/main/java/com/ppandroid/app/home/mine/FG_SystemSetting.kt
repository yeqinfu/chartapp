package com.ppandroid.app.home.mine

import android.graphics.Typeface
import com.ppandroid.app.R
import com.ppandroid.app.home.mine.adapter.AD_SystemSetting
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_system_setting.*
import kotlinx.android.synthetic.main.layout_head_view.*

/**
 * Created by yeqinfu on 2017/8/22.
 * 系统设置
 */
class FG_SystemSetting:FG_Base(){

    override fun fgRes(): Int= R.layout.fg_system_setting

    override fun afterViews() {
        head_view.setCenterTitle("系统设置")
        head_view.init(activity)
        var adapter=AD_SystemSetting(activity,childFragmentManager)
        view_pager.setAdapter(adapter)
        adapter.notifyDataSetChanged()
        title_indicator.setViewPager(view_pager)
        val density = resources.displayMetrics.density
        title_indicator.setTabSelectedTextColorResource(R.color.color_01)
        title_indicator.setIndicatorColorResource(R.color.color_01)
        title_indicator.setTypeface(null, Typeface.NORMAL)
        title_indicator.setTextSize((14 * density).toInt())
    }

}