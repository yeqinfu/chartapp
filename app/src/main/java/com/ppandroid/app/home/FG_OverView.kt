package com.ppandroid.app.home

import com.ppandroid.app.R
import com.ppandroid.app.widget.wheelview.FitChartHalf
import com.ppandroid.app.widget.wheelview.FitChartValue
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_over_view.*
import org.jetbrains.anko.find
import java.util.*

/**
 * Created by yeqinfu on 2017/8/3.
 */
class FG_OverView :FG_Base(){
    override fun fgRes():Int= R.layout.fg_over_view

    override fun afterViews() {

        val fitChart = view?.find<FitChartHalf>(R.id.fitChart)
        fitChart?.let {
            it.minValue=0f
            it.maxValue=100f
        }

        add.setOnClickListener {
            val resources = resources
            val values = ArrayList<FitChartValue>()
            values.add(FitChartValue(40f, resources.getColor(R.color.chart_value_1)))
            fitChart?.setValues(values)
        }
        btn_start.setOnClickListener {
            v_dount_view.startAnim()
        }
        btn_hp.setOnClickListener {
            v_hp.startAnim()
        }
    }

}