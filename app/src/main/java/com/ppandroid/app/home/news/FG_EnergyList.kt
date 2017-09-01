package com.ppandroid.app.home.news

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.ppandroid.app.R
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.news.BN_EnergyList
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
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
    private var content=ArrayList<BN_EnergyList.MessageBean.ContentBean>()
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
                    if (!it.message.content.isEmpty()){
                        content.addAll(it.message.content)
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
        var tv_date:TextView?=null
        var tv_value:TextView?=null
    }

    class AD_List(ac:Activity,list:List<BN_EnergyList.MessageBean.ContentBean>) : BaseAdapter() {
        private var ac=ac
        private var list=list
        override fun getView(pos: Int, convertView: View?, p2: ViewGroup?): View? {
            var layout:View?=null
            var holder:Holder?=null
            if (convertView!=null){
                layout=convertView
                holder= layout.tag as Holder?

            }else{
                layout=ac.layoutInflater.inflate(R.layout.item_energy_list,null)
                holder= Holder()
                holder.tv_date=layout.find(R.id.tv_date)
                holder.tv_value=layout.find(R.id.tv_value)
            }
            holder?.let {
                it.tv_date?.text=list[pos].createTime
                it.tv_value?.text=list[pos].totalKwh+"kwh"
            }

            return layout

        }

        override fun getItem(p0: Int): Any=list[p0]

        override fun getItemId(p0: Int): Long=p0.toLong()

        override fun getCount(): Int =list.size

    }


}