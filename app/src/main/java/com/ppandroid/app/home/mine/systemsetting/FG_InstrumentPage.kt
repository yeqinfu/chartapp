/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home.mine.systemsetting

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ppandroid.app.R
import com.ppandroid.app.base.NetWorkErrorView
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.mine.systemsetting.BN_SystemSettingPage01
import com.ppandroid.app.bean.mine.systemsetting.ET_SyStemSetting
import com.ppandroid.app.home.mine.adapter.AD_SlideDeleteBase
import com.ppandroid.app.home.mine.adapter.OnClickListenerDetailOrDelete
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.app.utils.Devices
import com.ppandroid.app.widget.CustomDialog
import com.ppandroid.im.base.FG_Base
import com.ppandroid.im.bean.BaseBody
import kotlinx.android.synthetic.main.fg_system_setting_page.*
import kotlinx.android.synthetic.main.layout_head_view.*
import kotlinx.android.synthetic.main.layout_network_error.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.find

/**
 * Created by yeqinfu on 2017/8/22.
 * 仪表数据列表页面
 * 副级别页面和viewpage页面共用
 *
 * 暂时砍掉副级页面
 */
class FG_InstrumentPage : FG_Base() {
    /**default:0代表viewpager 1 代表点击item进去的详情*/
    private var pageType=0
    /**当是子页面的时候这个id有用到*/
    private var parentId:String=""
    private var parentName:String=""
    private var energyClassificationId= Devices.ELECTRIC
    companion object {
        fun createBundle(parentId:String,parentName:String,energyClassificationId:String): Bundle {
            var b=Bundle()
            b.putString("parentName",parentName)
            b.putString("parentId",parentId)
            b.putInt("pageType",1)
            b.putString("energyClassificationId",energyClassificationId)
            return b
        }

    }
    override fun fgRes(): Int = R.layout.fg_system_setting_page

    override fun afterViews() {
        arguments?.let {
            pageType=it.getInt("pageType",0)
            parentId=it.getString("parentId","")
            parentName=it.getString("parentName","")
            energyClassificationId=it.getString("energyClassificationId","1")
        }
        if (pageType==1){
            sub_title.visibility=View.VISIBLE
            head_view.visibility=View.VISIBLE
            head_view.setCenterTitle(parentName)
            head_view.init(activity)
            head_view.setIvRight(R.drawable.ic_add_model, {
                var b=FG_AddInstrument.createBundle(parentId,parentName,"")
                startAC(FG_AddInstrument::class.java.name,b)
            })
        }else{
            sub_title.visibility=View.GONE
        }
        isNeedEventBus=true
        loadContent()
        network_error.setListener { loadContent() }

       /* lv_list.setOnItemLongClickListener { adapterView, view, i, l ->
            message?.let {
                operatorId=it[i].id.toString()
                operatorName=it[i].name.toString()
                showChooseDialog()
            }
            true
        }*/
    }

    private var operatorId=""
    private var operatorName=""
    private fun showConfirmDialog() {
        val builder = AlertDialog.Builder(activity).setMessage("确定删除吗？").setCancelable(false)
        builder.setPositiveButton("确定") { _, _ ->
            deleteInstrument(operatorId)
        }
        builder.setNegativeButton("取消") { _, _ ->
            builder.create().dismiss()
        }
        builder.create().show()

    }

    private fun deleteInstrument(id: String) {
        var url = "user/sysSet/instrument/delete.json?id=" + id
        Http.get(activity, url, BaseBody::class.java, object : MyCallBack<BaseBody> {
            override fun onResponse(response: BaseBody?) {
                response?.let {
                    if (!isAdded){
                        return
                    }
                    if (it.isSuccess) {
                        toast("删除成功")
                        loadContent()
                    }
                }
            }

            override fun onError(error: ErrorBody?) {
                toast(error)
            }

        })
    }

