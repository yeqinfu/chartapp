package com.ppandroid.app.home.mine.energyanalysis

import android.app.Activity
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.bruce.pickerview.popwindow.DatePickerPopWin
import com.ppandroid.app.R
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.mine.energyanalysis.BN_History
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_history.*
import kotlinx.android.synthetic.main.layout_head_view.*
import org.jetbrains.anko.find
import java.util.*


/**
 * Created by yeqinfu on 2017/8/30.
 */
class FG_History:FG_Base(){
    override fun fgRes(): Int= R.layout.fg_history

    override fun afterViews() {

        head_view.init(activity)
        head_view.setCenterTitle("历史查询")
        ll_year.setOnClickListener {
            showDatePop()
        }
    }

    fun loadContent(){
        var url="user/energy/analysis/getYearHistory.json?year=$select"
        Http.get(activity,url, BN_History::class.java,object :MyCallBack<BN_History>{
            override fun onResponse(response: BN_History?) {
                response?.let {
                    var adapter=AD_List(it.message,activity)
                    lv_list.adapter=adapter
                }
            }

            override fun onError(error: ErrorBody?) {
                toast(error)
            }

        })
    }

    class AD_List(message: List<String>,ac:Activity) :BaseAdapter(){
        private var message: List<String> = message
        private var ac: Activity =ac
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var layout=ac.layoutInflater.inflate(R.layout.item_history,null)
            var tv_key=layout.find<TextView>(R.id.tv_key)
            var tv_value=layout.find<TextView>(R.id.tv_value)
            tv_key.text=(p0+1).toString()+"月"
            tv_value.text=message[p0]
            return layout
        }

        override fun getItem(p0: Int): Any =message[p0]

        override fun getItemId(p0: Int): Long=p0.toLong()

        override fun getCount(): Int=message.size

    }


    var select=""
    private fun showDatePop() {
        val c = Calendar.getInstance()
        val initDate = c.get(Calendar.YEAR).toString() + "-" + (c.get(Calendar.MONTH) + 1).toString() + "-" + c.get(Calendar.DAY_OF_MONTH)
        val pickerPopWin = DatePickerPopWin.Builder(activity, DatePickerPopWin.OnDatePickedListener { year, month, day, dateDesc ->
            tv_choose.text = year.toString()
            select=year.toString()
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
        pickerPopWin.monthLoopView.visibility=View.GONE
        pickerPopWin.dayLoopView.visibility=View.GONE
        pickerPopWin.showPopWin(activity)
    }

}