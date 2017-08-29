package com.ppandroid.app.home.mine.systemsetting

import android.app.Activity
import android.support.v7.app.AlertDialog
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.ppandroid.app.R
import com.ppandroid.app.base.NetWorkErrorView
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.mine.systemsetting.BN_EnergyChargingPage
import com.ppandroid.app.bean.mine.systemsetting.ET_SyStemSetting
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.app.widget.CustomDialog
import com.ppandroid.im.base.FG_Base
import com.ppandroid.im.bean.BaseBody
import kotlinx.android.synthetic.main.fg_system_setting_page.*
import kotlinx.android.synthetic.main.layout_network_error.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.find

/**
 * Created by yeqinfu on 2017/8/22.
 * 区域列表
 */
class FG_EnergyChargingPage : FG_Base() {



    override fun fgRes(): Int = R.layout.fg_system_setting_page

    override fun afterViews() {
        isNeedEventBus=true
        loadContent()
        network_error.setListener { loadContent() }

        lv_list.setOnItemLongClickListener { adapterView, view, i, l ->
            message?.let {
                operatorId=it[i].energyChargingEntity.id.toString()
                code=it[i].energyChargingEntity.code.toString()
                energyClassificationId=it[i].energyChargingEntity.classificationId.toString()
                showChooseDialog()
            }
            false
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: ET_SyStemSetting) {
        if (event.taskId == ET_SyStemSetting.TASKID_REFRESH_ENERGY_CHARGING) {
            loadContent()
        }
    }


    private var operatorId=""
    private var energyClassificationId=""
    private var code=""
    private var dialog: CustomDialog? = null
    private fun showChooseDialog() {
        val mView = LayoutInflater.from(activity).inflate(R.layout.dialog_choose_modified_or_del, null)
        dialog = CustomDialog(activity, R.style.family_dialog_theme, mView, Gravity.CENTER, 4)
        mView.findViewById(R.id.rl_detail).setOnClickListener(dialogListener)
        mView.findViewById(R.id.rl_del).setOnClickListener(dialogListener)
        dialog?.show()
    }

    private val dialogListener = View.OnClickListener { v ->
        when (v.id) {
            R.id.rl_detail -> {
                var b=FG_AddEnergyCharging.createBundle(operatorId,energyClassificationId,code)
                startAC(FG_AddEnergyCharging::class.java.name,b)
                dialog?.dismiss()

            }
            R.id.rl_del -> {
                showConfirmDialog()
                dialog?.dismiss()
            }

        }
    }


    private fun showConfirmDialog() {
        val builder = AlertDialog.Builder(activity).setMessage("确定删除吗？").setCancelable(false)
        builder.setPositiveButton("确定") { _, _ ->
            deleteInstrument(operatorId)
        }
        builder.setNegativeButton("取消") { _, _ ->
            builder.create().dismiss()
        }
        builder.create().show()

    }

    private fun deleteInstrument(id: String) {
        var url = "user/sysSet/energyCharging/delete.json?id=" + id
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
        var url = "user/sysSet/energyCharging/search.json"
        Http.get(activity, url, BN_EnergyChargingPage::class.java, object : MyCallBack<BN_EnergyChargingPage> {
            override fun onResponse(response: BN_EnergyChargingPage?) {
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

    private var message: List<BN_EnergyChargingPage.MessageBean>? = null

    class AD_List(ac: Activity, message: List<BN_EnergyChargingPage.MessageBean>) : BaseAdapter() {
        private var ac: Activity
        private var message: List<BN_EnergyChargingPage.MessageBean>? = null

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
                layout = ac.layoutInflater.inflate(R.layout.item_ienergy_charging_page, null)
                holder = Holder()
                holder.tv_number = layout.find(R.id.tv_number)
                holder.tv_level = layout.find(R.id.tv_level)
                holder.tv_name = layout.find(R.id.tv_name)
                layout.tag = holder
            }
            holder?.tv_name?.text =  message?.get(pos)?.energyChargingName
            holder?.tv_level?.text =  message?.get(pos)?.classificationName
            holder?.tv_number?.text =  message?.get(pos)?.energyChargingEntity?.code

            return layout
        }

        override fun getItem(p0: Int): BN_EnergyChargingPage.MessageBean? {
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