/*
 * Created by yeqinfu on 17-12-29 下午5:00
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home.adapter

import android.app.Activity
import android.graphics.Color
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ppandroid.app.R
import com.ppandroid.app.bean.overview.BN_OverViewWater
import com.ppandroid.app.utils.DebugLog
import com.ppandroid.app.utils.Utils_Common
import com.ppandroid.app.widget.graphical.chart.PieData
import com.ppandroid.app.widget.graphical.demoview.DountChart01View
import com.ppandroid.im.utils.Contants
import org.jetbrains.anko.find

/**
 * Created by yeqinfu on 2017/8/11.
 */
class AD_EnergyWater(ac: Activity, list: List<BN_OverViewWater.MessageBean.OverviewConsumptionInformationBean.ClassificationInformationListBean>) : PagerAdapter() {
    var views = ArrayList<View>()// 将要分页显示的View装入数组中
    var list: List<BN_OverViewWater.MessageBean.OverviewConsumptionInformationBean.ClassificationInformationListBean>? = null
    var ac: Activity? = null

    init {
        this.ac = ac
        this.list = list
        /**饼图viewpager*/
        val lf = ac.layoutInflater
        for (item in list) {
            var view1 = lf.inflate(R.layout.item_yongdian, null)
            views.add(view1)
        }
    }


    override fun isViewFromObject(arg0: View, arg1: Any): Boolean {

        return arg0 === arg1
    }

    override fun getCount(): Int {
        return views.size
    }

    override fun destroyItem(container: ViewGroup, position: Int,
                             `object`: Any) {
        container.removeView(views.get(position))

    }

    override fun getItemPosition(`object`: Any?): Int {

        return super.getItemPosition(`object`)
    }

    override fun getPageTitle(position: Int): CharSequence {
        return ""
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        var tv_totalKwh = views[position].find<TextView>(R.id.tv_totalKwh)
        var tv_title = views[position].find<TextView>(R.id.tv_title)
        var tv_kwh = views[position].find<TextView>(R.id.tv_kwh)
        when (position) {
            0 -> tv_title.text="今日用水"
            1 -> tv_title.text="本月用水"
            else -> tv_title.text="本年用水"
        }
        tv_totalKwh.text = Utils_Common.findNumberFromStr(list?.get(position)?.totalM3 ?: "")
        var d=list?.get(position)?.totalM3?:""
        if (d.contains("万")){
            tv_kwh.text="万"+Contants.m3
        }else{
            tv_kwh.text=Contants.m3
        }

        var v_dount_view = views[position].find<DountChart01View>(R.id.v_dount_view)
        v_dount_view.charRender(getList(list?.get(position)))

        container.addView(views[position])

        return views[position]
    }

    var colors = arrayOf(
            "#FA6D6F",
            "#FE8D25",
            "#FFB338",
            "#6CC37E",
            "#2ED9A4"
    )

    private fun getList(source: BN_OverViewWater.MessageBean.OverviewConsumptionInformationBean.ClassificationInformationListBean?): List<PieData>? {
        var list = ArrayList<PieData>()
        source?.let {
            it.classificationM3MapList
                    .map { Utils_Common.findNumberFromStr(it.ratio) }
                    .mapIndexedTo(list) { i, dd ->
                        var number= Utils_Common.paraseDouble(dd)
                        DebugLog.d("yeqinfu------>" + number)
                        PieData("", dd, number, Color.parseColor(colors[i % colors.size]))
                    }
        }
        return list
    }


}