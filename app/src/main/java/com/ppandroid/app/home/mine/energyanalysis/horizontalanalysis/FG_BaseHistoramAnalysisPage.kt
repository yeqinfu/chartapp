package com.ppandroid.app.home.mine.energyanalysis.horizontalanalysis

import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.bruce.pickerview.popwindow.DatePickerPopWin
import com.ppandroid.app.R
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_base_histroam_analysis_page.*
import java.util.*

/**
 * Created by yeqinfu on 2017/9/15.
 */
abstract class FG_BaseHistoramAnalysisPage:FG_Base(){
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
    //0 日 1 月 2 年 3 总
    protected var index: Int = 0
    protected var parentId = "-1"

    override fun fgRes(): Int= R.layout.fg_base_histroam_analysis_page

    override fun afterViews() {
        arguments?.let {
            index = it.getInt("index", 0)
            parentId = it.getString("parentId", "-1")
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
            btn_search.setOnClickListener {
                showDatePop()
            }
        }
        loadContent()
        isFirstLoad=false
    }
    private var isFirstLoad:Boolean=true
  /*  override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (!isFirstLoad){
            DebugLog.d("http--->yeqinfu")
            loadContent()
        }
    }*/

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

}