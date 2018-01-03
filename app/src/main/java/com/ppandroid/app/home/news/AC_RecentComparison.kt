/*
 * Created by yeqinfu on 18-1-3 下午2:30
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home.news

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.ppandroid.app.R
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.news.BN_RecentComparison
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.app.utils.Utils_Common
import com.ppandroid.app.utils.Utils_DateFormat
import com.ppandroid.app.widget.linechart.MultipleChartView
import com.ppandroid.app.widget.popwindow.Pop_DatePicker
import com.ppandroid.im.base.AC_Base
import kotlinx.android.synthetic.main.ac__recent_comparison.*
import java.text.DecimalFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class AC_RecentComparison : AC_Base() {

    private var selectList = HashMap<String, String>()
    var energyClassificationId = "1"
    protected var select = ""
    private fun format(month: Int): String {
        if (month < 10) {
            return "0" + month
        }
        return month.toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac__recent_comparison)
        iv_back.setOnClickListener {
            finish()
        }
        var defalutDate = intent.getStringExtra("default")
        energyClassificationId = intent.getStringExtra("energyClassificationId")
        if (TextUtils.isEmpty(defalutDate)) {
            selectList.put("1", Utils_DateFormat.dateFormat(Date()))
        } else {
            selectList.put("1", defalutDate)
        }

        refrestTitle()
        loadContent()
        fg_01.setOnClickListener {
            showPop("1")
        }
        fg_02.setOnClickListener {
            showPop("2")
        }
        fg_03.setOnClickListener {
            showPop("3")
        }
        fg_04.setOnClickListener {
            showPop("4")
        }
        fg_05.setOnClickListener {
            showPop("5")
        }

    }

    private fun showPop(index: String) {
        val c = Calendar.getInstance()
        if (select.isEmpty()) {
            select = c.get(Calendar.YEAR).toString() + "-" + (format(c.get(Calendar.MONTH) + 1)) + "-" + format(c.get(Calendar.DAY_OF_MONTH))
        }
        var type = 0
        var pop = Pop_DatePicker(this, type)
        pop.setInitStr(select)
        pop.listener = object : Pop_DatePicker.IChooseListener {
            override fun choose(year: String, month: String, day: String) {
                select = "$year-$month-$day"

                for ((key, value) in selectList) {
                    if (value.equals(select)){//说明当前日期已经选择过了
                        toast("请选择没选择过的日期")
                        return
                    }
                }
                selectList.put(index, select)
                refrestTitle()
                loadContent()
                pop?.dismiss()
            }

        }
        pop.showPopupWindow()
    }

    private fun refrestTitle() {
        rl_time01.visibility = View.GONE
        rl_time02.visibility = View.GONE
        rl_time03.visibility = View.GONE
        rl_time04.visibility = View.GONE
        rl_time05.visibility = View.GONE
        selectList["1"].let {
            rl_time01.visibility = View.VISIBLE
            tv_date01.text = it
        }
        selectList["2"].let {
            rl_time02.visibility = View.VISIBLE
            tv_date02.text = it
        }
        selectList["3"].let {
            rl_time03.visibility = View.VISIBLE
            tv_date03.text = it
        }
        selectList["4"].let {
            rl_time04.visibility = View.VISIBLE
            tv_date04.text = it
        }
        selectList["5"].let {
            rl_time05.visibility = View.VISIBLE
            tv_date05.text = it
        }

    }


    private fun loadContent() {
        var url = if (energyClassificationId == "1") {
            "user/energy/analysis/getDeviceByDateArray.json?dateString="
        } else {
            "user/water/analysis/getDeviceByDateArray.json?dateString="
        }
        for ((key, value) in selectList) {
            url += value + ","
        }
        url = url.substring(0, url.length - 1)
        Http.get(this@AC_RecentComparison, url, BN_RecentComparison::class.java, object : MyCallBack<BN_RecentComparison> {
            override fun onResponse(response: BN_RecentComparison?) {
                response?.let {
                    var xValue=ArrayList<String>()
                    //取出横坐标值
                    it.message[0]?.let {
                        for (item in it.stageKwh){
                            xValue.add(item.key)

                        }
                    }

                    /**这三个变量代表所有的折线的值集合的平均最小和最大值*/
                    var avg=0.0
                    var max=6.0
                    var min=0.0
                    /**yValue根据最大值六等分获得*/
                    var yValue=ArrayList<Double>()
                    //值集合
                    var listValue=ArrayList<MultipleChartView.BrokenLine>()
                    var colors = arrayOf(
                            R.color.vcolor06,
                            R.color.vcolor07,
                            R.color.vcolor08,
                            R.color.vcolor09,
                            R.color.vcolor10
                    )
                    for ((index,item) in it.message.withIndex()){
                        var target= MultipleChartView.BrokenLine()
                        var targetMax=5.0
                        target.lineColor=colors[index]
                        for(child in item.stageKwh){
                            var value=Utils_Common.parseNumberString(child.value)
                            if (value>targetMax){
                                targetMax=value
                            }
                            target.value.put(child.key,value)
                        }
                        target.maxValue=targetMax
                        if (max<targetMax){
                            max=targetMax
                        }
                        listValue.add(target)
                        /*这边avg简单取第一个的avg*/
                        avg=Utils_Common.parseNumberString(item.averageKwh)
                    }
                    //向上取整纵坐标六等分
                    max=Math.ceil(max)
                    val df = DecimalFormat("######0.00")
                    //
                    (0..6).mapTo(yValue) {
                        df.format(it *max/6).toDouble() }
                    chartview?.isNeedSplit=true
                    chartview?.avgValue=avg
                    chartview?.avgText=avg.toString()
                    chartview.isShowAvg=true
                    chartview.setValue(listValue,xValue,yValue,df.format(max))

                }
            }

            override fun onError(error: ErrorBody?) {
                toast(error)
            }

        })


    }
}





