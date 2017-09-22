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
open class FG_BaseLineChart:FG_Base(){
    companion object {
        fun createBundle(index: Int): Bundle {
            var b = Bundle()
            b.putInt("index", index)
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

    override fun afterViews() {
        arguments?.let {
            index = it.getInt("index", 0)
        }
        val c = Calendar.getInstance()
        val initDate = c.get(Calendar.YEAR).toString() + "-" + format(c.get(Calendar.MONTH) + 1) + "-" + format(c.get(Calendar.DAY_OF_MONTH))

        var topText=""
        var topText2=""
        when(index){
            0->{
                topText="日累计"
                topText2="环比上日"
                tv_time.text=initDate
                tv_time.visibility= View.VISIBLE
                select=initDate
            }
            1->{
                topText="周累计"
                topText2="环比上周"
                tv_time.visibility= View.GONE
                select=""
            }
            2->{
                topText="月累计"
                topText2="环比上月"
                tv_time.visibility= View.GONE
                select= c.get(Calendar.YEAR).toString() + "-" + (format(c.get(Calendar.MONTH) + 1))
            }
            3->{
                topText="年累计"
                topText2="环比去年"
                tv_time.visibility= View.GONE
                select=c.get(Calendar.YEAR).toString()
            }
        }
        tv_01.text=topText
        tv_02.text=topText2











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
        //
        (0..6).mapTo(yValue) {
            val df = DecimalFormat("######0.00")
            df.format(it *max/6).toDouble() }
        chartview.isNeedSplit=true
        chartview.setValue(value,value2, xValue, yValue)
    }
    protected fun setSumRadioAvg(sum:String,radio:String,avg:Double){
        tv_sum.text=sum
        tv_radio.text=radio
        chartview.avgText=avg.toString()
        chartview.avgValue=avg

    }
    private fun format(month: Int): String {
        if (month < 10) {
            return "0" + month
        }
        return month.toString()
    }

}