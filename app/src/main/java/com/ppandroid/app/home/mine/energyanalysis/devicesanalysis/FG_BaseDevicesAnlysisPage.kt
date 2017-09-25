package com.ppandroid.app.home.mine.energyanalysis.devicesanalysis

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
import com.ppandroid.app.widget.graphical.chart.PieData
import com.ppandroid.app.widget.popwindow.Pop_DatePicker
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
                showDatePop2()
            }
        }
        loadContent()
        init()
    }

    abstract fun init()

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
    protected var select = ""


    private fun format(month: Int): String {
        if (month < 10) {
            return "0" + month
        }
        return month.toString()
    }

    abstract fun loadContent()



    class Model {
        var key = ""
        var values = ""
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
            var value= Utils_Common.paraseDouble(Utils_Common.findNumberFromStr(item.values))
            var p= PieData(item.key, value.toString(), value, Color.parseColor(colors[i % colors.size]))
            list.add(p)
        }
        return list
    }


    protected class AD_List(ac: Activity, list: List<Model>) : BaseAdapter() {
        private var ac = ac
        private var list = list
        var colors = arrayOf(
                "#FA6D6F",
                "#FE8D25",
                "#FFB338",
                "#6CC37E",
                "#2ED9A4"
        )
        override fun getView(pos: Int, p1: View?, p2: ViewGroup?): View {
            var layout = ac.layoutInflater.inflate(R.layout.item_base_devices_anlysis_page, null)
            var tv_key = layout.find<TextView>(R.id.tv_key)
            tv_key.text = list[pos].key
            var tv_value = layout.find<TextView>(R.id.tv_value)
            tv_value.text = list[pos].values
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