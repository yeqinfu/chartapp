package com.ppandroid.app.home.mine.systemsetting

import android.os.Bundle
import android.text.TextUtils
import com.ppandroid.app.R
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.mine.setting.BN_EnergyClasses
import com.ppandroid.app.bean.mine.systemsetting.ET_SyStemSetting
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.app.widget.popwindow.Pop_ChooseSimple
import com.ppandroid.im.base.FG_Base
import com.ppandroid.im.bean.BaseBody
import kotlinx.android.synthetic.main.fg_add_energy_charging.*
import kotlinx.android.synthetic.main.layout_head_view.*
import org.greenrobot.eventbus.EventBus
import java.util.*

/**
 * Created by yeqinfu on 2017/8/28.
 * 新建计费和修改计费
 */
class FG_AddEnergyCharging : FG_Base() {
    /**page type default:0 添加计费 1 修改计费*/
    private var pageType = "0"
    /** 如果是修改，这个id不为空 */
    private var objectId = ""
    /** 如果是修改，这个id不为空 */
    private var objectName = ""
    /** 能源分类：水、电、煤气、蒸汽  */
    private var energyClassificationId: String? = null
    /** 编号  */
    private var code: String? = null

    companion object {
        fun createBundle(objectId: String, energyClassificationId: String, code: String): Bundle {
            var b = Bundle()
            b.putString("objectId", objectId)
            b.putString("energyClassificationId", energyClassificationId)
            b.putString("code", code)
            b.putString("pageType", "1")
            return b
        }
    }

    override fun fgRes(): Int = R.layout.fg_add_energy_charging

    override fun afterViews() {
        loadEnergyClasses()

        arguments?.let {
            pageType = it.getString("pageType", "0")
            objectId = it.getString("objectId", "")
            energyClassificationId = it.getString("energyClassificationId", "")
            code = it.getString("code", "")
        }
        if (pageType == "1") {//修改计费
            head_view.setCenterTitle("修改计费")
            et_name.setText(code)
            tv_choose_class.text=mapIdName[energyClassificationId]
        } else {
            head_view.setCenterTitle("新建计费")
        }

        head_view.init(activity)
        head_view.setRightText("保存") {
            postInfo()
        }
        ll_energy_type.setOnClickListener {
            showPopChoose()
        }

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

    private fun postInfo() {
        if (TextUtils.isEmpty(et_name.text)) {
            toast("请输入编号")
            return
        }
        if (TextUtils.isEmpty(energyClassificationId)) {
            toast("请选择能源分类")
            return
        }
        var url = "user/sysSet/energyCharging/add.json"
        var map = TreeMap<String, String>()
        map.apply {
            if (pageType == "1") {//修改
                put("id", objectId)
            }
            put("code", et_name.text.toString())
            put("classificationId", energyClassificationId.toString())
        }
        Http.post(activity, url, map, BaseBody::class.java, object : MyCallBack<BaseBody> {
            override fun onResponse(response: BaseBody?) {
                response?.let {
                    if (it.isSuccess) {
                        EventBus.getDefault().post(ET_SyStemSetting(ET_SyStemSetting.TASKID_REFRESH_ENERGY_CHARGING))
                        if (pageType == "1") {
                            toast("修改成功")
                        } else {
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

}