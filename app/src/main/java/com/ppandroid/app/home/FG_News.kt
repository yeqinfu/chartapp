package com.ppandroid.app.home

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.ppandroid.app.R
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_news.*
import org.jetbrains.anko.find

/**
 * Created by yeqinfu on 2017/8/3.
 */
class FG_News :FG_Base(){
    override fun fgRes():Int= R.layout.fg_news

    var dataSet=ArrayList<BN_Data>()


    override fun afterViews() {
        var item=BN_Data()
        item.date="09.06"
        item.title="能耗汇总"
        item.msg="点击查看2017-08-08的能耗汇总"
        var item1=BN_Data()
        item1.date="2017-07-25"
        item1.title="系统消息"
        item1.msg="请先去系统设置设置数据。如果您有遇到..."
        dataSet.add(item)
        dataSet.add(item1)
        lv_list.adapter=AD_List(activity,dataSet)
    }
    class BN_Data{
        var title:String?=null
        var date:String?=null
        var msg:String?=null
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