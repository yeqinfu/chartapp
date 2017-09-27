/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home.mine.teammanagement.adapter

import android.app.Activity
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.ppandroid.app.R
import com.ppandroid.app.bean.mine.teammanagemet.BN_Employee
import com.ppandroid.app.utils.glide.GlideUtils
import org.jetbrains.anko.find

/**
 * Created by yeqinfu on 2017/8/25.
 */
class AD_Employee(message: List<BN_Employee>, ac: Activity) : BaseAdapter() {
    internal var message: List<BN_Employee> = message
    internal var ac: Activity = ac

    override fun getView(pos: Int, convertView: View?, p2: ViewGroup?): View {
        var layout = convertView ?: ac.layoutInflater.inflate(R.layout.item_employee, null)
        var holder: Holder? = null
        if (convertView != null) {
            holder = convertView.tag as Holder?
        } else {
            holder = Holder()
            holder.tv_name = layout.find(R.id.tv_name)
            holder.iv_head = layout.find(R.id.iv_head)
            holder.tv_tag = layout.find(R.id.tv_tag)
        }
        holder?.let {
            it.tv_name?.text = message[pos].realName
            if (TextUtils.isEmpty(message[pos].roleName)){
                it.tv_tag?.visibility=View.GONE
            }
            it.tv_tag?.text = message[pos].roleName
            GlideUtils.displayImage(ac,message[pos].headPhoto,it.iv_head)
        }

        return layout
    }

    override fun getItem(p0: Int): Any = message[p0]

    override fun getItemId(p0: Int): Long = p0.toLong()

    override fun getCount(): Int = message.size

    class Holder {
        var tv_name: TextView? = null
        var iv_head: ImageView? = null
        var tv_tag: TextView? = null
    }

}