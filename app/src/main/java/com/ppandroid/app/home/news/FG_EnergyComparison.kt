/*
 * Created by yeqinfu on 17-12-22 下午2:24
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home.news

import android.app.Activity
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ppandroid.app.R
import com.ppandroid.app.base.FG_CommonList
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.news.BN_EnergyComparison
import com.ppandroid.app.home.FG_News
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import kotlinx.android.synthetic.main.fg_common_list.*
import org.jetbrains.anko.find

/**
 * Created by yeqinfu on 2017/12/22.
 */
class FG_EnergyComparison:FG_CommonList<BN_EnergyComparison.MessageBean.ResultBean>(){
    var energyClassificationId="1"
    override fun afterViews() {
        arguments?.let {
            energyClassificationId=it.getString("energyClassificationId","1")
        }
        super.afterViews()
        lv_list.setOnItemClickListener { p0, p1, pos, p3 ->
            var intent= Intent()
            intent.setClass(activity,AC_RecentComparison::class.java)
            intent.putExtra("default",content[pos].alert)
            intent.putExtra("energyClassificationId",energyClassificationId)
            startActivity(intent)
        }
    }
    override fun getAdapter(activity: Activity, content: ArrayList<BN_EnergyComparison.MessageBean.ResultBean>): AD_CommonList<BN_EnergyComparison.MessageBean.ResultBean>? {
        return AD_EnergyComparison(activity,content)
    }
    override fun loadContent() {
        var url=if (energyClassificationId=="1"){
            "/user/message/get.json?type="+FG_News.BN_Data.POWER_COMPARISON+"&pageNumber=$pageNumber"
        }else{
            "/user/message/get.json?type="+FG_News.BN_Data.WATER_COMPARISON+"&pageNumber=$pageNumber"
        }
        Http.get(activity,url,BN_EnergyComparison::class.java,object :MyCallBack<BN_EnergyComparison>{
            override fun onResponse(response: BN_EnergyComparison?) {
                refresh_layout.finishRefresh()
                refresh_layout.finishLoadmore()
                response?.let {

                    if (it.message?.result?.isEmpty() == false){
                        content.addAll(it.message.result)
                        adapter?.notifyDataSetChanged()
                        pageNumber++
                        totalPage=it.message.totalPages
                    }
                }
            }

            override fun onError(error: ErrorBody?) {
                errorToast(error)
            }

        })
    }
    override fun getTitle(): String{
        return if (energyClassificationId.equals("1")){
            "能耗对比"
        }else{
            "水能耗对比"
        }
    }

    class Holder{
        var tv_msg_date: TextView?=null
        var tv_title: TextView?=null
        var tv_date: TextView?=null
        var tv_content: TextView?=null
    }
    class AD_EnergyComparison(ac: Activity, list:ArrayList<BN_EnergyComparison.MessageBean.ResultBean>):AD_CommonList<BN_EnergyComparison.MessageBean.ResultBean>(ac,list){
        override fun getView(pos: Int, convertView: View?, p2: ViewGroup?): View? {
            var view:View?=null
            var holder:Holder?=null
            if (convertView!=null){
                view=convertView
                holder= view.tag as Holder?
            }else{
                view=ac.layoutInflater.inflate(R.layout.item_energy_comparison,null)
                holder= Holder()
                view.tag = holder
                holder.tv_msg_date=view.find(R.id.tv_msg_date)
                holder.tv_title=view.find(R.id.tv_title)
                holder.tv_date=view.find(R.id.tv_date)
                holder.tv_content=view.find(R.id.tv_content)
            }
            holder?.let {
                it.tv_msg_date?.text=list[pos].createTime.toString()
                it.tv_title?.text=list[pos].title
                it.tv_content?.text="近三日实时能耗对比数据，最高值，最低值。多维度对比企业能耗情况"
                it.tv_date?.text=list[pos].alert
            }
            return view;

        }
        override fun getItem(p0: Int): Any=list[p0]
    }

}