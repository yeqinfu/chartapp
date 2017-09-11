package com.ppandroid.app.home.mine.systemsetting

import android.graphics.Typeface
import android.support.v4.view.ViewPager
import com.ppandroid.app.R
import com.ppandroid.app.base.AC_ContentFG
import com.ppandroid.app.home.mine.adapter.AD_SystemSetting
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_system_setting.*
import kotlinx.android.synthetic.main.layout_head_view.*

/**
 * Created by yeqinfu on 2017/8/22.
 * 系统设置
 */
class FG_SystemSetting : FG_Base() {

    override fun fgRes(): Int = R.layout.fg_system_setting

    override fun afterViews() {
        (activity as AC_ContentFG).setEnablePullToBack(false)
        head_view.setCenterTitle("系统设置")
        head_view.init(activity)
        head_view.setIvRight(R.drawable.ic_add_model, {
            addModelByPosition()
        })
        var adapter = AD_SystemSetting(activity, childFragmentManager)
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
        when (Position) {
            0 -> {//仪表添加
                startAC(FG_AddInstrument::class.java.name)
            }
            1->{//分项添加
                startAC(FG_AddDeviceCate::class.java.name)
            }
            2->{//区域添加
                startAC(FG_AddDeviceArea::class.java.name)
            }
            3->{//设备添加
                startAC(FG_AddDevices::class.java.name)
            }
            4->{//能源添加
                startAC(FG_AddEnergyCharging::class.java.name)
            }
        }
    }

    private var Position: Int = 0

}