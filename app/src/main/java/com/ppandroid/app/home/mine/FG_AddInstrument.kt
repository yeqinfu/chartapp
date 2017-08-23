package com.ppandroid.app.home.mine

import android.text.TextUtils
import com.ppandroid.app.R
import com.ppandroid.app.bean.ET_Base
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.mine.setting.BN_EnergyClasses
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.app.widget.popwindow.Pop_ChooseSimple
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_add_instrument.*
import kotlinx.android.synthetic.main.layout_head_view.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.*
import kotlin.collections.HashMap

/**
 * Created by yeqinfu on 2017/8/22.
 * 添加仪表
 */
class FG_AddInstrument:FG_Base(){
    override fun fgRes(): Int= R.layout.fg_add_instrument

    override fun afterViews() {
        isNeedEventBus=true
        head_view.init(activity)
        head_view.setCenterTitle("新建仪表")
        head_view.setRightText("保存") {
            postInfo()
        }
        tv_choose_instruemnt.setOnClickListener {
                var b=FG_ChooseInstrument.createBundle(deviceId)
                startAC(FG_ChooseInstrument::class.java.name,b)
        }
        tv_choose_class.setOnClickListener {
            showPopChoose()
        }
        loadEnergyClasses()
    }

    /**
     * 查询能源分类
     */
    private fun loadEnergyClasses() {
        var url="user/sysSet/energyClassification/search.json"
        Http.get(activity,url,BN_EnergyClasses::class.java,object :MyCallBack<BN_EnergyClasses>{
            override fun onResponse(response: BN_EnergyClasses?) {
                response?.let {
                    for (item in it.message){
                        list.add(item.name)
                        listMap.put(item.name,item.id)
                    }
                }
            }

            override fun onError(error: ErrorBody?) {
                toast(error)
            }

        })
    }
    var list=ArrayList<String>()
    var listMap=HashMap<String,Long>()
    private fun showPopChoose() {
        if (list.isEmpty()){
            loadEnergyClasses()
            return
        }
        var pop=Pop_ChooseSimple(activity,list)
        pop.listener=object :Pop_ChooseSimple.IChooseListener{
            override fun choose(item: String) {
                energyClassificationId= listMap[item]
                tv_choose_class.text=item
                pop.dismiss()
            }
        }
        pop.showPopupWindow()
    }

    /** 层级，1是最高级  */
    private val level = 1
    /** 父级id  */
    private val parentId: Long = -1
    /** 编号  */
    private var code: String? = null
    /** 能源分类：水、电、煤气、蒸汽  */
    private var energyClassificationId: Long? = null
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
    private var deviceId: Long = -1L
    /** 关联设备区域id  */
    private val deviceAreaId: Long? = null
    /** 关联设备分项id  */
    private val deviceCateId: Long? = null
    /** 状态  */
    private val status: Int = 0

    private fun postInfo() {
        if (TextUtils.isEmpty(et_code.text)){
            toast("请输入能源编号")
            return
        }
        if (energyClassificationId==null){
            toast("请选择能源分类")
            return
        }
        if (TextUtils.isEmpty(et_name.text)){
            toast("请输入别名")
            return
        }
        if (TextUtils.isEmpty(et_position.text)){
            toast("请输入位置")
            return
        }
        if (TextUtils.isEmpty(et_charge.text)){
            toast("请输入负载")
            return
        }
        if (TextUtils.isEmpty(et_rate.text)){
            toast("请输入赔率")
            return
        }
        if (TextUtils.isEmpty(et_codeAddress.text)){
            toast("请输入编码地址")
            return
        }

        code= et_code.text.toString()
        name= et_name.text.toString()
        position= et_position.text.toString()
        charge= et_charge.text.toString()
        rate= et_rate.text.toString()
        codeAddress= et_codeAddress.text.toString()





    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: ET_AddInstrument) {
        if (event.taskId == ET_AddInstrument.TASKID_REFRESH) {
            tv_choose_instruemnt.text=event.name
            deviceId=event.id
        }

    }

    class ET_AddInstrument( taskId: Int,id:Long, name: String) : ET_Base(taskId) {
        companion object {
            /**刷新 */
            val TASKID_REFRESH = UUID.randomUUID().hashCode()
        }
         var id=id
         var name=name

    }


}