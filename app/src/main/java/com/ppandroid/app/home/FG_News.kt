/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.ppandroid.app.R
import com.ppandroid.app.bean.ET_RedPoint
import com.ppandroid.app.home.news.FG_EnergyComparison
import com.ppandroid.app.home.news.FG_EnergyList
import com.ppandroid.app.home.news.FG_FaultHistory
import com.ppandroid.app.home.news.FG_SystemNewList
import com.ppandroid.app.utils.DebugLog
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_news.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.find
import java.util.*

/**
 * Created by yeqinfu on 2017/8/3.
 */
class FG_News : FG_Base() {
    override fun fgRes(): Int = R.layout.fg_news

    var dataSet = ArrayList<BN_Data>()

    @Subscribe(threadMode = ThreadMode.MAIN)
    open fun onMessageEvent(event: ET_RedPoint) {
        if (event.taskId === ET_RedPoint.TASKID_RED_POINT_SHOW) {//显示小红点
            if (event.type.equals("2")) {//显示通讯故障小红点
                EventBus.getDefault().post(ET_RedPoint(ET_RedPoint.TASKID_RED_POINT_SHOW_MAIN, "2"))
                if (dataSet.size > 1) {
                    dataSet[1].msg = event.msg
                    dataSet[1].showRedPoint = true
                    adapter?.notifyDataSetChanged()
                }


            } else if (event.type.equals("1")) {//显示能耗总会小红点
                EventBus.getDefault().post(ET_RedPoint(ET_RedPoint.TASKID_RED_POINT_SHOW_MAIN, "1"))
                if (!dataSet.isEmpty()) {
                    dataSet[0].showRedPoint = true
                    adapter?.notifyDataSetChanged()
                }

            }

        } else if (event.taskId === ET_RedPoint.TASKID_RED_POINT_HIDE) {//隐藏小红点
            if (event.type.equals("2")) {//隐藏通讯故障小红点
                EventBus.getDefault().post(ET_RedPoint(ET_RedPoint.TASKID_RED_POINT_HIDE_MAIN, "2"))
                if (dataSet.size > 1) {
                    dataSet[1].showRedPoint = false
                    adapter?.notifyDataSetChanged()
                }
            } else if (event.type.equals("1")) {//隐藏能耗总会小红点
                EventBus.getDefault().post(ET_RedPoint(ET_RedPoint.TASKID_RED_POINT_HIDE_MAIN, "1"))
                if (!dataSet.isEmpty()) {
                    dataSet[0].showRedPoint = false
                    adapter?.notifyDataSetChanged()
                }
            }
        }

    }

    var adapter: AD_List? = null
    override fun afterViews() {
        isNeedEventBus = true
        val c = Calendar.getInstance()
        val initDate = c.get(Calendar.YEAR).toString() + "-" + (c.get(Calendar.MONTH) + 1).toString() + "-" + c.get(Calendar.DAY_OF_MONTH)

        /**能耗汇总*/
        var item = BN_Data()
        item.date = initDate
        item.title = "能耗汇总"
        item.msg = "点击查看" + initDate + "的能耗汇总"
        dataSet.add(item)
        /**故障报警*/
        var item2 = BN_Data()
        item2.date = initDate
        item2.title = "故障报警"
        item2.msg = "点击查看故障报警历史"
        item2.icon = R.drawable.icon_guz
        dataSet.add(item2)
        /**系统消息*/
        var item3 = BN_Data()
        item3.date = initDate
        item3.title = "系统消息"
        item3.msg = "点击查看系统消息"
        item3.icon = R.drawable.icon_sys_msg
        dataSet.add(item3)
        /**能耗对比*/
        var item4 = BN_Data()
        item4.date = initDate
        item4.title = "能耗对比"
        item4.msg = "点击查看能耗对比"
        item4.icon = R.drawable.icon_nhdb
        dataSet.add(item4)


        adapter = AD_List(activity, dataSet)
        lv_list.adapter = adapter
        refreshLayout.setOnRefreshListener { layout ->
            DebugLog.d("++++++++++++++++++++++++++refresh yeqinfu")
            layout.finishRefresh(1000)
        }
        lv_list.setOnItemClickListener { _, _, i, _ ->
            when (i) {
                0 -> {
                    startAC(FG_EnergyList::class.java.name)
                    /**隐藏小红点*/
                    /**隐藏小红点*/
                    /**隐藏小红点*/
                    EventBus.getDefault().post(ET_RedPoint(ET_RedPoint.TASKID_RED_POINT_HIDE, "1"))
                }
                1 -> {
                    startAC(FG_FaultHistory::class.java.name)
                    /**隐藏小红点*/
                    /**隐藏小红点*/
                    /**隐藏小红点*/
                    EventBus.getDefault().post(ET_RedPoint(ET_RedPoint.TASKID_RED_POINT_HIDE, "2"))
                }
                2 -> {//系统消息
                    startAC(FG_SystemNewList::class.java.name)
                }
                3 -> {//能耗对比
                    startAC(FG_EnergyComparison::class.java.name)
                }
            }
        }
    }

    class BN_Data {
        var title: String? = null
        var date: String? = null
        var msg: String? = null
        var icon: Int? = null
        var showRedPoint: Boolean? = null
    }

    class AD_List(mContext: Activity?, dataSet: ArrayList<BN_Data>) : BaseAdapter() {
        private var mContext: Activity? = null
        var dataSet = ArrayList<BN_Data>()

        init {
            this.mContext = mContext
            this.dataSet = dataSet
        }

        override fun getView(pos: Int, p1: View?, p2: ViewGroup?): View? {
            var view = mContext?.layoutInflater?.inflate(R.layout.item_news, null)
            view?.let {
                var tv_title = it.find<TextView>(R.id.tv_title)
                tv_title.text = dataSet[pos].title
                var tv_msg = it.find<TextView>(R.id.tv_msg)
                tv_msg.text = dataSet[pos].msg
                var tv_date = it.find<TextView>(R.id.tv_date)
                tv_date.text = dataSet[pos].date
                var iv_icon = it.find<ImageView>(R.id.iv_icon)
                if (dataSet[pos].icon == null) {
                    iv_icon.setImageResource(R.drawable.ic_nenghaohz)
                } else {
                    dataSet[pos].icon?.let { it1 -> iv_icon.setImageResource(it1) }
                }
                var iv_red_point = it.find<ImageView>(R.id.iv_red_point)
                if (dataSet[pos].showRedPoint == true) {
                    iv_red_point.visibility = View.VISIBLE
                } else {
                    iv_red_point.visibility = View.GONE
                }

            }

            return view
        }

        override fun getItem(p0: Int): BN_Data {
            return dataSet.get(p0)
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int = dataSet.size

    }

}