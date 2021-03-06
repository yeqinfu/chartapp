/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home.devices

import android.graphics.Typeface
import android.os.Bundle
import com.ppandroid.app.R
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_devices_info.*
import kotlinx.android.synthetic.main.layout_head_view.*

/**
 * Created by yeqinfu on 2017/9/11.
 * 设备详情
 */
class FG_DevicesInfo :FG_Base(){
    var energyClassificationId="1"

    companion object {
        fun createIntent(energyClassificationId:String,name:String,deviceId:String): Bundle {
            var b=Bundle()
            b.putString("name",name)
            b.putString("energyClassificationId",energyClassificationId)
            b.putString("deviceId",deviceId)
            return b
        }
    }
    override fun fgRes(): Int= R.layout.fg_devices_info

    var name:String=""
    var deviceId:String=""

    override fun afterViews() {
        arguments?.let {
            name=it.getString("name","")
            deviceId=it.getString("deviceId","")
            energyClassificationId=it.getString("energyClassificationId","1")
        }
        head_view.setCenterTitle(name)
        head_view.init(activity)

        var adapter = AD_DevicesInfo(activity, childFragmentManager,deviceId,energyClassificationId)
        view_pager.adapter = adapter
        adapter.notifyDataSetChanged()
        title_indicator.setViewPager(view_pager)
        val density = resources.displayMetrics.density
        title_indicator.setTabSelectedTextColorResource(R.color.color_01)
        title_indicator.setIndicatorColorResource(R.color.color_01)
        title_indicator.setTypeface(null, Typeface.NORMAL)
        title_indicator.textSize = (14 * density).toInt()
    }

}