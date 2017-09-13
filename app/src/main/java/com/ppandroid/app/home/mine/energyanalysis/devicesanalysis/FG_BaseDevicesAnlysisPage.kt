package com.ppandroid.app.home.mine.energyanalysis.devicesanalysis

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.bruce.pickerview.popwindow.DatePickerPopWin
import com.ppandroid.app.R
import com.ppandroid.app.base.AC_ContentFG
import com.ppandroid.app.utils.DebugLog
import com.ppandroid.app.utils.Utils_Common
import com.ppandroid.app.widget.HorizontalPercentageView
import com.ppandroid.app.widget.graphical.chart.PieData
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_base_devices_analysis_page.*
import org.jetbrains.anko.find
import java.util.*

/**
 * Created by yeqinfu on 2017/8/31.
 */
abstract class FG_BaseDevicesAnlysisPage : FG_Base() {
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

    override fun fgRes(): Int = R.layout.fg_base_devices_analysis_page
    //0 日 1 月 2 年 3 总
    protected var index: Int = 0
    protected var parentId = "-1"

    override fun afterViews() {
        (activity as AC_ContentFG).setEnablePullToBack(false)
        arguments?.let {
            index = it.getInt("index", 0)
            parentId = it.getString("parentId", "-1")
            DebugLog.d("yeqinfu", "------FG_BaseAnlysisPage--->" + parentId)
        }
        val c = Calendar.getInstance()
        if (index == 3) {//总
            tv_time.text = c.get(Calendar.YEAR).toString() + "-" + c.get(Calendar.YEAR).toString() + "年"
            select = c.get(Calendar.YEAR).toString()
        } else {
            var initDate = when (index) {
                0 -> c.get(Calendar.YEAR).toString() + "-" + (format(c.get(Calendar.MONTH) + 1)) + "-" + format(c.get(Calendar.DAY_OF_MONTH))
                1 -> c.get(Calendar.YEAR).toString() + "-" + (format(c.get(Calendar.MONTH) + 1))
                else -> c.get(Calendar.YEAR).toString()
            }
            tv_time.text = initDate
            select = initDate
            tv_time.setOnClickListener {
                showDatePop()
            }
        }
        loadContent()
        init()
    }

    abstract fun init()


    protected var select = ""
    private fun showDatePop() {
        val c = Calendar.getInstance()
        val initDate = c.get(Calendar.YEAR).toString() + "-" + (c.get(Calendar.MONTH) + 1).toString() + "-" + c.get(Calendar.DAY_OF_MONTH)
        val pickerPopWin = DatePickerPopWin.Builder(activity, DatePickerPopWin.OnDatePickedListener { year, month, day, dateDesc ->


            when (index) {
                0 -> //日
                    select = year.toString() + "-" + format(month) + "-" + format(day)
                1 -> //月
                    select = year.toString() + "-" + format(month)
                2 -> //日
                    select = year.toString()
            }
            tv_time.text = select
            loadContent()
        }).textConfirm("确定") //text of confirm button
                .textCancel("取消") //text of cancel button
                .btnTextSize(16) // button text size
                .viewTextSize(25) // pick view text size
                .colorCancel(Color.parseColor("#999999")) //color of cancel button
                .colorConfirm(Color.parseColor("#009900"))//color of confirm button
                .minYear(1990) //min year in loop
                .maxYear(c.get(Calendar.YEAR) + 1) // max year in loop
                .showDayMonthYear(false) // shows like dd mm yyyy (default is false)
                .dateChose(initDate) // date chose when init popwindow
                .build()

        when (index) {
            0 -> //日
            {
            }
            1 -> //月
            {
                pickerPopWin.dayLoopView.visibility = View.GONE
            }
            2 -> //日
            {
                pickerPopWin.monthLoopView.visibility = View.GONE
                pickerPopWin.dayLoopView.visibility = View.GONE
            }
        }


        pickerPopWin.showPopWin(activity)
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
            var value= Utils_Common.paraseDouble(Utils_Common.findNumberFromStr(item.ratio))
            var p= PieData(item.name, value.toString(), value, Color.parseColor(colors[i % colors.size]))
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
            v_hp.colorId= Color.parseColor(colors[pos % colors.size])


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