package com.ppandroid.app.home.mine.systemsetting

import android.os.Bundle
import android.text.TextUtils
import com.ppandroid.app.R
import com.ppandroid.app.bean.ET_Base
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.mine.setting.BN_EnergyClasses
import com.ppandroid.app.bean.mine.systemsetting.BN_AddInstrumentDetail
import com.ppandroid.app.bean.mine.systemsetting.ET_SyStemSetting
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.app.widget.popwindow.Pop_ChooseSimple
import com.ppandroid.im.base.FG_Base
import com.ppandroid.im.bean.BaseBody
import kotlinx.android.synthetic.main.fg_add_instrument.*
import kotlinx.android.synthetic.main.layout_head_view.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.*
import kotlin.collections.HashMap

/**
 * Created by yeqinfu on 2017/8/22.
 * 添加仪表 修改仪表信息
 */
class FG_AddInstrument : FG_Base() {
    /**page type default:0 添加仪表 1 修改仪表*/
    private var pageType = "0"
    /** 如果是修改，这个id不为空 */
    private var objectId = ""
    /** 父级id  */
    private var parentId: String = "-1"
    /** 父级名称 */
    private var parentName: String = ""
    /** 编号  */
    private var code: String? = null
    /** 能源分类：水、电、煤气、蒸汽  */
    private var energyClassificationId: String? = null
    /** 别名  */
    private var name: String? = null
    /** 位置  */
    private var position: String? = null
    /** 负荷  */
    private var charge: String? = null
    /** 倍率  */
    private var rate: String? = null
    /** 编码地址  */
    private var codeAddress: String? = null
    /** 重点设备id  */
    private var deviceId: String = "-1"

    companion object {
        fun createBundle(objectId: String,parentName:String): Bundle {
            var b = Bundle()
            b.putString("objectId", objectId)
            b.putString("parentName", parentName)
            b.putString("pageType", "1")
            return b
        }
        fun createBundle(parentId: String,parentName: String,useless:String): Bundle {
            var b = Bundle()
            b.putString("parentId", parentId)
            b.putString("pageType", "0")
            b.putString("parentName", parentName)
            return b
        }
    }

    override fun fgRes(): Int = R.layout.fg_add_instrument

    override fun afterViews() {
        /**第一步必须先加载能源分类，因为modified的时候要根据id查到分类名称*/
        loadEnergyClasses()
        arguments?.let {
            pageType = it.getString("pageType", "0")
            objectId = it.getString("objectId", "")
            parentId = it.getString("parentId", "-1")
            parentName = it.getString("parentName", "")

        }

        if (pageType == "1") {//修改仪表
            head_view.setCenterTitle("修改仪表")
            tv_parent_name.text=parentName
            loadInstrumentDetail()

        } else {
            head_view.setCenterTitle("新建仪表")
            if (parentId!="-1"){
                tv_parent_name.text=parentName
            }
        }

        isNeedEventBus = true
        head_view.init(activity)
        head_view.setRightText("保存") {
            postInfo()
        }
        tv_choose_instruemnt.setOnClickListener {
            var b = FG_ChooseInstrument.createBundle(deviceId.toLong())
            startAC(FG_ChooseInstrument::class.java.name, b)
        }
        tv_choose_class.setOnClickListener {
            showPopChoose()
        }

    }

    /**
     * 修改的情况下，根据上级页面传入的id设置页面内容
     */
    private fun loadInstrumentDetail() {
        var url="user/sysSet/instrument/details.json?id=$objectId"
        Http.get(activity,url, BN_AddInstrumentDetail::class.java,object :MyCallBack<BN_AddInstrumentDetail>{
            override fun onResponse(response: BN_AddInstrumentDetail?) {
                response?.let {
                    et_code.setText(it.message.code)
                    tv_choose_class.text=it.message.energyClassificationName
                    energyClassificationId=it.message.energyClassificationId?.toString()
                    et_name.setText(it.message.name)
                    et_position.setText(it.message.position)
                    et_charge.setText(it.message.charge)
                    et_rate.setText(it.message.rate)
                    et_codeAddress.setText(it.message.codeAddress)
                    tv_choose_instruemnt.text=it.message.deviceName
                    deviceId=it.message.deviceId?.toString()


                }
            }

            override fun onError(error: ErrorBody?) {
                toast(error)
            }

        })

    }

