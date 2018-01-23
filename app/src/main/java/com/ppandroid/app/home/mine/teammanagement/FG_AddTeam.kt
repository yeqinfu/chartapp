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
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.im.base.FG_Base
import com.ppandroid.im.bean.BaseBody
import kotlinx.android.synthetic.main.fg_add_team.*
import kotlinx.android.synthetic.main.layout_head_view.*
import org.greenrobot.eventbus.EventBus

/**
 * Created by yeqinfu on 2017/8/24.
 * 添加部门和修改部门共用页面，如果parentId不是-1说明是修改部门
 */
class FG_AddTeam : FG_Base(){
    override fun fgRes(): Int= R.layout.fg_add_team

    companion object {
        fun createBundle(parentId:String,title:String): Bundle {
            var b=Bundle()
            b.putString("parentId",parentId)
            b.putString("title",title)
            return b
        }
    }

    override fun afterViews() {
        arguments?.let {
            parentId=it.getString("parentId","-1")
            title=it.getString("title","添加部门")
        }
        if (parentId == "-1"){//是新增部门
            btn_del.visibility= View.GONE
        }else{
            btn_del.visibility= View.VISIBLE
        }
        btn_del.setOnClickListener {
            showConfirmDialog()
        }
        head_view.init(activity)
        if (TextUtils.isEmpty(title)){
            title="添加部门"
        }
        head_view.setCenterTitle(title)
        head_view.setRightText("保存"){
            postInfo()
        }
    }
    private fun showConfirmDialog() {
        val builder = AlertDialog.Builder(activity).setMessage("确定删除吗？").setCancelable(false)
        builder.setPositiveButton("确定") { _, _ ->
            deleteTeam()
        }
        builder.setNegativeButton("取消") { _, _ ->
            builder.create().dismiss()
        }
        builder.create().show()

    }

    private fun deleteTeam() {
        var url="user/team/department/delete.json?id=$parentId"
        Http.get(activity,url,BaseBody::class.java,object :MyCallBack<BaseBody>{
            override fun onResponse(response: BaseBody?) {
                response?.safeRun {

                    toast("删除成功")
                    EventBus.getDefault().post(ET_Refresh(ET_Refresh.TASKID_REFRESH_TEAM_MANAGEMENT))
                    EventBus.getDefault().post(ET_Refresh(ET_Refresh.TASKID_REFRESH_TEAM_DETAIL))
                    finish()
                }
            }

            override fun onError(error: ErrorBody?) {
                toast(error)
            }

        })
    }

    private var parentId="-1"
    private var title="添加部门"
    private fun postInfo() {
        if (TextUtils.isEmpty(et_name.text)){
            toast("请输入部门名称")
            return
        }
        var name=et_name.text.toString()
        var url= "user/team/department/add.json?parentId=$parentId&name=$name"
        Http.get(activity,url,BaseBody::class.java,object :MyCallBack<BaseBody>{
            override fun onResponse(response: BaseBody?) {
                response?.safeRun {

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