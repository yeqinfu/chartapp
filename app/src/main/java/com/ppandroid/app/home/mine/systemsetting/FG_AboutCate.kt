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
import com.ppandroid.app.base.AC_ContentFG
import com.ppandroid.app.base.NetWorkErrorView
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.mine.systemsetting.BN_DeviceCatePage
import com.ppandroid.app.bean.mine.systemsetting.ET_SyStemSetting
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.app.utils.DebugLog
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_about_cate.*
import kotlinx.android.synthetic.main.layout_head_view.*
import kotlinx.android.synthetic.main.layout_network_error.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.find

/**
 * Created by yeqinfu on 2017/8/29.
 */
class FG_AboutCate:FG_Base(){
    /**当是子页面的时候这个id有用到*/
    private var parentId:String="-1"
    companion object {

        fun createBundle(energyClassificationId:String,parentId:String): Bundle {
            DebugLog.d("======3==="+parentId)
            var b=Bundle()
            b.putString("parentId",parentId)
            b.putString("energyClassificationId",energyClassificationId)
            return b
        }

    }

    override fun fgRes(): Int= R.layout.fg_about_cate
    private var energyClassificationId="1"
    override fun afterViews() {
        isNeedEventBus=true
        arguments?.let {
            parentId=it.getString("parentId","-1")
            energyClassificationId=it.getString("energyClassificationId","1")
        }
        head_view.init(activity)
        head_view.setRightText("保存"){

            adapter?.let {
                if (it.checkPos==-1){
                    toast("请选择一项")
                }else{
                    var checkPos=it.checkPos
                    message?.let {
                        var chooseId=it[checkPos].id.toString()
                        var choooseName=it[checkPos].name
                        EventBus.getDefault().post(FG_AddDevices.ET_AddDevices(FG_AddDevices.ET_AddDevices.TASKID_ADD_DEVICES_ABOUT_CATE,chooseId,choooseName))
                        //关闭本页面和父级别页面
                        EventBus.getDefault().post(ET_SyStemSetting(ET_SyStemSetting.TASKID_CLOSE_FG_ABOUT_CATE))
                    }

                }
            }
        }
        loadContent()
    }
    var adapter:AD_List?=null
    private var message: List<BN_DeviceCatePage.MessageBean>? = null
    private fun loadContent() {
        var url = "user/sysSet/deviceCate/search.json"
        url+="?energyClassificationId="+energyClassificationId
        if (parentId!="-1"){
            url+="&parentId=$parentId"
        }
        Http.get(activity, url, BN_DeviceCatePage::class.java, object : MyCallBack<BN_DeviceCatePage> {
            override fun onResponse(response: BN_DeviceCatePage?) {
                response?.safeRun {
                    message=it.message
                    if (response.message.isEmpty()) {
                        network_error?.setViewType(NetWorkErrorView.NOT_DATA)
                    } else {
                        network_error?.setViewType(NetWorkErrorView.NORMAL_VIEW)
                        adapter = AD_List(activity, it.message,energyClassificationId)
                        lv_list.adapter = adapter
                    }

                }
            }

            override fun onError(error: ErrorBody?) {
                network_error.setViewType(NetWorkErrorView.NETWORK_ERROR)
                toast(error)
            }
        })
    }


    class Holder {
        var tv_name: TextView? = null
        var tv_leaf: TextView? = null
        var cb_check: CheckBox? = null

    }


    class AD_List(ac: Activity, message: List<BN_DeviceCatePage.MessageBean>,energyClassificationId: String) : BaseAdapter() {
        var checkPos = -1
        private var ac: Activity = ac
        private var message: List<BN_DeviceCatePage.MessageBean> = message
        private var energyClassificationId=energyClassificationId
        override fun getView(pos: Int, convertView: View?, p2: ViewGroup?): View? {
            var layout: View? = null
            var holder: Holder? = null
            if (convertView != null) {
                layout = convertView
                holder = layout.tag as Holder?
            } else {
                layout = ac.layoutInflater.inflate(R.layout.item_about_cate, null)
                holder = Holder()
                holder.cb_check = layout.find(R.id.cb_check)
                holder.tv_leaf = layout.find(R.id.tv_leaf)
                holder.tv_name = layout.find(R.id.tv_name)
                layout.tag = holder
            }
            holder?.cb_check?.isChecked = pos == checkPos
            holder?.cb_check?.setOnClickListener {
                checkPos=pos
                notifyDataSetChanged()
            }
            message?.get(pos)?.let {
                holder?.tv_name?.text = it.name ?: ""

                if (it.isIsLeaf) {
                    holder?.cb_check?.visibility=View.VISIBLE
                    holder?.tv_leaf?.visibility = View.GONE
                } else {
                    holder?.cb_check?.visibility=View.INVISIBLE
                    holder?.tv_leaf?.visibility = View.VISIBLE
                    holder?.tv_leaf?.setOnClickListener { _ ->
                        var b = FG_AboutCate.createBundle(""+it.id,energyClassificationId)
                        var it = AC_ContentFG.createIntent(ac, FG_AboutCate::class.java.name,b)
                        ac.startActivity(it)
                    }
                }

            }




            return layout
        }

        override fun getItem(p0: Int): BN_DeviceCatePage.MessageBean? {
            return message?.get(p0)
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
            return message?.size ?: 0

        }

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: ET_SyStemSetting) {
        if (event.taskId == ET_SyStemSetting.TASKID_CLOSE_FG_ABOUT_CATE) {
           finish()
        }
    }
}