    private fun loadContent() {
        var url = "user/sysSet/instrument/search.json"
        url+="?energyClassificationId="+energyClassificationId
        if (pageType==1){
            url+="&parentId=$parentId"
        }

        Http.get(activity, url, BN_SystemSettingPage01::class.java, object : MyCallBack<BN_SystemSettingPage01> {
            override fun onResponse(response: BN_SystemSettingPage01?) {
                response?.let {
                    if (!isAdded){
                        return
                    }
                    if (response.message.isEmpty()) {
                        network_error?.setViewType(NetWorkErrorView.NOT_DATA)
                    } else {
                        network_error?.setViewType(NetWorkErrorView.NORMAL_VIEW)
                        message = it.message
                        var adapter = AD_List(activity, it.message)
                        lv_list.adapter = adapter

                        adapter.setOnClickListenerEditOrDelete(object : OnClickListenerDetailOrDelete {
                            override fun OnClickListenerDetail(position: Int) {
                                message?.let {
                                    operatorId=it[position].id.toString()
                                    operatorName=it[position].name.toString()
                                    var b=FG_AddInstrument.createBundle(energyClassificationId,operatorId,parentName)
                                    startAC(FG_AddInstrument::class.java.name,b)
                                }
                            }

                            override fun OnClickListenerDelete(position: Int) {
                                message?.let {
                                    operatorId=it[position].id.toString()
                                    operatorName=it[position].name.toString()
                                    showConfirmDialog()
                                }
                            }

                        })

                       /* lv_list.setOnItemClickListener { adapterView, view, i, l ->

                            var b= createBundle(it.message[i].id.toString(),it.message[i].name)
                            startAC(FG_InstrumentPage::class.java.name,b)
                        }*/
                    }

                }
            }

            override fun onError(error: ErrorBody?) {
                network_error.setViewType(NetWorkErrorView.NETWORK_ERROR)
                toast(error)
            }

        })
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: ET_SyStemSetting) {
        if (event.taskId == ET_SyStemSetting.TASKID_REFRESH_INSTRUMENT_PAGE) {
            loadContent()
        }
    }


    private var dialog: CustomDialog? = null
    private fun showChooseDialog() {
        val mView = LayoutInflater.from(activity).inflate(R.layout.dialog_choose_modified_or_del, null)
        dialog = CustomDialog(activity, R.style.family_dialog_theme, mView, Gravity.CENTER, 4)
        mView.findViewById(R.id.rl_detail).setOnClickListener(dialogListener)
        mView.findViewById(R.id.rl_del).setOnClickListener(dialogListener)
        dialog?.show()
    }

    private val dialogListener = View.OnClickListener { v ->
        when (v.id) {
            R.id.rl_detail -> {
                var b=FG_AddInstrument.createBundle(energyClassificationId,operatorId,parentName)
                startAC(FG_AddInstrument::class.java.name,b)
                dialog?.dismiss()

            }
            R.id.rl_del -> {
                showConfirmDialog()
                dialog?.dismiss()
            }

        }
    }



    class Holder {
        var tv_name: TextView? = null
        var tv_level: TextView? = null
        var tv_number: TextView? = null
        var tv_detail: TextView? = null
        var tv_delete: TextView? = null

    }

    private var message: List<BN_SystemSettingPage01.MessageBean>? = null

    class AD_List(ac: Activity, message: List<BN_SystemSettingPage01.MessageBean>) : AD_SlideDeleteBase() {
        private var ac: Activity = ac
        private var message: List<BN_SystemSettingPage01.MessageBean>? = null

        init {
            this.message = message
        }

        override fun getView(pos: Int, convertView: View?, p2: ViewGroup?): View? {
            var layout: View? = null
            var holder: Holder? = null
            if (convertView != null) {
                layout = convertView
                holder = layout.tag as Holder?
            } else {
                layout = ac.layoutInflater.inflate(R.layout.item_fg_sys_setting_page, null)
                holder = Holder()
                holder.tv_number = layout.find(R.id.tv_number)
                holder.tv_level = layout.find(R.id.tv_level)
                holder.tv_name = layout.find(R.id.tv_name)
                holder.tv_detail = layout.find(R.id.tv_detail)
                holder.tv_delete = layout.find(R.id.tv_delete)
                layout.tag = holder
            }

            holder?.tv_name?.text = message?.get(pos)?.name ?: ""
            holder?.tv_level?.text = (message?.get(pos)?.level ?: 1).toString() + "级"
            holder?.tv_number?.text = message?.get(pos)?.code ?: ""
            holder?.tv_delete?.setOnClickListener {
                onClickListenerDetailOrDelete?.OnClickListenerDelete(pos)
            }
            holder?.tv_detail?.setOnClickListener {
                onClickListenerDetailOrDelete?.OnClickListenerDetail(pos)
            }
            return layout
        }

        override fun getItem(p0: Int): BN_SystemSettingPage01.MessageBean? {
            return message?.get(p0)
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
            return message?.size ?: 0

        }

    }



}