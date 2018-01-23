/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home.mine.systemsetting

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.TextView
import com.ppandroid.app.R
import com.ppandroid.app.base.NetWorkErrorView
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.mine.systemsetting.BN_ChooseInstrument
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_choose_instruemnt.*
import kotlinx.android.synthetic.main.layout_head_view.*
import kotlinx.android.synthetic.main.layout_network_error.*
import org.greenrobot.eventbus.EventBus
import org.jetbrains.anko.find

/**
 * Created by yeqinfu on 2017/8/22.
 * 选择重点设备
 */
class FG_ChooseInstrument : FG_Base() {
    override fun fgRes(): Int = R.layout.fg_choose_instruemnt
    private var energyClassificationId="1"
    companion object {
        fun createBundle(energyClassificationId:String,chooseId:Long):Bundle{
            var b=Bundle()
            b.putString("energyClassificationId",energyClassificationId)
            b.putLong("chooseId",chooseId)
            return b
        }
    }
    private var chooseId:Long=-1L
    override fun afterViews() {
        arguments?.let {
            chooseId=it.getLong("chooseId",-1L)
            energyClassificationId=it.getString("energyClassificationId","1")
        }

        loadContent()
        head_view.init(activity)
        head_view.setCenterTitle("选择重点设备")
        head_view.setRightText("保存") {
            saveInfo()
        }
        network_error.setListener {
            loadContent()
        }

    }

    var adapter: AD_List? = null
    lateinit var body: BN_ChooseInstrument
    private fun loadContent() {
        var url = "user/sysSet/instrument/device.json"
        url+="?energyClassificationId="+energyClassificationId
        Http.get(activity, url, BN_ChooseInstrument::class.java, object : MyCallBack<BN_ChooseInstrument> {
            override fun onResponse(response: BN_ChooseInstrument?) {
                response?.safeRun {

                    body = it
                    adapter = AD_List(activity, it.message)
                    if (chooseId!=-1L){
                        for (i in 0 until it.message.size) {
                            if (it.message[i].id == chooseId) {
                                adapter?.checkPos=i
                                break
                            }
                        }
                    }
                    lv_list.adapter = adapter

                    if (it.message.isEmpty()){
                        network_error?.setViewType(NetWorkErrorView.NOT_DATA)
                    }else{
                        network_error?.setViewType(NetWorkErrorView.NORMAL_VIEW)
                    }

                }

            }

            override fun onError(error: ErrorBody?) {
                toast(error)
                network_error?.setViewType(NetWorkErrorView.NETWORK_ERROR)
            }

        })
    }

    private fun saveInfo() {
        var pos = adapter?.checkPos ?: -1
        if (pos == -1) {
            toast("请选择一项")
            return
        }
        var id = body.message[pos].id
        var name = body.message[pos].name
        EventBus.getDefault().post(FG_AddInstrument.ET_AddInstrument(FG_AddInstrument.ET_AddInstrument.TASKID_REFRESH, id, name))
        finish()


    }

    class Holder {
        var tv_name: TextView? = null
        var cb_check: CheckBox? = null
    }

    class AD_List(ac: Activity, list: List<BN_ChooseInstrument.MessageBean>) : BaseAdapter() {
        var checkPos = -1
        private var ac: Activity = ac
        private var list: List<BN_ChooseInstrument.MessageBean> = list
        override fun getView(pos: Int, convertView: View?, p2: ViewGroup?): View? {
            var layout: View? = null
            var holder: Holder? = null
            if (convertView != null) {
                layout = convertView
                holder = layout.tag as Holder?
            } else {
                layout = ac.layoutInflater.inflate(R.layout.item_choose_instrument, null)
                holder = Holder()
                holder.tv_name = layout.find(R.id.tv_name)
                holder.cb_check = layout.find(R.id.cb_check)
                layout.tag = holder
            }
            holder?.cb_check?.isChecked = pos == checkPos
            holder?.tv_name?.text = list?.get(pos)?.name
            holder?.cb_check?.setOnClickListener {
                checkPos=pos
                notifyDataSetChanged()
            }
            return layout

        }

        override fun getItem(p0: Int): BN_ChooseInstrument.MessageBean? {
            return list?.get(p0)
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
            return list?.size
        }

    }

}