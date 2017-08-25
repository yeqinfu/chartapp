package com.ppandroid.app.home.mine.systemsetting

import android.app.Activity
import android.support.v7.app.AlertDialog
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.ppandroid.app.R
import com.ppandroid.app.base.NetWorkErrorView
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.mine.systemsetting.BN_DeviceCatePage
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.im.base.FG_Base
import com.ppandroid.im.bean.BaseBody
import kotlinx.android.synthetic.main.fg_system_setting_page.*
import kotlinx.android.synthetic.main.layout_network_error.*
import org.jetbrains.anko.find

/**
 * Created by yeqinfu on 2017/8/22.
 */
class FG_DeviceCatePage : FG_Base() {
    override fun fgRes(): Int = R.layout.fg_system_setting_page

    override fun afterViews() {
        loadContent()
        network_error.setListener { loadContent() }

        lv_list.setOnItemLongClickListener { adapterView, view, i, l ->
            message?.let {
                showConfirmDialog(it[i].id)
            }
            false
        }
    }

    private fun showConfirmDialog(id: Int) {
        val builder = AlertDialog.Builder(activity).setMessage("确定删除吗？").setCancelable(false)
        builder.setPositiveButton("确定") { _, _ ->
            deleteInstrument(id)
        }
        builder.setNegativeButton("取消") { _, _ ->
            builder.create().dismiss()
        }
        builder.create().show()

    }

    private fun deleteInstrument(id: Int) {
        var url = "user/sysSet/deviceCate/delete.json?id=" + id
        Http.get(activity, url, BaseBody::class.java, object : MyCallBack<BaseBody> {
            override fun onResponse(response: BaseBody?) {
                response?.let {
                    if (it.isSuccess) {
                        toast("删除成功")
                        loadContent()
                    }
                }
            }

            override fun onError(error: ErrorBody?) {
                toast(error)
            }

        })
    }

    private fun loadContent() {
        var url = "user/sysSet/deviceCate/search.json"
        Http.get(activity, url, BN_DeviceCatePage::class.java, object : MyCallBack<BN_DeviceCatePage> {
            override fun onResponse(response: BN_DeviceCatePage?) {
                response?.let {
                    if (response.message.isEmpty()) {
                        network_error?.setViewType(NetWorkErrorView.NOT_DATA)
                    } else {
                        network_error?.setViewType(NetWorkErrorView.NORMAL_VIEW)
                        message = it.message
                        var adapter = AD_List(activity, it.message)
                        lv_list.adapter = adapter
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

    private var message: List<BN_DeviceCatePage.MessageBean>? = null

    class AD_List(ac: Activity, message: List<BN_DeviceCatePage.MessageBean>) : BaseAdapter() {
        private var ac: Activity
        private var message: List<BN_DeviceCatePage.MessageBean>? = null

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
                layout = ac.layoutInflater.inflate(R.layout.item_device_cate_page, null)
                holder = Holder()
                holder.tv_number = layout.find(R.id.tv_number)
                holder.tv_level = layout.find(R.id.tv_level)
                holder.tv_name = layout.find(R.id.tv_name)
                layout.tag = holder
            }
            holder?.tv_name?.text = message?.get(pos)?.name ?: ""
            holder?.tv_level?.text = (message?.get(pos)?.level ?: 1).toString() + "级"
            if (message?.get(pos)?.parentId==-1){
                holder?.tv_number?.text = "已经是最高级"
            }else{
                holder?.tv_number?.text = message?.get(pos)?.parentId.toString()
            }

            return layout
        }

        override fun getItem(p0: Int): BN_DeviceCatePage.MessageBean? {
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