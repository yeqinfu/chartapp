/*
 * Created by yeqinfu on 17-12-8 下午4:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home.center

import android.os.Bundle
import com.ppandroid.app.R
import com.ppandroid.app.bean.news.BN_SecurityCenter
import com.ppandroid.app.utils.glide.GlideUtils
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_fault_detail.*
import kotlinx.android.synthetic.main.layout_head_view.*

/**
 * Created by yeqinfu on 2017/12/8.
 */
class FG_FaultDetail:FG_Base(){
    companion object {
        fun createBundle(item: BN_SecurityCenter.MessageBean.DeviceEntityListBean):Bundle{
            var b= Bundle()
            b.putSerializable("data",item)
            return b
        }
    }
    override fun fgRes(): Int= R.layout.fg_fault_detail

    override fun afterViews() {
        head_view.setBackText("返回")
        head_view.init(activity)
        arguments?.let {

            var item=it.getSerializable("data") as BN_SecurityCenter.MessageBean.DeviceEntityListBean
            GlideUtils.displayImage(activity,item.photo,iv_img)
            head_view.setCenterTitle(item.name)
            tv_device_name.text=item.name
            tv_status001.text=item.statusString
            tv_time.text=item.lastModifyTime
            tv_content.text=item.statusString
        }
    }

}