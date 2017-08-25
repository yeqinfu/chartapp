package com.ppandroid.app.home.mine.teammanagement

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import com.ppandroid.app.R
import com.ppandroid.app.bean.ET_Refresh
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.mine.teammanagemet.BN_TeamDetail
import com.ppandroid.app.home.mine.teammanagement.adapter.AD_Employee
import com.ppandroid.app.home.mine.teammanagement.adapter.AD_Team
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.app.widget.CustomDialog
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_team_detail.*
import kotlinx.android.synthetic.main.layout_head_view.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.find

/**
 * Created by yeqinfu on 2017/8/25.
 */
class FG_TeamDetail : FG_Base() {
    companion object {
        fun createBundle(title: String, id: String): Bundle {
            var b = Bundle()
            b.putString("title", title)
            b.putString("id", id)
            return b
        }
    }

    override fun fgRes(): Int = R.layout.fg_team_detail

    var id: String? = null
    var title: String? = null
    override fun afterViews() {
        isNeedEventBus = true
        arguments?.let {
            id = it.getString("id", "")
            title=it.getString("title", "")
            head_view.setCenterTitle(title)
        }
        head_view.init(activity)
        head_view.setIvRight(R.drawable.ic_add_model) {
            showChooseDialog()
        }
        head_view.setIvRight2(R.drawable.ic_tdsz) {
            var b = id?.let { FG_AddTeam.createBundle(it, arguments?.getString("title", "") ?: "") }
            b?.let { it1 -> startAC(FG_AddTeam::class.java.name, it1) }
        }
        loadContent()
    }

    fun loadContent() {
        var url = "user/team/department/employee.json?departmentId=$id"
        Http.get(activity, url, BN_TeamDetail::class.java, object : MyCallBack<BN_TeamDetail> {
            override fun onResponse(response: BN_TeamDetail?) {
                response?.let {
                    it.message.departmentList?.let {
                        var teamAdapter = AD_Team(it, activity)
                        lv_list_team.adapter = teamAdapter
                        lv_list_team.setOnItemClickListener { _, _, i, _ ->
                            var b = FG_TeamDetail.createBundle(it[i].name.toString(), it[i].id.toString())
                            startAC(FG_TeamDetail::class.java.name, b)

                        }

                    }
                    it.message.employeeList?.let {
                        var employeeAdapter = AD_Employee(it, activity)
                        lv_list_emp.adapter = employeeAdapter
                        lv_list_emp.setOnItemClickListener { _, _, i, _ ->
                            var b=FG_AddEmployee.createBundle(
                                    it[i].id?.toString(),
                                    it[i].departmentId?.toString(),
                                    it[i].roleId?.toString(),
                                    it[i].realName?:"",
                                    it[i].mobile?:"",
                                    it[i].job?:""
                                    )
                            startAC(FG_AddEmployee::class.java.name,b)

                        }
                    }

                }
            }

            override fun onError(error: ErrorBody?) {
                toast(error)
            }

        })
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: ET_Refresh) {
        if (event.taskId === ET_Refresh.TASKID_REFRESH_TEAM_DETAIL) {
           finish()
        }else if (event.taskId==ET_Refresh.TASKID_REFRESH_TEAM_DETAIL_REFRESH){
            loadContent()
        }

    }

    internal var dialog: CustomDialog? = null
    private fun showChooseDialog() {
        val mView = LayoutInflater.from(activity).inflate(R.layout.dialog_choose_add_team, null)
        dialog = CustomDialog(activity, R.style.family_dialog_theme, mView, Gravity.CENTER, 4)
        mView.findViewById(R.id.rl_add_team).setOnClickListener(dialogListener)
        mView.find<View>(R.id.v_line).visibility=View.VISIBLE
        var v = mView.findViewById(R.id.rl_add_e)
        v.setOnClickListener(dialogListener)
        v.visibility=View.VISIBLE
        dialog?.show()
    }

    private val dialogListener = View.OnClickListener { v ->
        when (v.id) {
            R.id.rl_add_team -> {
                var b=FG_AddTeam.createBundle(id.toString(),title?:"")
                startAC(FG_AddTeam::class.java.name,b)
                dialog?.dismiss()

            }
            R.id.rl_add_e -> {
                var b=FG_AddEmployee.createBundle(id.toString())
                startAC(FG_AddEmployee::class.java.name,b)
                dialog?.dismiss()
            }

        }
    }
}