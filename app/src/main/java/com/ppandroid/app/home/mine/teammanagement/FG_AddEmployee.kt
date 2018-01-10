/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home.mine.teammanagement

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.text.TextUtils
import android.view.View
import com.ppandroid.app.R
import com.ppandroid.app.bean.ET_Refresh
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.mine.teammanagemet.BN_Role
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.app.widget.popwindow.Pop_ChooseSimple
import com.ppandroid.im.base.FG_Base
import com.ppandroid.im.bean.BaseBody
import kotlinx.android.synthetic.main.fg_add_employee.*
import kotlinx.android.synthetic.main.layout_head_view.*
import org.greenrobot.eventbus.EventBus

/**
 * Created by yeqinfu on 2017/8/24.
 * 添加员工和编辑员工(删除)共用页面
 */
class FG_AddEmployee : FG_Base() {

    companion object {
        fun createBundle(parentId: String): Bundle {
            var b = Bundle()
            b.putString("parentId", parentId)
            b.putInt("pageType", 0)
            return b
        }

        fun createBundle(id: String, parentId: String, roleId: String, realName: String, mobile: String, job: String): Bundle {
            var b = Bundle()
            b.putString("id", id)
            b.putString("parentId", parentId)
            b.putString("roleId", roleId)
            b.putString("realName", realName)
            b.putString("mobile", mobile)
            b.putString("job", job)
            b.putInt("pageType", 1)
            return b
        }
    }

    private var pageType = 0//0添加员工，1 编辑员工

    private var parentId: String = "-1"
    private var id: String = "-1"
    private var roleId: String = ""
    private var realName: String = "-1"
    private var mobile: String = "-1"
    private var job: String = "-1"
    override fun fgRes(): Int = R.layout.fg_add_employee

    fun loadRoleList() {
        var url = "user/personal/getRole.json"
        Http.get(activity,url,BN_Role::class.java,object :MyCallBack<BN_Role>{
            override fun onResponse(response: BN_Role?) {
                response?.let {
                    if (!isAdded){
                        return
                    }
                    for(item in it.message){
                        list.add(item.name)
                        map.put(item.name,item.id.toString())
                        map2.put(item.id.toString(),item.name)
                    }
                }
            }

            override fun onError(error: ErrorBody?) {
                toast(error)
            }

        })
    }

    override fun afterViews() {
        loadRoleList()

        arguments?.let {
            parentId = it.getString("parentId", "-1")
            id = it.getString("id", "-1")
            roleId = it.getString("roleId", "")
            realName = it.getString("realName", "-1")
            mobile = it.getString("mobile", "-1")
            job = it.getString("job", "-1")
            pageType = it.getInt("pageType", 0)
        }
        if (pageType == 1) {//编辑员工
            et_name.setText(realName)
            et_phone.setText(mobile)
            et_job.setText(job)
            tv_jurisdiction.text = map2[roleId]
            btn_del_e.visibility = View.VISIBLE
        }
        btn_del_e.setOnClickListener {
            showConfirmDialog()
        }
        head_view.init(activity)
        head_view.setCenterTitle("添加员工")
        head_view.setRightText("保存") {
            postInfo()
        }
        rl_power.setOnClickListener {
            showChoosePositionDialog()
        }
    }


    private fun showConfirmDialog() {
        val builder = AlertDialog.Builder(activity).setMessage("确定删除吗？").setCancelable(false)
        builder.setPositiveButton("确定") { _, _ ->
            deleteEmployee()
        }
        builder.setNegativeButton("取消") { _, _ ->
            builder.create().dismiss()
        }
        builder.create().show()

    }

    private fun deleteEmployee() {
        var url = "user/team/department/employee/delete.json?id=$id"
        Http.get(activity, url, BaseBody::class.java, object : MyCallBack<BaseBody> {
            override fun onResponse(response: BaseBody?) {
                response?.let {
                    if (!isAdded){
                        return
                    }
                    EventBus.getDefault().post(ET_Refresh(ET_Refresh.TASKID_REFRESH_TEAM_MANAGEMENT))
                    EventBus.getDefault().post(ET_Refresh(ET_Refresh.TASKID_REFRESH_TEAM_DETAIL_REFRESH))
                    toast("删除成功")
                    finish()
                }
            }

            override fun onError(error: ErrorBody?) {
                toast(error)
            }

        })
    }

    var map = HashMap<String, String>()
    var map2 = HashMap<String, String>()
    var list = ArrayList<String>()
    private fun showChoosePositionDialog() {
        if (list.isEmpty()){
            toast("权限列表为空")
            return
        }
        var pop = Pop_ChooseSimple(activity, list)
        pop.showPopupWindow()
        pop.setAutoShowInputMethod(false)
        pop.listener = object : Pop_ChooseSimple.IChooseListener {
            override fun choose(item: String) {
                roleId = map[item].toString()
                tv_jurisdiction.text = item
                pop.dismiss()
            }

        }

    }

    private fun postInfo() {
        var name = et_name.text.toString()
        var phone = et_phone.text.toString()
        var job = et_job.text.toString()
        if (TextUtils.isEmpty(name)) {
            toast("请输入姓名")
            return
        }
        if (TextUtils.isEmpty(phone)) {
            toast("请输入手机号")
            return
        }
        if (TextUtils.isEmpty(job)) {
            toast("请输入职位")
            return
        }
       /* if (TextUtils.isEmpty(roleId)) {
            toast("请选择权限类型")
            return
        }*/

        var url = "user/team/department/employee/add.json?departmentId=$parentId&roleId=$roleId&realName=$name&mobile=$phone&job=$job"

        if (pageType == 1) {
            url += "&id=$id"
        }
        Http.get(activity, url, BaseBody::class.java, object : MyCallBack<BaseBody> {
            override fun onResponse(response: BaseBody?) {
                response?.let {
                    if (!isAdded){
                        return
                    }
                    toast("添加成功")
                    EventBus.getDefault().post(ET_Refresh(ET_Refresh.TASKID_REFRESH_TEAM_MANAGEMENT))
                    EventBus.getDefault().post(ET_Refresh(ET_Refresh.TASKID_REFRESH_TEAM_DETAIL_REFRESH))
                    finish()
                }
            }

            override fun onError(error: ErrorBody?) {
                toast(error)
            }

        })


    }

}