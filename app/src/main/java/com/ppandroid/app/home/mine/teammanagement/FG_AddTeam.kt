package com.ppandroid.app.home.mine.teammanagement

import android.text.TextUtils
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
 */
class FG_AddTeam : FG_Base(){
    override fun fgRes(): Int= R.layout.fg_add_team

    override fun afterViews() {
        head_view.init(activity)
        head_view.setCenterTitle("添加部门")
        head_view.setRightText("保存"){
            postInfo()
        }
    }

    private var parentId="-1"
    private fun postInfo() {
        if (TextUtils.isEmpty(et_name.text)){
            toast("请输入部门名称")
            return
        }
        var name=et_name.text.toString()
        var url= "user/team/department/add.json?parentId=$parentId&name=$name"
        Http.get(activity,url,BaseBody::class.java,object :MyCallBack<BaseBody>{
            override fun onResponse(response: BaseBody?) {
                response?.let {
                    toast("添加成功")
                    EventBus.getDefault().post(ET_Refresh(ET_Refresh.TASKID_REFRESH_TEAM_MANAGEMENT))
                    finish()
                }
            }

            override fun onError(error: ErrorBody?) {
                toast(error)
            }

        })
    }

}