package com.ppandroid.app.home.mine.energyanalysis

import com.ppandroid.app.R
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.layout_head_view.*

/**
 * Created by yeqinfu on 2017/8/30.
 */
class FG_DeviceCateAnalysis:FG_Base(){
    override fun fgRes(): Int= R.layout.fg_device_cate_analysis

    override fun afterViews() {
        head_view.init(activity)
        head_view.setCenterTitle("分项用电")
    }

}