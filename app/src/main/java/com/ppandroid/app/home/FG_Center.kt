package com.ppandroid.app.home

import com.ppandroid.app.R
import com.ppandroid.im.base.FG_Base

/**
 * Created by yeqinfu on 2017/8/3.
 */
class FG_Center: FG_Base() {
    override fun fgRes(): Int= R.layout.fg_center

    override fun afterViews() {
        startAC(FG_SecurityCenter::class.java.name)
        finish()
    }


}