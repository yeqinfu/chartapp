package com.ppandroid.app.home.mine

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.ppandroid.app.R
import com.ppandroid.app.base.NetWorkErrorView
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.mine.BN_SystemSettingPage01
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_system_setting_page.*
import kotlinx.android.synthetic.main.layout_network_error.*
import org.jetbrains.anko.find

/**
 * Created by yeqinfu on 2017/8/22.
 */
class FG_SystemSettingPage : FG_Base() {
    override fun fgRes(): Int = R.layout.fg_system_setting_page

    override fun afterViews() {
        loadContent()
        network_error.setListener {
            loadContent()
        }
    }

    private fun loadContent() {
        var url = "user/sysSet/instrument/search.json"
        Http.get(activity, url, BN_SystemSettingPage01::class.java, object : MyCallBack<BN_SystemSettingPage01> {
            override fun onResponse(response: BN_SystemSettingPage01?) {
                response?.let {
                    if (response.message.isEmpty()) {
                        network_error.setViewType(NetWorkErrorView.NOT_DATA)
                    } else {
                        network_error.setViewType(NetWorkErrorView.NORMAL_VIEW)
                        var adapter=AD_List(activity,it.message)
                        lv_list.adapter=adapter
                    }

                }
            }

            override fun onError(error: ErrorBody?) {
                network_error.setViewType(NetWorkErrorView.NETWORK_ERROR)
                toast(error)
            }

        })
    }

    class Holder {
        var tv_name: TextView? = null
        var tv_level: TextView? = null
        var tv_number: TextView? = null

    }

    class AD_List(ac: Activity, message: List<BN_SystemSettingPage01.MessageBean>) : BaseAdapter() {
        private var ac: Activity
        private var message: List<BN_SystemSettingPage01.MessageBean>? = null

        init {
            this.ac = ac
            this.message = message
        }

        override fun getView(pos: Int, convertView: View?, p2: ViewGroup?): View? {
            var layout: View? = null
            var holder: Holder? = null
            if (convertView != null) {
                layout = convertView
                holder = layout.tag as Holder?
            } else {
                layout = ac.layoutInflater.inflate(R.layout.item_fg_sys_setting_page, null)
                holder = Holder()
                holder.tv_number = layout.find(R.id.tv_number)
                holder.tv_level = layout.find(R.id.tv_level)
                holder.tv_name = layout.find(R.id.tv_name)
                layout.tag = holder
            }
            holder?.tv_name?.text= message?.get(pos)?.name?:""
            holder?.tv_level?.text=( message?.get(pos)?.level?:1).toString()+"级"
            holder?.tv_number?.text= message?.get(pos)?.code?:""
            return layout
        }

        override fun getItem(p0: Int): BN_SystemSettingPage01.MessageBean? {
            return message?.get(p0)
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
            return message?.size ?: 0

        }

    }

}