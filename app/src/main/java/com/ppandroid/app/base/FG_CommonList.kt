/*
 * Created by yeqinfu on 17-12-26 下午2:09
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.base

import android.app.Activity
import android.widget.BaseAdapter
import com.ppandroid.app.R
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_common_list.*
import kotlinx.android.synthetic.main.layout_head_view.*

/**
 * Created by yeqinfu on 2017/12/26.
 * 模板类，一个列表，上拉刷新下拉加载更多的通用类
 */

open abstract class FG_CommonList<T> : FG_Base() {
    override fun fgRes(): Int=R.layout.fg_common_list
    override fun afterViews() {
        head_view.init(activity)
        head_view.setCenterTitle(getTitle()?:"")
        refresh_layout.setOnRefreshListener {
            loadData(true)
        }
        refresh_layout.setOnLoadmoreListener{
            loadData(false)
        }
        adapter= getAdapter(activity,content)
        lv_list.adapter=adapter
        loadData(true)
    }

    abstract fun getAdapter(activity: Activity, content: ArrayList<T>): AD_CommonList<T>?

    var adapter:AD_CommonList<T>?=null
    var pageNumber=1
    var totalPage=1
    var content=ArrayList<T>()
    private fun loadData(isRefresh: Boolean) {
        if (isRefresh){
            pageNumber=1
            totalPage=1
            content.clear()
        }
        if (pageNumber>totalPage){
            toast("已经是最后一页")
            refresh_layout.finishLoadmore()
            return
        }
        loadContent()

    }

    abstract fun loadContent()

    protected fun errorToast(error: ErrorBody?){
        refresh_layout.finishRefresh()
        refresh_layout.finishLoadmore()
        toast(error)
    }

    abstract fun getTitle(): String?

    abstract class AD_CommonList<T>(ac: Activity, list:ArrayList<T>): BaseAdapter() {

        protected var ac=ac
        protected var list=list
        override fun getItemId(p0: Int): Long=p0.toLong()

        override fun getCount(): Int=list.size

    }
}
