/*
 * Created by yeqinfu on 18-1-2 上午9:38
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home.mine.systemsetting

import com.ppandroid.app.R
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_system_setting_all.*
import kotlinx.android.synthetic.main.layout_head_view.*

/**
 * Created by yeqinfu on 2018/1/2.
 */
class FG_SystemSettingAll : FG_Base() {
    override fun fgRes(): Int= R.layout.fg_system_setting_all

    override fun afterViews() {
        head_view.setCenterTitle("系统设置")
        head_view.init(activity)
        ll_01.setOnClickListener {
            startAC(FG_SystemSetting::class.java.name)
        }
        ll_02.setOnClickListener {
            var b=FG_SystemSetting.Companion.createBundle("2")
            startAC(FG_SystemSetting::class.java.name,b)
        }

    }
}