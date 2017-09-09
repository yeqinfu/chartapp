package com.ppandroid.app.home.news

import android.app.Activity
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.ppandroid.app.R
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.news.BN_EnergyList
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.app.utils.DebugLog
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_energy_list.*
import kotlinx.android.synthetic.main.layout_head_view.*
import org.jetbrains.anko.find

/**
 * Created by yeqinfu on 2017/9/1.
 */
class FG_EnergyList :FG_Base(){
    override fun fgRes(): Int= R.layout.fg_energy_list

    override fun afterViews() {
        head_view.init(activity)
        head_view.setCenterTitle("能耗汇总")
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
    private var content=ArrayList<BN_EnergyList.MessageBean.EnergyConsumptionStatisticsDtoListBean>()
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
        var url="user/energy/statistics/get.json?pageNumber=$pageNumber"
        Http.get(activity,url, BN_EnergyList::class.java,object :MyCallBack<BN_EnergyList>{
            override fun onResponse(response: BN_EnergyList?) {
                refresh_layout.finishRefresh()
                refresh_layout.finishLoadmore()
                response?.let {
                    if (!it.message.energyConsumptionStatisticsDtoList.isEmpty()){
                        content.addAll(it.message.energyConsumptionStatisticsDtoList)
                        adapter?.notifyDataSetChanged()
                        pageNumber++
                        totalPage=it.message.totalPages
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
        var tv_msg_date:TextView?=null
        var tv_title:TextView?=null
        var tv_date:TextView?=null
        var tv_key:TextView?=null
        var tv_value:TextView?=null
        var iv_type:ImageView?=null
    }

    class AD_List(ac:Activity,list:ArrayList<BN_EnergyList.MessageBean.EnergyConsumptionStatisticsDtoListBean>) : BaseAdapter() {
        private var ac=ac
        private var list=list
        var colors = arrayOf(
                "#5EC1FF",
                "#45E069",
                "#FFCF3B",
                "#FF6969"
        )
        override fun getView(pos: Int, convertView: View?, p2: ViewGroup?): View? {
            DebugLog.d("======="+pos)
            var layout:View?=null
            var holder:Holder?=null
            if (convertView!=null){
                layout=convertView
                holder= layout.tag as Holder?

            }else{
                layout=ac.layoutInflater.inflate(R.layout.item_energy_list,null)
                holder= Holder()
                holder.tv_title=layout.find(R.id.tv_title)
                holder.tv_msg_date=layout.find(R.id.tv_msg_date)
                holder.tv_date=layout.find(R.id.tv_date)
                holder.tv_key=layout.find(R.id.tv_key)
                holder.tv_value=layout.find(R.id.tv_value)
                holder.iv_type=layout.find(R.id.iv_type)
                layout.tag = holder
            }
            holder?.let {
                it.tv_msg_date?.text=list[pos].recordDate
                it.tv_date?.text=list[pos].datePeriod+"用电情况，请知晓"
                it.tv_value?.text=list[pos].totalKwh+"kwh"
                when(list[pos].type){
                    1->{
                        it.iv_type?.setImageResource(R.drawable.ic_day)
                        it.tv_title?.setTextColor(Color.parseColor(colors[0]))
                        it.tv_key?.text="今日用电："
                    }
                    2->{
                        it.iv_type?.setImageResource(R.drawable.ic_week)
                        it.tv_title?.setTextColor(Color.parseColor(colors[1]))
                        it.tv_key?.text="本周用电："
                    }
                    3->{
                        it.iv_type?.setImageResource(R.drawable.ic_month)
                        it.tv_title?.setTextColor(Color.parseColor(colors[2]))
                        it.tv_key?.text="本月用电："
                    }
                    4->{
                        it.iv_type?.setImageResource(R.drawable.ic_year)
                        it.tv_title?.setTextColor(Color.parseColor(colors[3]))
                        it.tv_key?.text="本年用电："
                    }

                }


            }

            return layout

        }

        override fun getItem(p0: Int): Any=list[p0]

        override fun getItemId(p0: Int): Long=p0.toLong()

        override fun getCount(): Int =list.size

    }


}