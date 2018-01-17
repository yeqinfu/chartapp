/*
 * Created by yeqinfu on 18-1-3 下午2:30
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home.news

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.ppandroid.app.R
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.news.BN_RecentComparison
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.app.utils.Utils_Common
import com.ppandroid.app.utils.Utils_DateFormat
import com.ppandroid.app.utils.toast.ToastUtil.toast
import com.ppandroid.app.widget.linechart.MultipleChartView
import com.ppandroid.app.widget.popwindow.Pop_DatePicker
import com.ppandroid.im.base.AC_Base
import kotlinx.android.synthetic.main.ac__recent_comparison.*
import org.jetbrains.anko.find
import java.text.DecimalFormat
import java.util.*
import kotlin.collections.ArrayList

class AC_RecentComparison : AC_Base() {

    private var selectList = HashMap<String, String>()


    var energyClassificationId = "1"
    protected var select = ""


    var bnList = ArrayList<BN_Select>()

    /**
     * 选择器对象
     *
     */
    class BN_Select(context: Activity, index: Int, listener: RefreshTitle, selectList: HashMap<String, String>, listValue: ArrayList<MultipleChartView.BrokenLine>) {
        var index = index//选择器下标0 1 2 3 4 5
        var context = context
        var chartColor = -1
        var view: View? = null
        var listener = listener
        var selectList = selectList
        var listValue = listValue
        var rl_time01: RelativeLayout? = null
        var tv_date01: TextView? = null
        var isShow = true

        init {
            var colors = context.resources.getStringArray(R.array.array_line_color)
            chartColor = Color.parseColor(colors[index])
            view = LayoutInflater.from(context).inflate(R.layout.item_recent_title, null)
            view?.let {
                it.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f)
                var iv_01 = it.find<ImageView>(R.id.iv_01)
                iv_01.setOnClickListener {
                    showPop(index.toString())
                }
                rl_time01 = it.find(R.id.rl_time01)
                var v_select01 = it.find<View>(R.id.v_select01)
                v_select01.setBackgroundColor(chartColor)
                tv_date01 = it.find(R.id.tv_date01)
                var iv_del01 = it.find<ImageView>(R.id.iv_del01)
                if (!isShow){
                    v_select01.visibility = View.INVISIBLE
                }else{
                    v_select01.visibility = View.VISIBLE
                }
                rl_time01?.setOnClickListener {
                    selectList[(index).toString()]?.let {
                        for (obj in listValue) {
                            if (obj.index == index) {
                                if (obj.isShow) {
                                    isShow=!isShow
                                    obj.isShow = !obj.isShow
                                    v_select01.visibility = View.INVISIBLE
                                } else {
                                    isShow=!isShow
                                    v_select01.visibility = View.VISIBLE
                                    obj.isShow = !obj.isShow
                                }
                                listener.notifyDataChange()
                            }
                        }

                    }
                }

                rl_time01?.setOnLongClickListener { view ->
                    selectList[index.toString()]?.let {
                        rl_time01?.visibility = View.VISIBLE
                        iv_del01.visibility = View.VISIBLE
                        tv_date01?.text = it
                    }
                    true
                }

                iv_del01.setOnClickListener {
                    if (selectList.size <= 1) {
                        toast("请至少选择一个日期")
                        return@setOnClickListener
                    }
                    selectList.remove(index.toString())
                    refreshContent()
                    listener.onRefresh()
                    listener.notifyDataChange()
                }
                refreshContent()
            }
        }

        fun refreshContent() {
            rl_time01?.visibility = View.GONE
            selectList[(index).toString()]?.let {
                rl_time01?.visibility = View.VISIBLE
                tv_date01?.text = it
            }

        }

        fun getContentView(): View? {
            return view
        }

        var select = ""
        private fun format(month: Int): String {
            if (month < 10) {
                return "0" + month
            }
            return month.toString()
        }

        private fun showPop(index: String) {
            val c = Calendar.getInstance()
            if (select.isEmpty()) {
                select = c.get(Calendar.YEAR).toString() + "-" + (format(c.get(Calendar.MONTH) + 1)) + "-" + format(c.get(Calendar.DAY_OF_MONTH))
            }
            var type = 0
            var pop = Pop_DatePicker(context, type)
            pop.setInitStr(select)
            pop.listener = object : Pop_DatePicker.IChooseListener {
                override fun choose(year: String, month: String, day: String) {
                    select = "$year-$month-$day"

                    for ((key, value) in selectList) {
                        if (value.equals(select)) {//说明当前日期已经选择过了
                            toast("请选择没选择过的日期")
                            return
                        }
                    }
                    selectList.put(index, select)
                    refreshContent()
                    listener.onRefresh()
                    pop?.dismiss()
                }

            }
            pop.showPopupWindow()
        }
    }

    interface RefreshTitle {
        fun onRefresh()
        fun notifyDataChange()
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
            selectList.put("2", Utils_DateFormat.dateFormat(Date()))
        } else {
            selectList.put("2", defalutDate)
        }
        //增加前两天数据
        var nowDate = Utils_DateFormat.dateFormat(selectList.get("2"))
        var before01 = Utils_DateFormat.getBeforeDay(nowDate)
        var before02 = Utils_DateFormat.getBeforeDay(before01)
        selectList.put("1", Utils_DateFormat.dateFormat(before01))
        selectList.put("0", Utils_DateFormat.dateFormat(before02))

        var listener = object : RefreshTitle {
            override fun onRefresh() {
                loadContent()
            }

            override fun notifyDataChange() {
                chartview.notifyDataChange()
            }

        }

        (0..4)
                .map {
                    var item = BN_Select(AC_RecentComparison@ this, it, listener, selectList, listValue)
                    bnList.add(item)
                    item
                }
                .forEach { ll_title_content.addView(it.getContentView()) }

        loadContent()
    }

    var listValue = ArrayList<MultipleChartView.BrokenLine>()
    private fun loadContent() {
        listValue.clear()
        var url = if (energyClassificationId == "1") {
            "user/energy/analysis/getDeviceByDateArray.json?dateString="
        } else {
            "user/water/analysis/getDeviceByDateArray.json?dateString="
        }
        var colorIndexList = ArrayList<String>()
        for (item in 0..selectList.size) {
            var re = selectList["" + item]
            re?.let {
                url += re + ","
                colorIndexList.add(item.toString())
            }

        }


        url = url.substring(0, url.length - 1)
        Http.get(this@AC_RecentComparison, url, BN_RecentComparison::class.java, object : MyCallBack<BN_RecentComparison> {
            @SuppressLint("ResourceAsColor")
            override fun onResponse(response: BN_RecentComparison?) {
                response?.let {

                    var xValue = ArrayList<String>()
                    //取出横坐标值
                    it.message[0]?.let {
                        for (item in it.stageKwh) {
                            xValue.add(item.key)

                        }
                    }

                    /**这三个变量代表所有的折线的值集合的平均最小和最大值*/
                    var avg = 0.0
                    var max = 0.0
                    var min = 0.0
                    /**yValue根据最大值六等分获得*/
                    var yValue = ArrayList<Double>()
                    //值集合

                    var colors = resources.getStringArray(R.array.array_line_color)
                    var avgTotal = 0.0
                    for ((index, item) in it.message.withIndex()) {
                        min = Utils_Common.parseNumberString(item.stageKwh[0].value)
                        var target = MultipleChartView.BrokenLine()
                        var targetMax = 0.0
                        var targetMin = Utils_Common.parseNumberString(item.stageKwh[0].value)




                        if (index <= colorIndexList.size) {
                            var i = colorIndexList[index].toInt()
                            for (bn in bnList){
                                if (bn.index==i){
                                    target.isShow=bn.isShow
                                    break
                                }
                            }
                            target.index = i
                            target.lineColor = Color.parseColor(colors[i])
                            target.linePointColor = Color.parseColor(colors[i])
                        }

                        for (child in item.stageKwh) {
                            var value = Utils_Common.parseNumberString(child.value)
                            if (value > targetMax) {
                                targetMax = value
                            }
                            if (value < targetMin) {
                                targetMin = value
                            }
                            target.value.put(child.key, value)
                        }
                        target.maxValue = targetMax
                        target.minValue = targetMin
                        if (max < targetMax) {
                            max = targetMax
                        }
                        if (min > targetMin) {
                            min = targetMin
                        }

                        listValue.add(target)
                        /*这边avg简单取第一个的avg*/
                        target.avg = Utils_Common.parseNumberString(item.averageKwh)
                        avgTotal += target.avg
                    }
                    avg = avgTotal / listValue.size

                    var maxLine = MultipleChartView.BN_BrokeLine()
                    maxLine.value = max
                    maxLine.text = "最大值：" + max

                    //向上取整纵坐标六等分
                    max = Math.ceil(max)
                    val df = DecimalFormat("######0.00")
                    //
                    (0..6).mapTo(yValue) {
                        var yMax = if (max <= 0) {
                            6.0
                        } else {
                            max
                        }
                        df.format(it * yMax / 6).toDouble()
                    }
                    chartview?.isNeedSplit = true
                    chartview?.avgValue = avg
                    chartview?.avgText = "平均值：" + df.format(avg)
                    chartview.isShowAvg = true
                    //最大线，最小线
                    var listBrokenLine = ArrayList<MultipleChartView.BN_BrokeLine>()

                    listBrokenLine.add(maxLine)

                    var minLine = MultipleChartView.BN_BrokeLine()
                    minLine.value = min
                    minLine.text = "最小值：" + min
                    listBrokenLine.add(minLine)
                    chartview.listBrokeLine = listBrokenLine
                    chartview.setValue(listValue, xValue, yValue, df.format(max))

                }
            }

            override fun onError(error: ErrorBody?) {
                toast(error)
            }

        })


    }
}





