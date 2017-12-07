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
import com.ppandroid.app.home.news.FG_EnergyList
import com.ppandroid.app.home.news.FG_FaultHistory
import com.ppandroid.app.utils.DebugLog
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_news.*
import org.jetbrains.anko.find
import java.util.*

/**
 * Created by yeqinfu on 2017/8/3.
 */
class FG_News :FG_Base(){
    override fun fgRes():Int= R.layout.fg_news

    var dataSet=ArrayList<BN_Data>()



    override fun afterViews() {
        val c = Calendar.getInstance()
        val initDate = c.get(Calendar.YEAR).toString() + "-" + (c.get(Calendar.MONTH) + 1).toString() + "-" + c.get(Calendar.DAY_OF_MONTH)

        /**能耗汇总*/
        var item=BN_Data()
        item.date=initDate
        item.title="能耗汇总"
        item.msg="点击查看"+initDate+"的能耗汇总"
        dataSet.add(item)
        /**故障报警*/
        var item2=BN_Data()
        item2.date=initDate
        item2.title="故障报警"
        item2.msg="点击查看故障报警历史"
        item2.icon=R.drawable.icon_guz
        dataSet.add(item2)

        lv_list.adapter=AD_List(activity,dataSet)
        refreshLayout.setOnRefreshListener { layout->
            DebugLog.d("++++++++++++++++++++++++++refresh yeqinfu")
            layout.finishRefresh(1000)
        }
        lv_list.setOnItemClickListener { _, _, i, _ ->
            if (i==0){
                startAC(FG_EnergyList::class.java.name)
            }else if (i==1){
                startAC(FG_FaultHistory::class.java.name)
            }
        }
    }
    class BN_Data{
        var title:String?=null
        var date:String?=null
        var msg:String?=null
        var icon:Int?=null
    }
    class  AD_List(mContext: Activity?, dataSet: ArrayList<BN_Data>): BaseAdapter() {
        private var mContext: Activity?=null
        var dataSet=ArrayList<BN_Data>()
        init {
            this.mContext = mContext
            this.dataSet = dataSet
        }

        override fun getView(pos: Int, p1: View?, p2: ViewGroup?): View? {
           var view=mContext?.layoutInflater?.inflate(R.layout.item_news,null)
            view?.let {
                var tv_title=it.find<TextView>(R.id.tv_title)
                tv_title.text=dataSet[pos].title
                var tv_msg=it.find<TextView>(R.id.tv_msg)
                tv_msg.text=dataSet[pos].msg
                var tv_date=it.find<TextView>(R.id.tv_date)
                tv_date.text=dataSet[pos].date
                var iv_icon=it.find<ImageView>(R.id.iv_icon)
                if (dataSet[pos].icon==null){
                    iv_icon.setImageResource(R.drawable.ic_nenghaohz)
                }else{
                    dataSet[pos].icon?.let { it1 -> iv_icon.setImageResource(it1) }
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

        override fun getCount(): Int=dataSet.size

    }

}