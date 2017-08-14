package com.ppandroid.app.home.overview

import com.ppandroid.app.R
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_over_view_config.*
import kotlinx.android.synthetic.main.layout_head_view.*
import java.util.*

/**
 * Created by yeqinfu on 2017/8/14.
 */
class FG_OverViewConfig:FG_Base(){
    override fun fgRes(): Int= R.layout.fg_over_view_config

    override fun afterViews() {
        head_view.setCenterTitle("自定义模块")
        head_view.init(activity)
        head_view.setRightText("保存") {
          saveInfo()
        }
        val list = ArrayList<String>()
        for (i in 0..29) {
            list.add("test+" + i)
        }
        val adapter = DragAdapter(activity)
        adapter.setDatas(list)
        drag_list.adapter=adapter
    }

    private fun saveInfo() {
        toast("click save")
    }


}