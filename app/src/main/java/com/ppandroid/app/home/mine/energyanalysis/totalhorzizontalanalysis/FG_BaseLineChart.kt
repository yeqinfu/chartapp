package com.ppandroid.app.home.mine.energyanalysis.totalhorzizontalanalysis

import com.ppandroid.app.R
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_base_line_chart.*
import java.util.*

/**
 * Created by yeqinfu on 2017/9/18.
 */
open class FG_BaseLineChart:FG_Base(){
    override fun fgRes(): Int= R.layout.fg_base_line_chart
    //x轴坐标对应的数据
    private val xValue = ArrayList<String>()
    //y轴坐标对应的数据
    private val yValue = ArrayList<Int>()
    //折线对应的数据
    private val value = HashMap<String, Int>()
    override fun afterViews() {
        xValue.clear()
        yValue.clear()
        value.clear()
        for (i in 0..11) {
            xValue.add((i + 1).toString())
            value.put((i + 1).toString(), (Math.random() * 181 + 60).toInt())//60--240
        }

        for (i in 0..5) {
            yValue.add(i * 60)
        }
        chartview.setValue(value, xValue, yValue)
    }
}