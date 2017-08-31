package com.ppandroid.app.home.mine.energyanalysis

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import com.bruce.pickerview.popwindow.DatePickerPopWin
import com.ppandroid.app.R
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
    }

    override fun fgRes(): Int = R.layout.fg_base_analysis_page
    //0 日 1 月 2 年 3 总
    protected var index: Int = 0

    override fun afterViews() {
        arguments?.let {
            index = it.getInt("index", 0)
        }
        val c = Calendar.getInstance()
        val initDate = c.get(Calendar.YEAR).toString() + "-" + (format(c.get(Calendar.MONTH) + 1)) + "-" + format(c.get(Calendar.DAY_OF_MONTH))
        if (index == 3) {//总
            tv_time.text = c.get(Calendar.YEAR).toString() + "-" + c.get(Calendar.YEAR).toString() + "年"
            select = c.get(Calendar.YEAR).toString()
        } else {
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
        if (month<10){
            return "0"+month
        }
        return month.toString()
    }

    abstract fun loadContent()


    private  var isHaveChild=true

    class Model{
        var name=""
        var ratio=""
        var kmh=""
        var value=0.0f
    }

    protected class AD_List(ac: Activity,list:List<Model>,isHaveChild:Boolean):BaseAdapter(){
        private var ac=ac
        private var list=list
        private  var isHaveChild=isHaveChild

        override fun getView(pos: Int, p1: View?, p2: ViewGroup?): View {
            var layout=ac.layoutInflater.inflate(R.layout.item_base_anlysis_page,null)
            var btn_next=layout.find<Button>(R.id.btn_next)
            if (isHaveChild){
                btn_next.visibility=View.VISIBLE
            }else{
                btn_next.visibility=View.GONE
            }

            var tv_name=layout.find<TextView>(R.id.tv_name)
            tv_name.text=list[pos].name
            var tv_ratio=layout.find<TextView>(R.id.tv_ratio)
            tv_ratio.text=list[pos].ratio
            var tv_kmh=layout.find<TextView>(R.id.tv_kmh)
            tv_kmh.text=list[pos].kmh

            return layout
        }

        override fun getItem(p0: Int): Any=list[p0]

        override fun getItemId(p0: Int): Long=p0.toLong()

        override fun getCount(): Int=list.size

    }


}