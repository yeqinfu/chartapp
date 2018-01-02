/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home.mine.systemsetting

import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.view.ViewPager
import com.ppandroid.app.R
import com.ppandroid.app.home.mine.adapter.AD_SystemSetting
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_system_setting.*
import kotlinx.android.synthetic.main.layout_head_view.*

/**
 * Created by yeqinfu on 2017/8/22.
 * 系统设置
 */
class FG_SystemSetting : FG_Base() {

    companion object {
        fun createBundle(energyClassificationId:String):Bundle{
            var b= Bundle()
            b.putString("energyClassificationId",energyClassificationId)
            return b
        }
    }
    //电 1水2温湿度3监控4
    private var energyClassificationId="1"
    override fun fgRes(): Int = R.layout.fg_system_setting

    override fun afterViews() {
        arguments?.let {
            energyClassificationId=it.getString("energyClassificationId","1")
        }
        head_view.setCenterTitle("系统设置")
        head_view.init(activity)
        head_view.setIvRight(R.drawable.ic_add_model, {
            addModelByPosition()
        })
        var adapter = AD_SystemSetting(activity, childFragmentManager,energyClassificationId)
        view_pager.adapter = adapter
        adapter.notifyDataSetChanged()
        title_indicator.setViewPager(view_pager)
        val density = resources.displayMetrics.density
        title_indicator.setTabSelectedTextColorResource(R.color.color_01)
        title_indicator.setIndicatorColorResource(R.color.color_01)
        title_indicator.setTypeface(null, Typeface.NORMAL)
        title_indicator.textSize = (14 * density).toInt()
        title_indicator.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                Position = position
            }

        })
    }

    private fun addModelByPosition() {
        var b=createBundle()
        when (Position) {
            0 -> {//仪表添加
                startAC(FG_AddInstrument::class.java.name,b)
            }
            1->{//分项添加
                startAC(FG_AddDeviceCate::class.java.name,b)
            }
            2->{//区域添加
                startAC(FG_AddDeviceArea::class.java.name,b)
            }
            3->{//设备添加
                startAC(FG_AddDevices::class.java.name,b)
            }
            4->{//能源添加
                startAC(FG_AddEnergyCharging::class.java.name,b)
            }
        }
    }

    private var Position: Int = 0
    fun createBundle(): Bundle {
        val b = Bundle()
        b.putString("energyClassificationId", energyClassificationId)
        return b
    }

}