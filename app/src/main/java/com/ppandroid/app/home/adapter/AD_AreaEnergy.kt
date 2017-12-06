/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home.adapter

import android.app.Activity
import android.graphics.Color
import android.support.v4.view.PagerAdapter
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.ppandroid.app.R
import com.ppandroid.app.base.AC_ContentFG
import com.ppandroid.app.bean.overview.BN_OverView
import com.ppandroid.app.home.mine.energyanalysis.devicesanalysis.FG_AreaDetailAnalysis
import com.ppandroid.app.utils.Utils_Common
import com.ppandroid.app.widget.HorizontalPercentageView
import org.jetbrains.anko.find

/**
 * Created by yeqinfu on 2017/8/11.
 */
class AD_AreaEnergy(ac: Activity, list: List<BN_OverView.MessageBean.OverviewConsumptionInformationBean.AreaInformationListBean>) : PagerAdapter() {
    /**横条图viewpager*/
    private var arrays_title = arrayOf(
            "今日",
            "本月",
            "本年"

    )
    var views = ArrayList<View>()// 将要分页显示的View装入数组中
    var list: List<BN_OverView.MessageBean.OverviewConsumptionInformationBean.AreaInformationListBean>? = null
    var ac: Activity? = null

    init {
        this.ac = ac
        this.list = list
        /**viewpager*/
        val lf = ac.layoutInflater
        for (item in list) {
            var view1 = lf.inflate(R.layout.item_energy, null)
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
        container.removeView(views[position])

    }

    override fun getItemPosition(`object`: Any?): Int {

        return super.getItemPosition(`object`)
    }

    override fun getPageTitle(position: Int): CharSequence {
        return arrays_title[position]
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        var view = views[position]
        var tv_key = view.find<TextView>(R.id.tv_key)
        var tv_value = view.find<TextView>(R.id.tv_value)
        var tv_key2 = view.find<TextView>(R.id.tv_key2)
        var tv_value2 = view.find<TextView>(R.id.tv_value2)
        var v_hp = view.find<HorizontalPercentageView>(R.id.v_hp)
        var v_hp2 = view.find<HorizontalPercentageView>(R.id.v_hp2)

        list?.get(position)?.areaKwhMapList?.let {
            if (it.size>0){
                tv_key.text = it[0]?.key
                tv_value.text = it[0]?.value
                var d = Utils_Common.findNumberFromStr(it[0]?.ratio)
                v_hp.percentage = Utils_Common.paraseDouble(d).toFloat()/100f
                v_hp2.colorId= Color.parseColor("#FA6D6F")
                v_hp.startAnim()

                var ll_first = view.find<LinearLayout>(R.id.ll_first2)
                ll_first.setOnClickListener {bd->
                    if (!TextUtils.isEmpty(it[0].id)){
                        var bundle= FG_AreaDetailAnalysis.createBundle(it[0].id,it[0].key)
                        var intent = AC_ContentFG.createIntent(ac, FG_AreaDetailAnalysis::class.java.name,bundle)
                        ac?.startActivity(intent)
                    }

                }

                var ll_second = view.find<LinearLayout>(R.id.ll_second)

                if (it.size>= 2) {
                    tv_key2.text = it[1]?.key
                    tv_value2.text = it[1]?.value
                    var d = Utils_Common.findNumberFromStr(it[1]?.ratio)
                    v_hp2.percentage = Utils_Common.paraseDouble(d).toFloat()/100f
                    v_hp2.colorId= Color.parseColor("#FE8D25")
                    v_hp2.startAnim()
                    ll_second.visibility = View.VISIBLE
                    ll_second.setOnClickListener {bd->
                        if (!TextUtils.isEmpty(it[1].id)){
                            var bundle= FG_AreaDetailAnalysis.createBundle(it[1].id,it[1].key)
                            var intent = AC_ContentFG.createIntent(ac, FG_AreaDetailAnalysis::class.java.name,bundle)
                            ac?.startActivity(intent)
                        }

                    }
                } else {
                    ll_second.visibility = View.GONE

                }
            }
        }


        container.addView(views[position])
        return views[position]
    }

}