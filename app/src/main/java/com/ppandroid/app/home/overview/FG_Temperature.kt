/*
 * Created by yeqinfu on 18-1-2 上午9:21
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home.overview

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Matrix
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.TextView
import com.ppandroid.app.R
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.overview.BN_Temperature
import com.ppandroid.app.bean.overview.BN_TemperatureItem
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.app.utils.toast.ToastUtil
import com.ppandroid.app.widget.linechart.MultipleChartView
import com.ppandroid.app.widget.popwindow.Pop_DatePicker
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_coal_gas.*
import org.jetbrains.anko.find
import java.text.DecimalFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by yeqinfu on 2018/1/2.
 */
class FG_Temperature :FG_Base(){
    override fun fgRes(): Int= R.layout.fg_coal_gas

    private var pageNumber=1
    private var totalPage=1
    private var pageSize=10
    private var content=ArrayList<BN_Temperature.MessageBean.DevicePageBean.ResultBean>()
    var adapter:AD_Ex?=null
    override fun refresh() {
        super.refresh()
        loadContent(true)
    }
    override fun afterViews() {
        adapter=AD_Ex(activity,content)
        lv_ex.setAdapter(adapter)
        loadContent(true)
        refreshLayout.setOnRefreshListener {
            loadContent(true)
        }
        refreshLayout.setOnLoadmoreListener{
            loadContent(false)
        }
    }

    private fun loadContent(isRefresh:Boolean){
        if (isRefresh){
            pageNumber=1
            totalPage=1
            content.clear()
        }
        if (pageNumber>totalPage){
            toast("已经是最后一页")
            refreshLayout.finishLoadmore()
            return
        }

        var url="user/overview/temperature/index.json?pageNumber=$pageNumber&pageSize=$pageSize"
        Http.get(activity,url, BN_Temperature::class.java,object :MyCallBack<BN_Temperature>{
            override fun onResponse(response: BN_Temperature?) {
                refreshLayout.finishRefresh()
                refreshLayout.finishLoadmore()
                response?.let {
                    if (!it.message.devicePage.result.isEmpty()){
                        content.addAll(it.message.devicePage.result)
                        adapter?.notifyDataSetChanged()
                        pageNumber++
                        totalPage=it.message.devicePage.totalPages
                    }

                    lv_ex.setOnGroupCollapseListener { var1 ->
                        content[var1].isOpen = false
                        adapter?.notifyDataSetChanged()
                    }
                    lv_ex.setOnGroupExpandListener { var1 ->
                        content[var1].isOpen = true
                        adapter?.notifyDataSetChanged()
                    }
                    adapter?.let {
                        for (i in 0 until it.groupCount) {
                            lv_ex.expandGroup(i)
                        }
                    }





                }

            }

            override fun onError(error: ErrorBody?) {
                refreshLayout.finishRefresh()
                refreshLayout.finishLoadmore()
                toast(error)
            }

        })
    }


    class AD_Ex(ac: Activity, content:ArrayList<BN_Temperature.MessageBean.DevicePageBean.ResultBean>) : BaseExpandableListAdapter() {
        private var content=content
        private var ac=ac
        override fun getGroup(p0: Int): Any=content[p0]

        override fun isChildSelectable(p0: Int, p1: Int): Boolean=true

        override fun hasStableIds(): Boolean =false

        override fun getGroupView(parentPos: Int, p1: Boolean, convertView: View?, p3: ViewGroup?): View? {

            var view: View= convertView ?: ac.layoutInflater.inflate(R.layout.item_parent_temperature,null)

            var title=view.find<TextView>(R.id.parent_title)
            title.text=content[parentPos].name

            var tv_msg=view.find<TextView>(R.id.tv_msg)
            tv_msg.text="当前温度："+content[parentPos].temperature+" 相对湿度："+content[parentPos].humidity

            val iv_arrow = view.findViewById(R.id.iv_arrow) as ImageView
            if (content[parentPos].isOpen){
                iv_arrow.setImageResource(R.drawable.icon_arrar)
            }else{
                val res = ac.resources
                val img = BitmapFactory.decodeResource(res, R.drawable.icon_arrar)
                val matrix = Matrix()
                matrix.postRotate(180f) //翻转180度
                val width = img.width
                val height = img.height
                val img_a = Bitmap.createBitmap(img, 0, 0, width, height, matrix, true)
                iv_arrow.setImageBitmap(img_a)
            }
            return view

        }

        override fun getChildrenCount(p0: Int): Int= 1

        override fun getChild(parentPos: Int, childPos: Int): Any= content[parentPos].temperatureList[childPos]

        override fun getGroupId(p0: Int): Long=p0.toLong()

