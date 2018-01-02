/*
 * Created by yeqinfu on 18-1-2 下午2:44
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home.mine.energyanalysis

import com.ppandroid.app.R
import com.ppandroid.app.home.mine.systemsetting.FG_SystemSetting
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_energy_analysis_all.*
import kotlinx.android.synthetic.main.layout_head_view.*

/**
 * Created by yeqinfu on 2018/1/2.
 */
class FG_EnergyAnalysisAll : FG_Base() {
    override fun fgRes(): Int= R.layout.fg_energy_analysis_all

    override fun afterViews() {
        head_view.setCenterTitle("能耗分析")
        head_view.init(activity)
        ll_01.setOnClickListener {
            startAC(FG_EnergyAnalysis::class.java.name)
        }
        ll_02.setOnClickListener {
            var b= FG_SystemSetting.createBundle("2")
            startAC(FG_EnergyAnalysis::class.java.name,b)
        }

    }
}