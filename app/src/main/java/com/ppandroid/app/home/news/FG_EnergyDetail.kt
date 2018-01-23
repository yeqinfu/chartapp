/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home.news

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.ppandroid.app.R
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.news.BN_EnergyDetail
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.app.utils.Devices
import com.ppandroid.app.utils.Utils_Common
import com.ppandroid.app.widget.HorizontalPercentageView
import com.ppandroid.im.base.FG_Base
import com.ppandroid.im.utils.Contants
import kotlinx.android.synthetic.main.fg_energy_detail.*
import kotlinx.android.synthetic.main.layout_head_view.*
import org.jetbrains.anko.find

/**
 * Created by yeqinfu on 2017/9/21.
 */
class FG_EnergyDetail : FG_Base() {
    var energyClassificationId= Devices.ELECTRIC
    companion object {
        fun createBundle(energyClassificationId:String,id: String, title: String): Bundle {
            var b = Bundle()
            b.putString("id", id)
            b.putString("energyClassificationId", energyClassificationId)
            b.putString("title", title)
            return b
        }
    }

    private var title = ""
    private var id = ""

    override fun fgRes(): Int = R.layout.fg_energy_detail

    private var tag01="电"
    private var tag02="kwh"
    override fun afterViews() {
        arguments?.let {
            id = it.getString("id", "")
            title = it.getString("title", "")
            energyClassificationId = it.getString("energyClassificationId", Devices.ELECTRIC)
        }
        if (energyClassificationId=="2"){
            tag01="水"
            tag02=Contants.m3
        }
        head_view.init(activity)
        head_view.setCenterTitle(title + "用"+tag01+"情况")
        loadContent()
    }

    private fun loadContent() {
        var url = if (energyClassificationId==Devices.ELECTRIC){
            "user/energy/statistics/getConsumptionList.json?id=$id"
        }else{
            "user/water/statistics/getConsumptionList.json?id=$id"
        }
        Http.get(activity, url, BN_EnergyDetail::class.java, object : MyCallBack<BN_EnergyDetail> {
            override fun onResponse(response: BN_EnergyDetail?) {
                response?.safeRun {

                    tv_total_number.text = "总用"+tag01+"量（" + it.message.deviceNumber + "项）"
                    tv_total_kwh.text = it.message.consumptionSum + tag02
                    var adapter=AD_List(energyClassificationId,it.message.energyConsumptionDeviceList,activity)
                    lv_list.adapter=adapter

                }
            }

            override fun onError(error: ErrorBody?) {
                toast(error)
            }

        })
    }

    class Holder {
        var tv_name: TextView? = null
        var tv_this_value: TextView? = null
        var tv_this_modified: TextView? = null
        var tv_this_ratio: TextView? = null
        var tv_last_value: TextView? = null
        var tv_last_ratio: TextView? = null
        var v_hp: HorizontalPercentageView? = null
        var v_hp2: HorizontalPercentageView? = null
    }

    class AD_List(energyClassificationId:String,data: List<BN_EnergyDetail.MessageBean.EnergyConsumptionDeviceListBean>, context: Context) : BaseAdapter() {
        private var data = data
        private var energyClassificationId = energyClassificationId
        private var context = context
        private var tag01="电"
        private var tag02="kwh"
        init {
            if (energyClassificationId=="2"){
                tag01="水"
                tag02=Contants.m3
            }
        }
        override fun getView(pos: Int, convertView: View?, p2: ViewGroup?): View? {
            var view:View?=null
            var holder:Holder?=null
            if (convertView==null){
                view = LayoutInflater.from(context).inflate(R.layout.item_energy_detail, null)
                holder= Holder()
                holder.tv_name=view.find(R.id.tv_name)
                holder.tv_this_value=view.find(R.id.tv_this_value)
                holder.tv_this_modified=view.find(R.id.tv_this_modified)
                holder.tv_this_ratio=view.find(R.id.tv_this_ratio)
                holder.tv_last_value=view.find(R.id.tv_last_value)
                holder.tv_last_ratio=view.find(R.id.tv_last_ratio)
                holder.v_hp=view.find(R.id.v_hp)
                holder.v_hp2=view.find(R.id.v_hp2)
                view.tag=holder
            }else{
                view=convertView
                holder= view.tag as Holder?
            }
            holder?.let {
                var item=data[pos]
                it.tv_name?.text=item.deviceName
                it.tv_this_value?.text=item.thisDevice+tag02
                it.tv_this_ratio?.text=item.thisDeviceRatio
                it.tv_last_value?.text=item.lastDevice+tag02
                it.tv_last_ratio?.text=item.lastDeviceRatio
                var a=Utils_Common.parseNumberString(item.thisDevice)
                var b=Utils_Common.parseNumberString(item.lastDevice)
                it.tv_this_modified?.text=(a.minus(b)).toString()

                val drawable = if (a.minus(b)>0){
                    it.tv_this_modified?.setTextColor(context.resources.getColor(R.color.vcolor03))

                    context.resources.getDrawable(R.drawable.up_yellow)

                }else{
                    it.tv_this_modified?.setTextColor(context.resources.getColor(R.color.color_01))

                    context.resources.getDrawable(R.drawable.down_green)

                }
                drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
                it.tv_this_modified?.setCompoundDrawables(drawable,null, null, null)




                it.v_hp?.colorId=context.resources.getColor(R.color.vcolor03)
                it.v_hp2?.colorId=context.resources.getColor(R.color.vcolor05)

                var c=Utils_Common.parseNumberString(item.thisDeviceRatio)
                var d=Utils_Common.parseNumberString(item.lastDeviceRatio)

                it.v_hp?.percentage= (c/100f).toFloat()
                it.v_hp2?.percentage= (d/100f).toFloat()
                it.v_hp?.startAnim()
                it.v_hp2?.startAnim()


            }
            return view
        }

        override fun getItem(p0: Int): Any = data[p0]

        override fun getItemId(p0: Int): Long = p0.toLong()

        override fun getCount(): Int = data.size

    }


}