        override fun getChildView(parentPos: Int, p1: Int, p2: Boolean, convertView: View?, p4: ViewGroup?): View {

            var view: View= convertView ?: ac.layoutInflater.inflate(R.layout.item_child_temperature,null)
            var v_chart_multi=view.find<MultipleChartView>(R.id.v_chart_multi)

            var rg_all=view.find<RadioGroup>(R.id.rg_all)
            val c = Calendar.getInstance()
            if (TextUtils.isEmpty(content[parentPos].select)){
                content[parentPos].select=c.get(Calendar.YEAR).toString() + "-" + (format(c.get(Calendar.MONTH) + 1)) + "-" + format(c.get(Calendar.DAY_OF_MONTH))
            }
            var tv_time=view.find<TextView>(R.id.tv_time)
            tv_time.text = content[parentPos].select
            tv_time.setOnClickListener {
                var type=0
                var pop= Pop_DatePicker(ac,type)
                pop.setInitStr(content[parentPos].select)
                pop.listener=object : Pop_DatePicker.IChooseListener{
                    override fun choose(year: String, month: String, day: String) {
                        content[parentPos].select = "$year-$month-$day"
                        tv_time.text = content[parentPos].select

                        loadItemInfo(parentPos,content[parentPos].id,content[parentPos].select)
                        pop?.dismiss()
                    }

                }
                pop.showPopupWindow()
            }

            rg_all.setOnCheckedChangeListener { _, i ->
                if (i==R.id.rb_001){//显示温度
                    content[parentPos].isShowTemp=true
                    notifyDataSetChanged()

                }else{//显示相对湿度
                    content[parentPos].isShowTemp=false
                    notifyDataSetChanged()
                }
            }
            var xValue=ArrayList<String>()
            var yMax=0.0
            //值集合
            var listValue=ArrayList<MultipleChartView.BrokenLine>()
            var target= MultipleChartView.BrokenLine()
            var values= LinkedHashMap<String, Double> ()    //折线对应的数据
            if (content[parentPos].isShowTemp){//显示温度
                for(item in content[parentPos].temperatureList){
                    xValue.add(item.hour.toString())
                    if (yMax<item.temperature){
                        yMax=item.temperature.toDouble()
                    }
                    values.put(item.hour.toString(),item.temperature.toDouble())
                }
                target.lineColor= Color.parseColor("#6b6b6b")
                target.linePointColor= Color.parseColor("#00bd0b")

            }else{//显示相对湿度
                for(item in content[parentPos].temperatureList){
                    xValue.add(item.hour.toString())
                    if (yMax<item.humidity){
                        yMax=item.humidity.toDouble()
                    }
                    values.put(item.hour.toString(),item.humidity.toDouble())
                }
                target.lineColor= Color.parseColor("#6b6b6b")
                target.linePointColor= Color.parseColor("#FE8D25")
            }

            target.value=values
            listValue.add(target)

            val df = DecimalFormat("######0.00")
            /**yValue根据最大值六等分获得*/
            var yValue=ArrayList<Double>()
            //
            (0..6).mapTo(yValue) {
                var yMax=if (yMax<=0){
                    6.0
                }else{
                    yMax
                }
                df.format(it *yMax/6).toDouble()
            }
            v_chart_multi?.isNeedSplit=true
            v_chart_multi.setValue(listValue,xValue,yValue,df.format(yMax))



            return view
        }

        /**
         * 更新item数据
         * */
        private fun loadItemInfo(parentPos: Int, id: String?, select: String?) {
            var url="user/overview/temperature/device/data.json?id=$id&date=$select"
            Http.get(ac,url, BN_TemperatureItem::class.java,object :MyCallBack<BN_TemperatureItem>{
                override fun onResponse(response: BN_TemperatureItem?) {
                    response?.let {
                        if (it.message.temperatureList.isEmpty()){
                            toast("当前日期无数据")
                        }else{
                            content[parentPos].temperatureList=it.message.temperatureList
                            notifyDataSetChanged()
                        }
                    }
                }

                override fun onError(error: ErrorBody?) {
                    toast(error)
                }

            })

        }
        protected fun toast(error: ErrorBody?) {
            toast(error?.message ?: "")
        }

        protected fun toast(msg: String?) {
            msg?.let {
                if (!TextUtils.isEmpty(msg)) {
                    ToastUtil.toast(ac, it)
                }
            }

        }

        override fun getChildId(p0: Int, p1: Int): Long =p1.toLong()

        override fun getGroupCount(): Int=content.size

        private fun format(month: Int): String {
            if (month < 10) {
                return "0" + month
            }
            return month.toString()
        }


    }

}