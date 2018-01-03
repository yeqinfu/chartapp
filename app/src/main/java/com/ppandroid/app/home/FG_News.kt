/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home

import android.app.Activity
import android.os.Bundle
import android.text.TextUtils
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
            //转发通知给主tabs显示小红点
            EventBus.getDefault().post(ET_RedPoint(ET_RedPoint.TASKID_RED_POINT_SHOW_MAIN, event.type))
            for (item in dataSet){
                if (item.msgType.toString()==(event.type)){
                    if (!TextUtils.isEmpty(event.msg)){//如果消息不为空，相应显示在选项item中
                        item.msg=event.msg
                    }
                    item.showRedPoint=true
                    DebugLog.d("============================================")
                }
            }
            adapter?.notifyDataSetChanged()

        } else if (event.taskId === ET_RedPoint.TASKID_RED_POINT_HIDE) {//隐藏小红点
            //转发通知给主tab取消小红点
            EventBus.getDefault().post(ET_RedPoint(ET_RedPoint.TASKID_RED_POINT_HIDE_MAIN, event.type))
            for (item in dataSet){
                if (item.msgType.toString() == event.type){
                    item.showRedPoint=false
                }
            }
            adapter?.notifyDataSetChanged()
        }

    }

    var adapter: AD_List? = null
    override fun afterViews() {
        isNeedEventBus = true
        val c = Calendar.getInstance()
        val initDate = c.get(Calendar.YEAR).toString() + "-" + (c.get(Calendar.MONTH) + 1).toString() + "-" + c.get(Calendar.DAY_OF_MONTH)

        /**能耗汇总*/
        var item = BN_Data()
        item.msgType=BN_Data.ENERGY_COLLECT
        item.date = initDate
        item.title = "能耗汇总"
        item.msg = "点击查看" + initDate + "的能耗汇总"
        dataSet.add(item)

        /**水能耗总汇*/
        var item5 = BN_Data()
        item5.msgType=BN_Data.WATER_COLLECT
        item5.date = initDate
        item5.title = "用水量汇总"
        item5.msg =  "点击查看" + initDate + "的用水量汇总"
        item5.icon = R.drawable.icon_yongsl
        dataSet.add(item5)

        /**故障报警*/
        var item2 = BN_Data()
        item2.msgType=BN_Data.FAILUE_WARNING
        item2.date = initDate
        item2.title = "故障报警"
        item2.msg = "点击查看故障报警历史"
        item2.icon = R.drawable.icon_guz
        dataSet.add(item2)
        /**系统消息*/
        var item3 = BN_Data()
        item3.msgType=BN_Data.SYSTEM
        item3.date = initDate
        item3.title = "系统消息"
        item3.msg = "点击查看系统消息"
        item3.icon = R.drawable.icon_sys_msg
        dataSet.add(item3)
        /**能耗对比*/
        var item4 = BN_Data()
        item4.msgType=BN_Data.POWER_COMPARISON
        item4.date = initDate
        item4.title = "能耗对比"
        item4.msg = "点击查看能耗对比"
        item4.icon = R.drawable.icon_nhdb
        dataSet.add(item4)


        /**用水量对比*/
        var item6 = BN_Data()
        item6.msgType=BN_Data.WATER_COMPARISON
        item6.date = initDate
        item6.title = "用水量对比"
        item6.msg =  "点击查看用水量对比"
        item6.icon = R.drawable.icon_ysldb
        dataSet.add(item6)


        adapter = AD_List(activity, dataSet)
        lv_list.adapter = adapter
        refreshLayout.setOnRefreshListener { layout ->
            layout.finishRefresh(1000)
        }
        lv_list.setOnItemClickListener { _, _, i, _ ->
            //消息转发给主tab，要求取消小红点
            EventBus.getDefault().post(ET_RedPoint(ET_RedPoint.TASKID_RED_POINT_HIDE, dataSet[i].msgType.toString()))
            when (dataSet[i].msgType) {
                BN_Data.ENERGY_COLLECT -> {//能耗汇总
                    startAC(FG_EnergyList::class.java.name)
                }
                BN_Data.FAILUE_WARNING -> {//故障报警
                    startAC(FG_FaultHistory::class.java.name)
                }
                BN_Data.SYSTEM -> {//系统消息
                    startAC(FG_SystemNewList::class.java.name)
                }
                BN_Data.POWER_COMPARISON -> {//能耗对比
                    startAC(FG_EnergyComparison::class.java.name)
                }
                BN_Data.WATER_COLLECT->{//水能耗总汇
                    var b=createBundle()
                    startAC(FG_EnergyList::class.java.name,b)
                }
                BN_Data.WATER_COMPARISON->{//水能耗对比
                    startAC(FG_EnergyComparison::class.java.name,createBundle())
                }
            }
        }
    }
    companion object {
         fun createBundle(): Bundle {
            var energyClassificationId="2"
            var b=Bundle()
            b.putString("energyClassificationId",energyClassificationId)
            return b
        }
    }


    open class BN_Data {
        companion object {//消息列表类型常量
            val ENERGY_COLLECT=1//电能耗汇总
            val FAILUE_WARNING=2//故障报警
            val POWER_COMPARISON=3//电能耗对比
            val SYSTEM=4//系统消息
            val WATER_COLLECT=5//水能耗汇总
            val WATER_COMPARISON=6//水能耗对比
        }
        var msgType=ENERGY_COLLECT
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