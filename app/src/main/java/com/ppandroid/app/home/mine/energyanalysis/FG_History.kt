/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home.mine.energyanalysis

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.ppandroid.app.R
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.mine.energyanalysis.BN_History
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.app.utils.Devices
import com.ppandroid.app.widget.popwindow.Pop_DatePicker
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_history.*
import kotlinx.android.synthetic.main.layout_head_view.*
import org.jetbrains.anko.find


/**
 * Created by yeqinfu on 2017/8/30.
 */
class FG_History : FG_Base() {
    override fun fgRes(): Int = R.layout.fg_history
    private var energyClassificationId = Devices.ELECTRIC
    override fun afterViews() {

        arguments?.let {
            energyClassificationId = it.getString("energyClassificationId", Devices.ELECTRIC)
        }
        head_view.init(activity)
        head_view.setCenterTitle("历史查询")
        ll_year.setOnClickListener {
            showDatePop2()
        }
    }

    fun loadContent() {
        var url: String = if (energyClassificationId.equals(Devices.ELECTRIC)){//电
            "user/energy/analysis/getYearHistory.json"
        }else{//水
            "user/water/analysis/getYearHistory.json"
        }

        url += "?energyClassificationId=" + energyClassificationId
        url += "&year=$select"
        Http.get(activity, url, BN_History::class.java, object : MyCallBack<BN_History> {
            override fun onResponse(response: BN_History?) {
                response?.let {
                    var adapter = AD_List(it.message, activity)
                    lv_list.adapter = adapter
                }
            }

            override fun onError(error: ErrorBody?) {
                toast(error)
            }

        })
    }

    class AD_List(message: List<String>, ac: Activity) : BaseAdapter() {
        private var message: List<String> = message
        private var ac: Activity = ac
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var layout = ac.layoutInflater.inflate(R.layout.item_history, null)
            var tv_key = layout.find<TextView>(R.id.tv_key)
            var tv_value = layout.find<TextView>(R.id.tv_value)
            tv_key.text = (p0 + 1).toString() + "月"
            tv_value.text = message[p0]
            return layout
        }

        override fun getItem(p0: Int): Any = message[p0]

        override fun getItemId(p0: Int): Long = p0.toLong()

        override fun getCount(): Int = message.size

    }


    var select = ""


    private fun showDatePop2() {
        var type = 2
        var pop = Pop_DatePicker(activity, type)
        pop.setInitStr(select)
        pop.listener = object : Pop_DatePicker.IChooseListener {
            override fun choose(year: String, month: String, day: String) {
                tv_choose.text = year
                select = year
                loadContent()
                pop?.dismiss()
            }

        }
        pop.showPopupWindow()
    }
}