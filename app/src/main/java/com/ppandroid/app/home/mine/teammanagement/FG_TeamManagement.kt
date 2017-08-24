package com.ppandroid.app.home.mine.teammanagement

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.ppandroid.app.R
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.mine.teammanagemet.BN_TeamManagement
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_team_management.*
import kotlinx.android.synthetic.main.layout_head_view.*
import org.jetbrains.anko.find

/**
 * Created by yeqinfu on 2017/8/24.
 * 团队管理
 */
class FG_TeamManagement : FG_Base() {
    override fun fgRes(): Int = R.layout.fg_team_management

    override fun afterViews() {
        head_view.init(activity)
        head_view.setCenterTitle("团队管理")
        loadContent()

    }

    private fun loadContent() {
        var url = "user/team/department/list.json"
        Http.get(activity, url, BN_TeamManagement::class.java, object : MyCallBack<BN_TeamManagement> {
            override fun onResponse(response: BN_TeamManagement?) {
                response?.let {
                    var adapter=AD_List(it.message,activity)
                    lv_list.adapter=adapter

                }
            }

            override fun onError(error: ErrorBody?) {
                toast(error)
            }

        })
    }

    class Holder{
        var tv_name:TextView?=null
        var tv_department_number:TextView?=null
        var tv_employee_number:TextView?=null
    }

    class AD_List(message: List<BN_TeamManagement.MessageBean>, ac: Activity) : BaseAdapter() {
        internal var message: List<BN_TeamManagement.MessageBean> = message
        internal var ac: Activity = ac

        override fun getView(pos: Int, convertView: View?, p2: ViewGroup?): View {
            var layout= convertView ?: ac.layoutInflater.inflate(R.layout.item_team_management,null)
            var holder:Holder?=null
            if (convertView!=null){
                holder= convertView.tag as Holder?
            }else{
                holder=Holder()
                holder.tv_name=layout.find(R.id.tv_name)
                holder.tv_department_number=layout.find(R.id.tv_department_number)
                holder.tv_employee_number=layout.find(R.id.tv_employee_number)
            }
            holder?.let {
                it.tv_name?.text=message[pos].name
                it.tv_department_number?.text=message[pos].sonSum.toString()
                it.tv_employee_number?.text=message[pos].employeeSum.toString()
            }

            return layout
        }

        override fun getItem(p0: Int): Any=message[p0]

        override fun getItemId(p0: Int): Long=p0.toLong()

        override fun getCount(): Int=message.size

    }


}