    /**
     * 查询能源分类
     */
    private fun loadEnergyClasses() {
        var url = "user/sysSet/energyClassification/search.json"
        Http.get(activity, url, BN_EnergyClasses::class.java, object : MyCallBack<BN_EnergyClasses> {
            override fun onResponse(response: BN_EnergyClasses?) {
                response?.let {
                    for (item in it.message) {
                        list.add(item.name)
                        listMap.put(item.name, item.id)
                        mapIdName.put(item.id.toString(), item.name)
                    }
                }
            }

            override fun onError(error: ErrorBody?) {
                toast(error)
            }

        })
    }

    var list = ArrayList<String>()
    var listMap = HashMap<String, Long>()
    var mapIdName = HashMap<String, String>()
    private fun showPopChoose() {
        if (list.isEmpty()) {
            loadEnergyClasses()
            return
        }
        var pop = Pop_ChooseSimple(activity, list)
        pop.setAutoShowInputMethod(false)
        pop.listener = object : Pop_ChooseSimple.IChooseListener {
            override fun choose(item: String) {
                energyClassificationId = listMap[item].toString()
                tv_choose_class.text = item
                pop.dismiss()
            }
        }

        pop.showPopupWindow()
    }


    private fun postInfo() {
        if (TextUtils.isEmpty(et_code.text)) {
            toast("请输入能源编号")
            et_code.error = "请输入能源编号"
            return
        }
        if (energyClassificationId == null) {
            toast("请选择能源分类")
            return
        }
        if (TextUtils.isEmpty(et_name.text)) {
            toast("请输入别名")
            et_name.error = "请输入别名"
            return
        }
        if (TextUtils.isEmpty(et_position.text)) {
            toast("请输入位置")
            et_position.error = "请输入位置"

            return
        }
        if (TextUtils.isEmpty(et_charge.text)) {
            toast("请输入负载")
            et_charge.error = "请输入负载"

            return
        }
        if (TextUtils.isEmpty(et_rate.text)) {
            toast("请输入赔率")
            et_rate.error = "请输入赔率"

            return
        }
        if (TextUtils.isEmpty(et_codeAddress.text)) {
            toast("请输入编码地址")
            et_codeAddress.error = "请输入编码地址"
            return
        }

        code = et_code.text.toString()
        name = et_name.text.toString()
        position = et_position.text.toString()
        charge = et_charge.text.toString()
        rate = et_rate.text.toString()
        codeAddress = et_codeAddress.text.toString()

        var map = TreeMap<String, String>()
        map.apply {
            if (pageType=="1"){//修改
                put("id",objectId)
            }
            put("parentId", parentId.toString())
            put("code", code.toString())
            put("name", name.toString())
            put("position", position.toString())
            put("charge", charge.toString())
            put("rate", rate.toString())
            put("codeAddress", codeAddress.toString())
            put("deviceId", deviceId.toString())
            put("energyClassificationId", energyClassificationId.toString())
        }
        var url = "user/sysSet/instrument/add.json"
        Http.post(activity, url, map, BaseBody::class.java, object : MyCallBack<BaseBody> {
            override fun onResponse(response: BaseBody?) {
                response?.let {
                    if (it.isSuccess) {
                        EventBus.getDefault().post(ET_SyStemSetting(ET_SyStemSetting.TASKID_REFRESH_INSTRUMENT_PAGE))
                        if (pageType=="1"){
                            toast("修改成功")
                        }else{
                            toast("添加成功")
                        }
                        finish()
                    } else {
                        toast(it.title)
                    }
                }
            }

            override fun onError(error: ErrorBody?) {
                toast(error)
            }

        })


    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: ET_AddInstrument) {
        if (event.taskId == ET_AddInstrument.TASKID_REFRESH) {
            tv_choose_instruemnt.text = event.name
            deviceId = event.id.toString()
        }

    }

    class ET_AddInstrument(taskId: Int, id: Long, name: String) : ET_Base(taskId) {
        companion object {
            /**刷新 */
            val TASKID_REFRESH = UUID.randomUUID().hashCode()
        }

        var id = id
        var name = name

    }


}