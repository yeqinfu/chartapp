/*
 * Created by yeqinfu on 17-12-7 上午10:24
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home.news

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.ppandroid.app.R
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.news.BN_FaultHistory
import com.ppandroid.app.home.center.FG_FaultDetail
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_fault_history.*
import kotlinx.android.synthetic.main.layout_head_view.*
import org.jetbrains.anko.find

/**
 * Created by yeqinfu on 2017/12/7.
 */

class FG_FaultHistory:FG_Base(){
    override fun fgRes(): Int= R.layout.fg_fault_history

    override fun afterViews() {
        head_view.init(activity)
        head_view.setCenterTitle("故障报警")
        refresh_layout.setOnRefreshListener {
            loadContent(true)
        }
        refresh_layout.setOnLoadmoreListener{
            loadContent(false)
        }
        adapter= AD_List(activity,content)
        lv_list.adapter=adapter
        loadContent(true)
    }

    var adapter:AD_List?=null
    var pageNumber=1
    var totalPage=1
    private var content=ArrayList<BN_FaultHistory.MessageBean.FailueWarningListBean>()
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
        var url="user/failure/history.json?pageNumber=$pageNumber"
        Http.get(activity,url, BN_FaultHistory::class.java,object : MyCallBack<BN_FaultHistory> {
            override fun onResponse(response: BN_FaultHistory?) {

                response?.safeRun {
                    refresh_layout.finishRefresh()
                    refresh_layout.finishLoadmore()
                    if (!it.message.failueWarningList.isEmpty()){
                        content.addAll(it.message.failueWarningList)
                        adapter?.notifyDataSetChanged()
                        pageNumber++
                        totalPage=it.message.totalPage
                        lv_list.setOnItemClickListener { _, _, i, _ ->
                            var b= FG_FaultDetail.createBundle(it.message.failueWarningList[i])
                            startAC(FG_FaultDetail::class.java.name,b)
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
        var tv_msg_date: TextView?=null
        var tv_title: TextView?=null
        var tv_date: TextView?=null
        var tv_key: TextView?=null
        var tv_value: TextView?=null
        var iv_type: ImageView?=null
    }

    class AD_List(ac: Activity, list:ArrayList<BN_FaultHistory.MessageBean.FailueWarningListBean>) : BaseAdapter() {
        private var ac=ac
        private var list=list
        var colors = arrayOf(
                "#5EC1FF",
                "#45E069",
                "#FFCF3B",
                "#FF6969"
        )
        override fun getView(pos: Int, convertView: View?, p2: ViewGroup?): View? {
            var layout: View?=null
            var holder:Holder?=null
            if (convertView!=null){
                layout=convertView
                holder= layout.tag as Holder?

            }else{
                layout=ac.layoutInflater.inflate(R.layout.item_fault_history_list,null)
                holder= Holder()
                holder.tv_title=layout.find(R.id.tv_title)
                holder.tv_msg_date=layout.find(R.id.tv_msg_date)
                holder.tv_date=layout.find(R.id.tv_date)
                holder.tv_value=layout.find(R.id.tv_value)
                holder.iv_type=layout.find(R.id.iv_type)
                layout.tag = holder
            }
            holder?.let {
                it.tv_date?.text="时间："+list[pos].failueTime
                it.tv_msg_date?.text=list[pos].warningTime
                it.tv_title?.text="您好，您有一条故障报警提示"
                it.tv_value?.text=list[pos].status
                it.iv_type?.setImageResource(R.drawable.icon_guz)

            }

            return layout

        }

        override fun getItem(p0: Int): Any=list[p0]

        override fun getItemId(p0: Int): Long=p0.toLong()

        override fun getCount(): Int =list.size

    }


}
