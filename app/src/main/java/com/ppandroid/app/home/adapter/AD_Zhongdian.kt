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
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import com.ppandroid.app.R
import com.ppandroid.app.base.AC_ContentFG
import com.ppandroid.app.bean.overview.BN_OverView
import com.ppandroid.app.home.FG_OverView
import com.ppandroid.app.home.mine.energyanalysis.devicesanalysis.FG_DeviceDetailAnalysis
import com.ppandroid.app.utils.Devices
import com.ppandroid.app.utils.Utils_Common
import com.ppandroid.app.widget.HorizontalPercentageView
import org.jetbrains.anko.find

/**
 * Created by yeqinfu on 2017/8/11.
 */
class AD_Zhongdian(ac: Activity, list: List<BN_OverView.MessageBean.DeviceInformationListBean>) : PagerAdapter() {
    var energyClassificationId= Devices.ELECTRIC
    /**横条图viewpager*/
    var arrays_title = arrayOf(
            "今日",
            "本月",
            "本年"

    )
    var views = ArrayList<View>()// 将要分页显示的View装入数组中
    var list: List<BN_OverView.MessageBean.DeviceInformationListBean>? = null
    var ac: Activity? = null
    var lisenter:FG_OverView.IHeightListener?=null

    init {
        this.ac = ac
        this.list = list
        /**viewpager*/
        val lf = ac.layoutInflater
        for (item in list) {
            var view1 = lf.inflate(R.layout.item_zhongdian, null)
            view1.viewTreeObserver.addOnGlobalLayoutListener {
               lisenter?.getHeight(view1.height)
            }
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
        return arrays_title[position]
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        container.addView(views[position])

        var lv_list = views[position].find<ListView>(R.id.lv_list)
        ac?.let {
            var adapter = AD_List(it, list?.get(position)?.deviceKwhMapList)
            lv_list.adapter = adapter
            lv_list.setOnItemClickListener { adapterView, view, i, l ->

                var item= list?.get(position)?.deviceKwhMapList?.get(i)
                if (!TextUtils.isEmpty(item?.id)){
                    var bundle= FG_DeviceDetailAnalysis.createBundle(energyClassificationId,item?.id?:"",item?.key?:"")
                    var intent= AC_ContentFG.createIntent(ac, FG_DeviceDetailAnalysis::class.java.name,bundle)
                    ac?.startActivity(intent)
                }

            }
        }
        return views[position]
    }

    class Holder {
        var tv_key: TextView? = null
        var tv_value: TextView? = null
        var v_hp: HorizontalPercentageView? = null
    }

    class AD_List(ac: Activity, instrumentMapList: List<BN_OverView.MessageBean.DeviceInformationListBean.DeviceKwhMapListBean>?) : BaseAdapter() {


        var colors = arrayOf(
                "#FA6D6F",
                "#FE8D25",
                "#FFB338",
                "#6CC37E",
                "#2ED9A4"
                )
        private var ac: Activity
        internal var instrumentMapList: List<BN_OverView.MessageBean.DeviceInformationListBean.DeviceKwhMapListBean>? = null


        private var max: Double = 0.0

        init {
            this.ac = ac
            this.instrumentMapList = instrumentMapList
        }

        override fun getView(p0: Int, v: View?, p2: ViewGroup?): View? {
            var layout: View? = null
            var holder: Holder? = null
            if (v != null) {
                layout = v
                holder = v.tag as Holder?
            } else {
                layout = ac.layoutInflater.inflate(R.layout.item_zhongdian_listview_item, null)
                holder = Holder()
                holder.tv_key = layout.find(R.id.tv_key)
                holder.tv_value = layout.find(R.id.tv_value)
                holder.v_hp = layout.find(R.id.v_hp)
                layout.tag = holder
            }
            holder?.let {
                it.tv_key?.text = instrumentMapList?.get(p0)?.key ?: ""
                it.tv_value?.text = instrumentMapList?.get(p0)?.value ?: ""


                var d=Utils_Common.findNumberFromStr(instrumentMapList?.get(p0)?.ratio?:"")
                var dd=Utils_Common.paraseDouble(d)/100f

                holder?.v_hp?.percentage =dd.toFloat()
                holder?.v_hp?.colorId = Color.parseColor(colors[p0%colors.size])
                holder?.v_hp?.startAnim()


            }
            return layout
        }

        override fun getItem(p0: Int): Any? {
            return instrumentMapList?.get(p0)
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
            return instrumentMapList?.size ?: 0
        }

    }

}