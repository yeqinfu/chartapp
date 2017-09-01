package com.ppandroid.app.home.news

import com.ppandroid.app.R
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.layout_head_view.*

/**
 * Created by yeqinfu on 2017/9/1.
 */
class FG_EnergyList :FG_Base(){
    override fun fgRes(): Int= R.layout.fg_energy_list

    override fun afterViews() {
        head_view.init(activity)
        head_view.setCenterTitle("能耗汇总")
    }

}