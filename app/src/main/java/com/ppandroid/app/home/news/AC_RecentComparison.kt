/*
 * Created by yeqinfu on 18-1-3 下午2:30
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home.news

import android.annotation.SuppressLint
import android.graphics.Color
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
import com.ppandroid.app.utils.Utils_Dialog
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
            selectList.put("3", Utils_DateFormat.dateFormat(Date()))
        } else {
            selectList.put("3", defalutDate)
        }
        //增加前两天数据
        var nowDate=Utils_DateFormat.dateFormat(selectList.get("3"))
        var before01=Utils_DateFormat.getBeforeDay(nowDate)
        var before02=Utils_DateFormat.getBeforeDay(before01)
        selectList.put("2",Utils_DateFormat.dateFormat(before01))
        selectList.put("1",Utils_DateFormat.dateFormat(before02))

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
        fg_01.setOnLongClickListener { view->
            selectList["1"]?.let {
                rl_time01.visibility = View.VISIBLE
                iv_del01.visibility = View.VISIBLE
                tv_date01.text = it
            }
            true
        }
        fg_02.setOnLongClickListener { view->
            selectList["2"]?.let {
                rl_time02.visibility = View.VISIBLE
                iv_del02.visibility = View.VISIBLE
                tv_date02.text = it
            }
            true
        }
        fg_03.setOnLongClickListener { view->
            selectList["3"]?.let {
                rl_time03.visibility = View.VISIBLE
                iv_del03.visibility = View.VISIBLE
                tv_date03.text = it
            }
            true
        }
        fg_04.setOnLongClickListener { view->
            selectList["4"]?.let {
                rl_time04.visibility = View.VISIBLE
                iv_del04.visibility = View.VISIBLE
                tv_date04.text = it
            }
            true
        }
        fg_05.setOnLongClickListener { view->
            selectList["5"]?.let {
                rl_time05.visibility = View.VISIBLE
                iv_del05.visibility = View.VISIBLE
                tv_date05.text = it
            }
            true
        }
        iv_del01.setOnClickListener {
            if (selectList.size<=1){
                toast("请至少选择一个日期")
                return@setOnClickListener
            }
            selectList.remove("1")
            loadContent()
            refrestTitle()
        }
        iv_del02.setOnClickListener {
            if (selectList.size<=1){
                toast("请至少选择一个日期")
                return@setOnClickListener
            }
            selectList.remove("2")
            loadContent()
            refrestTitle()
        }
        iv_del03.setOnClickListener {
            if (selectList.size<=1){
                toast("请至少选择一个日期")
                return@setOnClickListener
            }
            selectList.remove("3")
            loadContent()
            refrestTitle()
        }
        iv_del04.setOnClickListener {
            if (selectList.size<=1){
                toast("请至少选择一个日期")
                return@setOnClickListener
            }
            selectList.remove("4")
            loadContent()
            refrestTitle()
        }
        iv_del05.setOnClickListener {
            if (selectList.size<=1){
                toast("请至少选择一个日期")
                return@setOnClickListener
            }
            selectList.remove("5")
            refrestTitle()
            loadContent()
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
        selectList["1"]?.let {
            rl_time01.visibility = View.VISIBLE
            tv_date01.text = it
        }
        selectList["2"]?.let {
            rl_time02.visibility = View.VISIBLE
            tv_date02.text = it
        }
        selectList["3"]?.let {
            rl_time03.visibility = View.VISIBLE
            tv_date03.text = it
        }
        selectList["4"]?.let {
            rl_time04.visibility = View.VISIBLE
            tv_date04.text = it
        }
        selectList["5"]?.let {
            rl_time05.visibility = View.VISIBLE
            tv_date05.text = it
        }

    }


    private fun loadContent() {
        Utils_Dialog.showLoading(this@AC_RecentComparison)
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
            @SuppressLint("ResourceAsColor")
            override fun onResponse(response: BN_RecentComparison?) {
                Utils_Dialog.disMissLoading()
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
                    var max=0.0
                    var min=0.0
                    /**yValue根据最大值六等分获得*/
                    var yValue=ArrayList<Double>()
                    //值集合
                    var listValue=ArrayList<MultipleChartView.BrokenLine>()
                    var colors =resources.getStringArray(R.array.array_line_color)
                    var avgTotal=0.0
                    for ((index,item) in it.message.withIndex()){
                        min=Utils_Common.parseNumberString(item.stageKwh[0].value)
                        var target= MultipleChartView.BrokenLine()
                        var targetMax=0.0
                        var targetMin=Utils_Common.parseNumberString(item.stageKwh[0].value)
                        target.lineColor= Color.parseColor(colors[index])
                        target.linePointColor= Color.parseColor(colors[index])
                        for(child in item.stageKwh){
                            var value=Utils_Common.parseNumberString(child.value)
                            if (value>targetMax){
                                targetMax=value
                            }
                            if (value<targetMin){
                                targetMin=value
                            }
                            target.value.put(child.key,value)
                        }
                        target.maxValue=targetMax
                        target.minValue=targetMin
                        if (max<targetMax){
                            max=targetMax
                        }
                        if (min>targetMin){
                            min=targetMin
                        }
                        listValue.add(target)
                        /*这边avg简单取第一个的avg*/
                        target.avg=Utils_Common.parseNumberString(item.averageKwh)
                        avgTotal+=target.avg
                    }
                    avg=avgTotal/listValue.size
                    //向上取整纵坐标六等分
                    max=Math.ceil(max)
                    val df = DecimalFormat("######0.00")
                    //
                    (0..6).mapTo(yValue) {
                        var yMax=if (max<=0){
                            6.0
                        }else{
                            max
                        }
                        df.format(it *yMax/6).toDouble()
                    }
                    chartview?.isNeedSplit=true
                    chartview?.avgValue=avg
                    chartview?.avgText="平均值："+df.format(avg)
                    chartview.isShowAvg=true
                    //最大线，最小线
                    var listBrokenLine= ArrayList<MultipleChartView.BN_BrokeLine>()
                    var maxLine=MultipleChartView.BN_BrokeLine()
                    maxLine.value=max
                    maxLine.text="最大值："+max
                    listBrokenLine.add(maxLine)

                    var minLine=MultipleChartView.BN_BrokeLine()
                    minLine.value=min
                    minLine.text="最小值："+min
                    listBrokenLine.add(minLine)
                    chartview.listBrokeLine=listBrokenLine
                    chartview.setValue(listValue,xValue,yValue,df.format(max))

                }
            }

            override fun onError(error: ErrorBody?) {
                Utils_Dialog.disMissLoading()
                toast(error)
            }

        })


    }
}





