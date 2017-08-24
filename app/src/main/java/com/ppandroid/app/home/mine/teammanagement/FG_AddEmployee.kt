package com.ppandroid.app.home.mine.teammanagement

import com.ppandroid.app.R
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.layout_head_view.*

/**
 * Created by yeqinfu on 2017/8/24.
 */
class FG_AddEmployee:FG_Base(){
    override fun fgRes(): Int= R.layout.fg_add_employee

    override fun afterViews() {
        head_view.init(activity)
        head_view.setCenterTitle("添加员工")
    }

}