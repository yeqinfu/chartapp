/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home.news

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.ppandroid.app.R
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.news.BN_EnergyList
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.app.utils.DebugLog
import com.ppandroid.app.utils.Devices
import com.ppandroid.im.base.FG_Base
import com.ppandroid.im.utils.Contants
import kotlinx.android.synthetic.main.fg_energy_list.*
import kotlinx.android.synthetic.main.layout_head_view.*
import org.jetbrains.anko.find

/**
 * Created by yeqinfu on 2017/9/1.
 */
class FG_EnergyList :FG_Base(){
    override fun fgRes(): Int= R.layout.fg_energy_list
    var energyClassificationId= Devices.ELECTRIC
    override fun afterViews() {
        head_view.init(activity)

        arguments?.let {
            energyClassificationId=it.getString("energyClassificationId",Devices.ELECTRIC)
        }
        var title=if (energyClassificationId.equals(Devices.ELECTRIC)){
            "能耗汇总"
        }else{
            "用水量汇总"
        }
        head_view.setCenterTitle(title)
        refresh_layout.setOnRefreshListener {
            loadContent(true)
        }
        refresh_layout.setOnLoadmoreListener{
            loadContent(false)
        }
        adapter= AD_List(energyClassificationId,activity,content)
        lv_list.adapter=adapter
        loadContent(true)
    }

    var adapter:AD_List?=null
    var pageNumber=1
    var totalPage=1
    private var content=ArrayList<BN_EnergyList.MessageBean.EnergyConsumptionStatisticsDtoListBean>()
    private fun loadContent(isRefresh: Boolean){
        if (isRefresh){
            pageNumber=1
            totalPage=1
            content.clear()
        }
        if (pageNumber>totalPage){
            toast("已经是最后一页")
            refresh_layout.finishLoadmore()
            return
        }
        var url=if (energyClassificationId.equals(Devices.ELECTRIC)){
            "user/energy/statistics/get.json?pageNumber=$pageNumber"
        }else{
            "user/water/statistics/get.json?pageNumber=$pageNumber"
        }
        Http.get(activity,url, BN_EnergyList::class.java,object :MyCallBack<BN_EnergyList>{
            override fun onResponse(response: BN_EnergyList?) {

                response?.safeRun {
                    refresh_layout.finishRefresh()
                    refresh_layout.finishLoadmore()
                    if (!it.message.energyConsumptionStatisticsDtoList.isEmpty()){
                        content.addAll(it.message.energyConsumptionStatisticsDtoList)
                        adapter?.notifyDataSetChanged()
                        pageNumber++
                        totalPage=it.message.totalPages
                        lv_list.setOnItemClickListener { _, _, i, _ ->
                            var b=FG_EnergyDetail.Companion.createBundle(energyClassificationId,content[i].id.toString(),content[i].datePeriod)
                            startAC(FG_EnergyDetail::class.java.name,b)
                        }
                    }
                }
            }

            override fun onError(error: ErrorBody?) {
                refresh_layout.finishRefresh()
                refresh_layout.finishLoadmore()
                toast(error)
            }

        })
    }

    class Holder{
        var tv_msg_date:TextView?=null
        var tv_title:TextView?=null
        var tv_date:TextView?=null
        var tv_key:TextView?=null
        var tv_value:TextView?=null
        var iv_type:ImageView?=null
    }

    class AD_List(energyClassificationId:String,ac:Activity,list:ArrayList<BN_EnergyList.MessageBean.EnergyConsumptionStatisticsDtoListBean>) : BaseAdapter() {
        private var ac=ac
        private var list=list
        var energyClassificationId=energyClassificationId
        var colors = arrayOf(
                "#5EC1FF",
                "#45E069",
                "#FFCF3B",
                "#FF6969"
        )
        @SuppressLint("SetTextI18n")
        override fun getView(pos: Int, convertView: View?, p2: ViewGroup?): View? {
            DebugLog.d("======="+pos)
            var layout:View?=null
            var holder:Holder?=null
            if (convertView!=null){
                layout=convertView
                holder= layout.tag as Holder?

            }else{
                layout=ac.layoutInflater.inflate(R.layout.item_energy_list,null)
                holder= Holder()
                holder.tv_title=layout.find(R.id.tv_title)
                holder.tv_msg_date=layout.find(R.id.tv_msg_date)
                holder.tv_date=layout.find(R.id.tv_date)
                holder.tv_key=layout.find(R.id.tv_key)
                holder.tv_value=layout.find(R.id.tv_value)
                holder.iv_type=layout.find(R.id.iv_type)
                layout.tag = holder
            }
            holder?.let {
                it.tv_msg_date?.text=list[pos].recordDate
                var tag01="电"
                var tag02="kwh"
                if (energyClassificationId.equals("2")){
                    tag01="水"
                    tag02=Contants.m3
                }
                it.tv_date?.text=list[pos].datePeriod+"用"+tag01+"情况，请知晓"
                it.tv_value?.text=list[pos].totalKwh+tag02
                when(list[pos].type){
                    1->{
                        it.iv_type?.setImageResource(R.drawable.ic_day)
                        it.tv_title?.setTextColor(Color.parseColor(colors[0]))
                        it.tv_title?.text="企业日用"+tag01+"情况汇总"
                        it.tv_key?.text="今日用"+tag01+"："
                    }
                    2->{
                        it.iv_type?.setImageResource(R.drawable.ic_week)
                        it.tv_title?.setTextColor(Color.parseColor(colors[1]))
                        it.tv_title?.text="企业周用"+tag01+"情况汇总"
                        it.tv_key?.text="本周用"+tag01+"："
                    }
                    3->{
                        it.iv_type?.setImageResource(R.drawable.ic_month)
                        it.tv_title?.setTextColor(Color.parseColor(colors[2]))
                        it.tv_title?.text="企业月用"+tag01+"情况汇总"
                        it.tv_key?.text="本月用"+tag01+"："
                    }
                    4->{
                        it.iv_type?.setImageResource(R.drawable.ic_year)
                        it.tv_title?.setTextColor(Color.parseColor(colors[3]))
                        it.tv_title?.text="企业年用"+tag01+"情况汇总"
                        it.tv_key?.text="本年用"+tag01+"："
                    }

                }


            }

            return layout

        }

        override fun getItem(p0: Int): Any=list[p0]

        override fun getItemId(p0: Int): Long=p0.toLong()

        override fun getCount(): Int =list.size

    }


}