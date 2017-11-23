/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home.devices

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.bumptech.glide.Glide
import com.ppandroid.app.R
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.devices.BN_DevicesDetail
import com.ppandroid.app.home.mine.systemsetting.FG_AddDevices
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.app.utils.Utils_Common
import com.ppandroid.app.utils.glide.GlideUtils
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_devices_detail.*
import org.jetbrains.anko.find

/**
 * Created by yeqinfu on 2017/9/11.
 */
class FG_DevicesDetail:FG_Base(){
    override fun fgRes(): Int= R.layout.fg_devices_detail
    private var id: String = "-1"
    override fun afterViews() {
        var b = arguments
        b?.let {
            id = it.getString("id", "-1")
        }
        loadContent()
    }
    companion object {
        fun createBundle(id: String): Bundle {
            var b = Bundle()
            b.putString("id", id)
            return b
        }
    }
    fun loadContent(){
        var url="user/device/info.json?id=$id"
        Http.get(activity,url,BN_DevicesDetail::class.java,object :MyCallBack<BN_DevicesDetail>{
            override fun onResponse(response: BN_DevicesDetail?) {
                response?.let {
                    tv_device_name.text=it.message.name
                    when {
                        it.message.status == 2 -> tv_status001.setTextColor(Color.parseColor("#5ec1ff"))
                        it.message.status == 3 ->  tv_status001.setTextColor(Color.parseColor("#45e069"))
                        else ->tv_status001.setTextColor(Color.parseColor("#d5d5d5"))
                    }
                    tv_status001.text = it.message.statusString
                    val url = GlideUtils.addImageBaseUrl(it.message.photo)
                    Glide.with(activity).load(url).asBitmap().centerCrop().placeholder(R.drawable.pic_device_default).into(iv_img)
                    var modelBody= Utils_Common.parseJson(it.message.propertiesJson, FG_AddDevices.ModelBody::class.java)
                    var model=FG_AddDevices.Model()
                    model.key="设备型号"
                    model.value=it.message.model
                    if (modelBody?.list==null){
                        modelBody?.list=ArrayList()
                        modelBody?.list?.add(0,model)
                    }else{
                        modelBody?.list?.add(0,model)
                    }
                    modelBody?.list?.let {
                        var adapter=AD_List(activity,it)
                        gv_list.adapter=adapter

                    }

                }
            }
            override fun onError(error: ErrorBody?) {
                toast(error)
            }
        })
    }
    class Holder{
        var tv_key:TextView?=null
        var tv_value:TextView?=null

    }
    class AD_List(ac: Activity,list:ArrayList<FG_AddDevices.Model>) :BaseAdapter(){
        private var ac: Activity =ac
        var list:ArrayList<FG_AddDevices.Model> = list
        override fun getView(pos: Int, convertView: View?, p2: ViewGroup?): View? {
            var layout:View?=null
            var holder:Holder?=null
            if (convertView!=null){
                layout=convertView
                holder= layout.tag as Holder?
            }else{
                layout=ac.layoutInflater.inflate(R.layout.item_devices_detail,null)
                holder= Holder()
                holder.tv_key=layout.find(R.id.tv_key)
                holder.tv_value=layout.find(R.id.tv_value)
                layout.tag=holder
            }
            holder?.let {
                it.tv_key?.text=list[pos].key
                it.tv_value?.text=list[pos].value
            }
            return layout
        }

        override fun getItem(p0: Int): Any=list.get(p0)

        override fun getItemId(p0: Int): Long=p0.toLong()

        override fun getCount(): Int=list.size

    }
}