/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home.mine.energyanalysis

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.ppandroid.app.R
import com.ppandroid.app.utils.DebugLog
import com.ppandroid.app.utils.Utils_Common
import com.ppandroid.app.widget.HorizontalPercentageView
import com.ppandroid.app.widget.graphical.chart.PieData
import com.ppandroid.app.widget.graphical.render.XEnum
import com.ppandroid.app.widget.popwindow.Pop_DatePicker
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_base_analysis_page.*
import org.jetbrains.anko.find
import java.util.*

/**
 * Created by yeqinfu on 2017/8/31.
 */
abstract class FG_BaseAnlysisPage : FG_Base() {
    companion object {
        fun createBundle(index: Int): Bundle {
            var b = Bundle()
            b.putInt("index", index)
            return b
        }

        fun createBundle(index: Int, parentId: String): Bundle {
            var b = createBundle(index)
            b.putString("parentId", parentId)
            return b
        }
    }

    override fun fgRes(): Int = R.layout.fg_base_analysis_page
    //0 日 1 月 2 年 3 总
    protected var index: Int = 0
    protected var parentId = "-1"

    override fun afterViews() {
        v_dount_view.setLabelStyle(XEnum.SliceLabelStyle.INSIDE)
        arguments?.let {
            index = it.getInt("index", 0)
            parentId = it.getString("parentId", "-1")
            DebugLog.d("yeqinfu", "------FG_BaseAnlysisPage--->" + parentId)
        }
        val c = Calendar.getInstance()
        if (index == 3) {//总
            tv_time.text = c.get(Calendar.YEAR).toString() + "-" + c.get(Calendar.YEAR).toString() + "年"
            select = ""//总计传空
        } else {
            var initDate = when (index) {
                0 -> c.get(Calendar.YEAR).toString() + "-" + (format(c.get(Calendar.MONTH) + 1)) + "-" + format(c.get(Calendar.DAY_OF_MONTH))
                1 -> c.get(Calendar.YEAR).toString() + "-" + (format(c.get(Calendar.MONTH) + 1))
                else -> c.get(Calendar.YEAR).toString()
            }
            tv_time.text = initDate
            select = initDate
            tv_time.setOnClickListener {
                showDatePop2()
            }
        }
        loadContent()
        init()
    }

    abstract fun init()


    protected var select = ""

    private fun showDatePop2(){
        var type=0
        when(index){
            0->type=0
            1->type=1
            2->type=2
        }
        var pop= Pop_DatePicker(activity,type)
        pop.setInitStr(select)
        pop.listener=object : Pop_DatePicker.IChooseListener{
            override fun choose(year: String, month: String, day: String) {
                when (index) {
                    0 -> //日
                        select = "$year-$month-$day"
                    1 -> //月
                        select = year+ "-" + (month)
                    2 -> //日
                        select = year
                }
                tv_time.text = select
                loadContent()
                pop?.dismiss()
            }

        }
        pop.showPopupWindow()
    }


    private fun format(month: Int): String {
        if (month < 10) {
            return "0" + month
        }
        return month.toString()
    }

    abstract fun loadContent()


    protected var isHaveChild = true
        set(value) {
            field = value
            if (value) {
                v_holder.visibility = View.VISIBLE
            } else {
                v_holder.visibility = View.GONE
            }

        }

    class Model {
        var name = ""
        var ratio = ""
        var kmh = ""
        var value = 0.0f
        var isLeaf = false
    }

    var colors = arrayOf(
            "#FA6D6F",
            "#FE8D25",
            "#FFB338",
            "#6CC37E",
            "#2ED9A4"
    )


    protected fun getValues(source: List<Model>): List<PieData>? {
        var list = ArrayList<PieData>()
        for ((i, item) in source.withIndex()){
            var value=Utils_Common.paraseDouble(Utils_Common.findNumberFromStr(item.ratio))
            var p=PieData(item.name,value.toString(),value,Color.parseColor(colors[i%colors.size]))
            list.add(p)
        }
        return list
    }


    protected class AD_List(ac: Activity, list: List<Model>, isHaveChild: Boolean) : BaseAdapter() {
        private var ac = ac
        private var list = list
        private var isHaveChild = isHaveChild
        var colors = arrayOf(
                "#FA6D6F",
                "#FE8D25",
                "#FFB338",
                "#6CC37E",
                "#2ED9A4"
        )
        override fun getView(pos: Int, p1: View?, p2: ViewGroup?): View {
            var layout = ac.layoutInflater.inflate(R.layout.item_base_anlysis_page, null)
            var btn_next = layout.find<TextView>(R.id.btn_next)
            if (isHaveChild) {
                btn_next.visibility = View.VISIBLE
            } else {
                btn_next.visibility = View.GONE
            }

            var tv_name = layout.find<TextView>(R.id.tv_name)
            tv_name.text = list[pos].name
            var tv_ratio = layout.find<TextView>(R.id.tv_ratio)
            tv_ratio.text = list[pos].ratio
            var tv_kmh = layout.find<TextView>(R.id.tv_kmh)
            tv_kmh.text = list[pos].kmh

            if (list[pos].isLeaf) {//子节点
                btn_next.setBackgroundResource(R.drawable.shape_stroke_color06_corner_4dp)
                btn_next.setTextColor(ac.resources.getColor(R.color.color_06))

            } else {//支节点
                btn_next.setBackgroundResource(R.drawable.shape_stroke_color01_corner_4dp)
                btn_next.setTextColor(ac.resources.getColor(R.color.color_01))
                btn_next.setOnClickListener {
                    listener?.choose(pos)
                }
            }

            var value = list[pos].ratio.replace("%", "")
            var f = Utils_Common.paraseDouble(value)
            var v_hp=layout.find<HorizontalPercentageView>(R.id.v_hp)
            v_hp.percentage= f.toFloat()/100f
            v_hp.startAnim()
            v_hp.colorId=Color.parseColor(colors[pos%colors.size])


            return layout
        }

        var listener: ItemChoosseListener? = null


        override fun getItem(p0: Int): Any = list[p0]

        override fun getItemId(p0: Int): Long = p0.toLong()

        override fun getCount(): Int = list.size

    }

    interface ItemChoosseListener {
        fun choose(index: Int)
    }


}