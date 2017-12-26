/*
 * Created by yeqinfu on 17-12-22 下午2:24
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home.news

import com.ppandroid.app.R
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.im.base.FG_Base
import com.ppandroid.im.bean.BaseBody
import kotlinx.android.synthetic.main.fg_energy_comarison.*
import kotlinx.android.synthetic.main.layout_head_view.*

/**
 * Created by yeqinfu on 2017/12/22.
 */
class FG_EnergyComparison:FG_Base(){
    override fun fgRes(): Int = R.layout.fg_energy_comarison

    override fun afterViews() {
            head_view.init(activity)
            head_view.setCenterTitle("能耗对比")
            refresh_layout.setOnRefreshListener {
                loadContent(true)
            }
            refresh_layout.setOnLoadmoreListener {
                loadContent(false)
            }
    }

    private fun loadContent(b: Boolean) {
        var url="/user/message/get.json?type=3"
        Http.get(activity,url, BaseBody::class.java,object : MyCallBack<BaseBody> {
            override fun onResponse(response: BaseBody?) {
            }

            override fun onError(error: ErrorBody?) {
            }

        })
    }

}