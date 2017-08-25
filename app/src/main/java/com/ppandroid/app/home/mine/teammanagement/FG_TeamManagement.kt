package com.ppandroid.app.home.mine.teammanagement

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import com.ppandroid.app.R
import com.ppandroid.app.bean.ET_Refresh
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.mine.teammanagemet.BN_TeamManagement
import com.ppandroid.app.home.mine.teammanagement.adapter.AD_Team
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.app.widget.CustomDialog
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_team_management.*
import kotlinx.android.synthetic.main.layout_head_view.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by yeqinfu on 2017/8/24.
 * 部门管理
 */
class FG_TeamManagement : FG_Base() {
    override fun fgRes(): Int = R.layout.fg_team_management

    override fun afterViews() {
        isNeedEventBus=true
        head_view.init(activity)
        head_view.setCenterTitle("部门管理")
        head_view.setIvRight(R.drawable.ic_add_model) {
            showChooseDialog()
        }
        loadContent()

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: ET_Refresh) {
        if (event.taskId === ET_Refresh.TASKID_REFRESH_TEAM_MANAGEMENT) {
            loadContent()
        }

    }

    internal var dialog: CustomDialog? = null
    private fun showChooseDialog() {
        val mView = LayoutInflater.from(activity).inflate(R.layout.dialog_choose_add_team, null)
        dialog = CustomDialog(activity, R.style.family_dialog_theme, mView, Gravity.CENTER, 4)
        mView.findViewById(R.id.rl_add_team).setOnClickListener(dialogListener)
        mView.findViewById(R.id.rl_add_e).setOnClickListener(dialogListener)
        dialog?.show()
    }

    private val dialogListener = View.OnClickListener { v ->
        when (v.id) {
            R.id.rl_add_team -> {
                startAC(FG_AddTeam::class.java.name)
                dialog?.dismiss()

            }
            R.id.rl_add_e -> {
                startAC(FG_AddEmployee::class.java.name)
                dialog?.dismiss()
            }

        }
    }


    private fun loadContent() {
        var url = "user/team/department/list.json"
        Http.get(activity, url, BN_TeamManagement::class.java, object : MyCallBack<BN_TeamManagement> {
            override fun onResponse(response: BN_TeamManagement?) {
                response?.let {
                    var adapter = AD_Team(it.message, activity)
                    lv_list.adapter = adapter
                    lv_list.setOnItemClickListener { _, _, i, _ ->
                        var b=FG_TeamDetail.createBundle(it.message[i].name,it.message[i].id.toString())
                        startAC(FG_TeamDetail::class.java.name,b)
                    }

                }
            }

            override fun onError(error: ErrorBody?) {
                toast(error)
            }

        })
    }






}