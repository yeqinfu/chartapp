/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.demo

import com.ppandroid.app.R
import com.ppandroid.app.bean.ET_Refresh
import com.ppandroid.app.widget.deletelistview.ListViewSlideAdapter
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_delete_listview.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by yeqinfu on 2017/9/8.
 */
class FG_DeleteListView :FG_Base(){
    private val list = ArrayList<String>()
    override fun afterViews() {
        isNeedEventBus=true
        (0..19).mapTo(list) { "第" + it.toString() + "个item" }
        var adapter = ListViewSlideAdapter(activity, list)
        lv_list2.adapter = adapter
        adapter.setOnClickListenerEditOrDelete(object : ListViewSlideAdapter.OnClickListenerEditOrDelete {
            override fun OnClickListenerDelete(position: Int) {
                toast("OnClickListenerDelete" + position)
                EventBus.getDefault().post(ET_Refresh(ET_Refresh.TASKID_REFRESH_MINE))

            }

            override fun OnClickListenerEdit(position: Int) {
                toast("OnClickListenerEdit" + position)

            }

        })

      /*  adapter.setOnClickListenerEditOrDelete(object :OnClickListenerDetailOrDelete{
            override fun OnClickListenerDetail(position: Int) {
                toast("OnClickListenerDetail")
            }

            override fun OnClickListenerDelete(position: Int) {
                toast("OnClickListenerDelete")
            }

        })*/
        lv_list2.setOnItemClickListener { adapterView, view, i, l ->
            toast("setOnItemClickListener")
            true
        }
    }

    override fun fgRes(): Int= R.layout.fg_delete_listview
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: ET_Refresh) {
        if (event.taskId === ET_Refresh.TASKID_REFRESH_MINE) {
            toast("FG_DeleteListView")
        }

    }
}