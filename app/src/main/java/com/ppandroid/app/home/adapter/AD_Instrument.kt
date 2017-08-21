package com.ppandroid.app.home.adapter

import android.app.Activity
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import com.ppandroid.app.R
import com.ppandroid.app.bean.overview.BN_OverView
import org.jetbrains.anko.find

/**
 * Created by yeqinfu on 2017/8/11.
 */
class AD_Instrument(ac: Activity, list: List<BN_OverView.MessageBean.InstrumentInfomationListBean>): PagerAdapter(){
    /**横条图viewpager*/
    var arrays_title = arrayOf(
            "今日",
            "本月",
            "本年"

    )
    var views = ArrayList<View>()// 将要分页显示的View装入数组中
    var list: List<BN_OverView.MessageBean.InstrumentInfomationListBean>?=null
    var ac: Activity?=null
    init {
        this.ac=ac
        this.list=list
        /**viewpager*/
        val lf = ac.layoutInflater
        for (item in list){
            var view1 = lf.inflate(R.layout.item_zhongdian, null)
            views.add(view1)
        }
    }

    override fun isViewFromObject(arg0: View, arg1: Any): Boolean {

        return arg0 === arg1
    }

    override fun getCount(): Int {
        return views.size
    }

    override fun destroyItem(container: ViewGroup, position: Int,
                             `object`: Any) {
        container.removeView(views.get(position))

    }

    override fun getItemPosition(`object`: Any?): Int {

        return super.getItemPosition(`object`)
    }

    override fun getPageTitle(position: Int): CharSequence {
        return arrays_title[position]
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        container.addView(views[position])

        var lv_list=views[position].find<ListView>(R.id.lv_list)
        ac?.let {
            var adapter=AD_List(it, list?.get(position)?.instrumentMapList)
            lv_list.adapter=adapter
        }
        return views[position]
    }
    class Holder{
        var tv_key:TextView?=null
        var tv_value:TextView?=null

    }
    class AD_List(ac:Activity,instrumentMapList: List<BN_OverView.MessageBean.InstrumentInfomationListBean.InstrumentMapListBean>?) : BaseAdapter() {
        private var ac:Activity
        internal var instrumentMapList: List<BN_OverView.MessageBean.InstrumentInfomationListBean.InstrumentMapListBean>?=null
        init {
            this.ac=ac
            this.instrumentMapList=instrumentMapList
        }
        override fun getView(p0: Int, v: View?, p2: ViewGroup?): View? {
            var layout:View?=null
            var holder:Holder?=null
            if (v!=null){
                layout=v
                holder= v.tag as Holder?
            }else{
                layout=ac.layoutInflater.inflate(R.layout.item_zhongdian_listview_item,null)
                holder= Holder()
                holder.tv_key=layout.find(R.id.tv_key)
                holder.tv_value=layout.find(R.id.tv_value)
                layout.tag=holder
            }
            holder?.let {
                it.tv_key?.text= instrumentMapList?.get(p0)?.key?:""
                it.tv_value?.text= instrumentMapList?.get(p0)?.value?:""

            }
            return layout
        }

        override fun getItem(p0: Int): Any? {
            return instrumentMapList?.get(p0)
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
            return instrumentMapList?.size?:0
        }

    }

}