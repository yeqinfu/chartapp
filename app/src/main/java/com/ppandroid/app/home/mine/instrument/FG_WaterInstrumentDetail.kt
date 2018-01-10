/*
 * Created by yeqinfu on 18-1-8 下午5:15
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home.mine.instrument

import com.ppandroid.app.R
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.mine.instrument.BN_WaterIntrumentDetail
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_water_instrument_detail.*
import kotlinx.android.synthetic.main.layout_head_view.*

/**
 * Created by yeqinfu on 2018/1/8.
 */
class FG_WaterInstrumentDetail:FG_Base(){
    override fun fgRes(): Int= R.layout.fg_water_instrument_detail
    private var id: Long = -1

    override fun afterViews() {
        head_view.init(activity)
        var b = arguments
        b?.let {
            id = it.getLong("id", -1L)
            head_view.setCenterTitle(it.getString("title", ""))
        }
        loadContent()


    }

    private fun loadContent() {

        var url="user/instrument/record/water.json?id=$id"
        Http.get(activity,url, BN_WaterIntrumentDetail::class.java,object :MyCallBack<BN_WaterIntrumentDetail>{
            override fun onResponse(response: BN_WaterIntrumentDetail?) {
                response?.let {
                    if (!isAdded){
                        return
                    }
                    tv_number.text=it.message.m3.toString()
                    tv_lastModifyTime.text=it.message.lastModifyTime.toString()
                }
            }

            override fun onError(error: ErrorBody?) {
                toast(error)
            }

        })
    }
}