package com.ppandroid.app.home.mine.aboutme

import com.ppandroid.app.R
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.layout_head_view.*

/**
 * Created by yeqinfu on 2017/8/24.
 */
class FG_AboutMe :FG_Base(){
    override fun fgRes(): Int= R.layout.fg_about_me

    override fun afterViews() {
        head_view.init(activity)
        head_view.setCenterTitle("关于节能通")

    }

}