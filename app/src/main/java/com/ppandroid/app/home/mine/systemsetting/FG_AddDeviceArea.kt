package com.ppandroid.app.home.mine.systemsetting

import android.os.Bundle
import android.text.TextUtils
import com.ppandroid.app.R
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.mine.systemsetting.ET_SyStemSetting
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.im.base.FG_Base
import com.ppandroid.im.bean.BaseBody
import kotlinx.android.synthetic.main.fg_add_device_area.*
import kotlinx.android.synthetic.main.layout_head_view.*
import org.greenrobot.eventbus.EventBus
import java.util.*

/**
 * Created by yeqinfu on 2017/8/28.
 * 新建区域和修改区域
 */
class FG_AddDeviceArea : FG_Base(){
    /**page type default:0 添加区域 1 修改区域*/
    private var pageType = "0"
    /** 如果是修改，这个id不为空 */
    private var objectId = ""
    /** 如果是修改，这个id不为空 */
    private var objectName = ""
    /** 父级id  */
    private var parentId: String = "-1"
    /** 父级名称 */
    private var parentName: String = ""

    companion object {
        fun createBundle(objectId: String,objectName:String,parentId: String,parentName:String): Bundle {
            var b = Bundle()
            b.putString("objectId", objectId)
            b.putString("objectName", objectName)
            b.putString("parentName", parentName)
            b.putString("parentId", parentId)
            b.putString("pageType", "1")
            return b
        }
        fun createBundle(parentId: String,parentName: String): Bundle {
            var b = Bundle()
            b.putString("parentId", parentId)
            b.putString("pageType", "0")
            b.putString("parentName", parentName)
            return b
        }
    }
    override fun fgRes(): Int= R.layout.fg_add_device_area

    override fun afterViews() {
        arguments?.let {
            pageType = it.getString("pageType", "0")
            objectId = it.getString("objectId", "")
            objectName = it.getString("objectName", "")
            parentId = it.getString("parentId", "-1")
            parentName = it.getString("parentName", "")

        }
        if (pageType == "1") {//修改区域
            head_view.setCenterTitle("修改区域")
            if (parentId!="-1"){
                tv_parent_name.text=parentName
            }
            et_name.setText(objectName)
        } else {
            head_view.setCenterTitle("新建区域")
            if (parentId!="-1"){
                tv_parent_name.text=parentName
            }
        }

        head_view.init(activity)
        head_view.setRightText("保存") {
            postInfo()
        }

    }

    private fun postInfo() {
        if (TextUtils.isEmpty(et_name.text)) {
            toast("请输入别名")
            et_name.error="请输入别名"
            return
        }
        var url="user/sysSet/deviceArea/add.json"
        var map = TreeMap<String, String>()
        map.apply {
            if (pageType=="1"){//修改
                put("id",objectId)
            }
            put("name", et_name.text.toString())
            put("parentId", parentId)
        }
        Http.post(activity, url, map, BaseBody::class.java, object : MyCallBack<BaseBody> {
            override fun onResponse(response: BaseBody?) {
                response?.let {
                    if (it.isSuccess) {
                        EventBus.getDefault().post(ET_SyStemSetting(ET_SyStemSetting.TASKID_REFRESH_DEVICE_AREA_PAGE))
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