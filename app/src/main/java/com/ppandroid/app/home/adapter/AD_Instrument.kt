package com.ppandroid.app.home.adapter

import android.app.Activity
import android.graphics.Color
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import com.ppandroid.app.R
import com.ppandroid.app.bean.overview.BN_OverView
import com.ppandroid.app.utils.Utils_Common
import com.ppandroid.app.widget.HorizontalPercentageView
import org.jetbrains.anko.find
import java.util.regex.Pattern

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
        var v_hp: HorizontalPercentageView? = null

    }
    class AD_List(ac:Activity,instrumentMapList: List<BN_OverView.MessageBean.InstrumentInfomationListBean.InstrumentMapListBean>?) : BaseAdapter() {

        var colors = arrayOf(
                "#FA6D6F",
                "#FE8D25",
                "#FFB338",
                "#6CC37E",
                "#2ED9A4"
        )
        private var max: Double = 0.0

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
                holder.v_hp = layout.find(R.id.v_hp)
                layout.tag=holder
            }
            holder?.let {
                it.tv_key?.text= instrumentMapList?.get(p0)?.key?:""
                it.tv_value?.text= instrumentMapList?.get(p0)?.value?:""

                val regEx = "kwh$|万kwh$"
                val p = Pattern.compile(regEx)
                val m = p.matcher(instrumentMapList?.get(p0)?.value ?: "")
                var value = m.replaceAll("")
                if (p0 == 0) {
                    max = Utils_Common.paraseDouble(value) * 1.2
                }
                if (max == 0.0){
                    max=1.0
                }


                holder?.v_hp?.percentage = (Utils_Common.paraseDouble(value) / max).toFloat()
                holder?.v_hp?.colorId = Color.parseColor(colors[p0%colors.size])
                holder?.v_hp?.startAnim()

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