package com.ppandroid.app.home.mine

import android.text.TextUtils
import com.ppandroid.app.R
import com.ppandroid.app.bean.ET_Base
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_add_instrument.*
import kotlinx.android.synthetic.main.layout_head_view.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.*

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
            startAC(FG_ChooseInstrument::class.java.name)
        }
    }

    /** 层级，1是最高级  */
    private val level = 1
    /** 父级id  */
    private val parentId: Long = -1
    /** 编号  */
    private var code: String? = null
    /** 能源分类：水、电、煤气、蒸汽  */
    private val energyClassificationId: Long? = null
    /** 别名  */
    private val name: String? = null
    /** 位置  */
    private val position: String? = null
    /** 负荷  */
    private val charge: String? = null
    /** 倍率  */
    private val rate: String? = null
    /** 编码地址  */
    private val codeAddress: String? = null
    /** 重点设备id  */
    private var deviceId: Long? = null
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
        code= et_code.text.toString()

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