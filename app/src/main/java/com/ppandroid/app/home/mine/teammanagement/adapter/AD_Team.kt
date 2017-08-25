package com.ppandroid.app.home.mine.teammanagement.adapter

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.ppandroid.app.R
import com.ppandroid.app.bean.mine.teammanagemet.BN_Team
import org.jetbrains.anko.find

/**
 * Created by yeqinfu on 2017/8/25.
 */
class AD_Team(message: List<BN_Team>, ac: Activity) : BaseAdapter() {
    internal var message: List<BN_Team> = message
    internal var ac: Activity = ac

    override fun getView(pos: Int, convertView: View?, p2: ViewGroup?): View {
        var layout = convertView ?: ac.layoutInflater.inflate(R.layout.item_team_management, null)
        var holder: Holder? = null
        if (convertView != null) {
            holder = convertView.tag as Holder?
        } else {
            holder = Holder()
            holder.tv_name = layout.find(R.id.tv_name)
            holder.tv_department_number = layout.find(R.id.tv_department_number)
            holder.tv_employee_number = layout.find(R.id.tv_employee_number)
        }
        holder?.let {
            it.tv_name?.text = message[pos].name
            it.tv_department_number?.text = message[pos].sonSum.toString()
            it.tv_employee_number?.text = message[pos].employeeSum.toString()
        }

        return layout
    }

    override fun getItem(p0: Int): Any = message[p0]

    override fun getItemId(p0: Int): Long = p0.toLong()

    override fun getCount(): Int = message.size

    class Holder {
        var tv_name: TextView? = null
        var tv_department_number: TextView? = null
        var tv_employee_number: TextView? = null
    }

}
