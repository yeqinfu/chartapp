package com.ppandroid.app.home.mine.instrument

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.ppandroid.app.R
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.mine.instrument.BN_InstrumentList
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_system_setting_page.*
import kotlinx.android.synthetic.main.layout_head_view.*
import org.jetbrains.anko.find

/**
 * Created by yeqinfu on 2017/8/23.
 */
class FG_InstrumentList:FG_Base(){
    override fun fgRes(): Int= R.layout.fg_instrument_list

    override fun afterViews() {
        head_view.init(activity)
        head_view.setCenterTitle("仪表检测")
        head_view.setIvRight(R.drawable.ic_head_inst_list){
            toast("hahahhah")
        }
        loadContent()
    }
    private fun loadContent(){
        var url="user/instrument/search.json"
        Http.get(activity,url,BN_InstrumentList::class.java,object :MyCallBack<BN_InstrumentList>{
            override fun onResponse(response: BN_InstrumentList?) {
                response?.let {
                    var adapter=AD_List(activity,it.message)
                    lv_list.adapter=adapter

                }
            }

            override fun onError(error: ErrorBody?) {
                toast(error)
            }

        })
    }

    class Holder {
        var tv_name: TextView? = null
        var tv_level: TextView? = null
        var tv_number: TextView? = null
        var tv_status: TextView? = null
    }



    class AD_List(ac: Activity, list: List<BN_InstrumentList.MessageBean>) : BaseAdapter() {

        /**仪表状态枚举*/
        /**未启用*/
        val NO_USE=-1
        /**正常*/
        val NORMAL=0
        /**通讯故障*/
        val COMMUNICATION_CAULT=1


        private var ac: Activity = ac
        private var list: List<BN_InstrumentList.MessageBean> = list
        override fun getView(pos: Int, convertView: View?, p2: ViewGroup?): View? {
            var layout: View? = null
            var holder: Holder? = null
            if (convertView != null) {
                layout = convertView
                holder = layout.tag as Holder?
            } else {
                layout = ac.layoutInflater.inflate(R.layout.item_instrument_list, null)
                holder = Holder()
                holder.tv_name = layout.find(R.id.tv_name)
                holder.tv_level = layout.find(R.id.tv_level)
                holder.tv_number = layout.find(R.id.tv_number)
                holder.tv_status = layout.find(R.id.tv_status)
                layout.tag = holder
            }

            holder?.let {
                it.tv_name?.text= list[pos].name
                it.tv_level?.text= list[pos].level.toString()+"级"
                it.tv_number?.text= list[pos].code
                when {
                    list[pos].status==NO_USE -> it.tv_status?.text= "未启用"
                    list[pos].status==NORMAL -> it.tv_status?.text= "正常"
                    list[pos].status==COMMUNICATION_CAULT -> it.tv_status?.text= "通讯故障"
                }


            }

            return layout

        }

        override fun getItem(p0: Int): BN_InstrumentList.MessageBean? {
            return list?.get(p0)
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
            return list?.size
        }

    }

}