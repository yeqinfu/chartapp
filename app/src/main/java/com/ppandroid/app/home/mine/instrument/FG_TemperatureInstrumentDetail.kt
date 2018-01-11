/*
 * Created by yeqinfu on 18-1-8 下午5:22
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home.mine.instrument

import com.ppandroid.app.R
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.mine.instrument.BN_TemperatureIntrDetail
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_temperature_instrument_detail.*
import kotlinx.android.synthetic.main.layout_head_view.*

/**
 * Created by yeqinfu on 2018/1/8.
 */
class FG_TemperatureInstrumentDetail : FG_Base(){
    override fun fgRes(): Int= R.layout.fg_temperature_instrument_detail
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

        var url="user/instrument/record/temperature.json?id=$id"
        Http.get(activity, url, BN_TemperatureIntrDetail::class.java, object : MyCallBack<BN_TemperatureIntrDetail> {
            override fun onResponse(response: BN_TemperatureIntrDetail?) {
                response?.let {
                    if (!isAdded){
                        return
                    }
                    tv_humidity.text=it.message.humidity?.toString()
                    tv_temperature.text=it.message.temperature?.toString()
                    tv_lastModifyTime.text=it.message.lastModifyTime?.toString()
                }
            }

            override fun onError(error: ErrorBody?) {
                toast(error)
            }

        })
    }
}