/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home.mine.energyanalysis.totalhorzizontalanalysis

import android.os.Bundle
import android.view.View
import com.ppandroid.app.R
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_base_line_chart.*
import java.text.DecimalFormat
import java.util.*
import kotlin.collections.LinkedHashMap

/**
 * Created by yeqinfu on 2017/9/18.
 */
open abstract class FG_BaseLineChart:FG_Base(){
    companion object {
        fun createBundle(energyClassificationId:String,index: Int): Bundle {
            var b = Bundle()
            b.putInt("index", index)
            b.putString("energyClassificationId", energyClassificationId)
            return b
        }


    }
    override fun fgRes(): Int= R.layout.fg_base_line_chart
    //x轴坐标对应的数据
    protected val xValue = ArrayList<String>()
    //y轴坐标对应的数据
    protected val yValue = ArrayList<Double>()
    //折线对应的数据
    protected val value = LinkedHashMap<String, Double>()
    //数据源2
    protected val value2 = LinkedHashMap<String, Double>()

    //0 日 1 周 2 月 3 年
    protected var index: Int = 0
    protected var select = ""
    protected var energyClassificationId="1"
    override fun afterViews() {
        arguments?.let {
            index = it.getInt("index", 0)
            energyClassificationId = it.getString("energyClassificationId", "1")
        }
        val c = Calendar.getInstance()
        val initDate = c.get(Calendar.YEAR).toString() + "-" + format(c.get(Calendar.MONTH) + 1) + "-" + format(c.get(Calendar.DAY_OF_MONTH))

        var topText=""
        var topText2=""
        when(index){
            0->{
                topText="日累计"
                topText2=getKeyStr()+"比上日"
                tv_time.text=initDate
                tv_time.visibility= View.VISIBLE
                select=initDate
                tv_before.text= c.get(Calendar.YEAR).toString() + "年" + format(c.get(Calendar.MONTH) + 1) + "月"+format(c.get(Calendar.DAY_OF_MONTH)-1)+"日"
                tv_this.text= c.get(Calendar.YEAR).toString() + "年" + format(c.get(Calendar.MONTH) + 1) + "月"+format(c.get(Calendar.DAY_OF_MONTH))+"日"

            }
            1->{
                topText="周累计"
                topText2=getKeyStr()+"比上周"
                tv_time.visibility= View.GONE
                select=""
                tv_this.text="本周用电"
                tv_before.text=getKeyStr()+"比上周"
            }
            2->{
                topText="月累计"
                topText2=getKeyStr()+"比上月"
                tv_time.visibility= View.GONE
                select= c.get(Calendar.YEAR).toString() + "-" + (format(c.get(Calendar.MONTH) + 1))
                tv_before.text= c.get(Calendar.YEAR).toString() + "年" + format(c.get(Calendar.MONTH)) + "月"
                tv_this.text= c.get(Calendar.YEAR).toString() + "年" + format(c.get(Calendar.MONTH) + 1) + "月"

            }
            3->{
                topText="年累计"
                topText2=getKeyStr()+"比去年"
                tv_time.visibility= View.GONE
                select=c.get(Calendar.YEAR).toString()
                tv_before.text= (c.get(Calendar.YEAR)-1).toString() + "年"
                tv_this.text= c.get(Calendar.YEAR).toString() + "年"
            }
        }
        tv_01.text=topText
        tv_02.text=topText2
        tv_before.setOnClickListener {
            chartview.isShowValue1=!chartview.isShowValue1
        }
        tv_this.setOnClickListener {
            chartview.isShowValue2=!chartview.isShowValue2
        }











        xValue.clear()
        yValue.clear()
        value.clear()
        value2.clear()
     /*   for (i in 0..22) {
            xValue.add((i + 1).toString())
            value.put((i + 1).toString(), (Math.random() * 20 + 60))//60--80
            value2.put((i + 1).toString(), (Math.random() * 20 + 60))//60--80
        }

        (0..5).mapTo(yValue) { it * 60 }
        chartview.setValue(value,value2, xValue, yValue)*/
    }

    abstract fun getKeyStr(): String

    protected fun setValueAll(){
        var max= 0.0
        for (item in value){
            if (max<item.value){
                max=item.value
            }
        }
        for (item in value2){
            if (max<item.value){
                max=item.value
            }
        }

        //向上取整纵坐标六等分
        max=Math.ceil(max)
        val df = DecimalFormat("######0.00")
        //
        (0..6).mapTo(yValue) {
            df.format(it *max/6).toDouble() }
        if (index!=3&&index!=1){
            chartview?.isNeedSplit=true
        }
        chartview?.setValue(value,value2, xValue, yValue,df.format(max))
    }
    protected fun setSumRadioAvg(sum:String,radio:String,avg:Double){
        tv_sum?.text=sum
        tv_radio?.text=radio
        chartview?.avgText=avg.toString()
        chartview?.avgValue=avg

    }
    private fun format(month: Int): String {
        if (month < 10) {
            return "0" + month
        }
        return month.toString()
    }

}