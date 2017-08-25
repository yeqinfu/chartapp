package com.ppandroid.app.home.mine.teammanagement

import android.os.Bundle
import com.ppandroid.app.R
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.mine.teammanagemet.BN_TeamDetail
import com.ppandroid.app.home.mine.teammanagement.adapter.AD_Employee
import com.ppandroid.app.home.mine.teammanagement.adapter.AD_Team
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_team_detail.*
import kotlinx.android.synthetic.main.layout_head_view.*

/**
 * Created by yeqinfu on 2017/8/25.
 */
class FG_TeamDetail :FG_Base(){
    companion object {
        fun createBundle(title:String,id:String): Bundle {
            var b=Bundle()
            b.putString("title",title)
            b.putString("id",id)
            return b
        }
    }
    override fun fgRes(): Int= R.layout.fg_team_detail

    var id:String?=null
    override fun afterViews() {
        arguments?.let {
            id=it.getString("id","")
            head_view.setCenterTitle(it.getString("title",""))
        }
        head_view.init(activity)
        head_view.setIvRight(R.drawable.ic_add_model){
            toast("tianjia")
        }
        head_view.setIvRight2(R.drawable.ic_tdsz){
            toast("setting")
        }
        loadContent()
    }
    fun loadContent(){
        var url="user/team/department/employee.json?departmentId=$id"
        Http.get(activity,url, BN_TeamDetail::class.java,object :MyCallBack<BN_TeamDetail>{
            override fun onResponse(response: BN_TeamDetail?) {
                response?.let {
                    it.message.departmentList?.let {
                        var teamAdapter=AD_Team(it,activity)
                        lv_list_team.adapter=teamAdapter
                    }
                    it.message.employeeList?.let {
                        var employeeAdapter=AD_Employee(it,activity)
                        lv_list_emp.adapter=employeeAdapter
                    }

                }
            }

            override fun onError(error: ErrorBody?) {
                toast(error)
            }

        })
    }

}