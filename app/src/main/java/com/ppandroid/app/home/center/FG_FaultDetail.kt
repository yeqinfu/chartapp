/*
 * Created by yeqinfu on 17-12-8 下午4:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home.center

import android.os.Bundle
import android.text.TextUtils
import com.ppandroid.app.R
import com.ppandroid.app.bean.news.BN_FaultHistory
import com.ppandroid.app.utils.glide.GlideUtils
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_fault_detail.*
import kotlinx.android.synthetic.main.layout_head_view.*

/**
 * Created by yeqinfu on 2017/12/8.
 */
class FG_FaultDetail:FG_Base(){
    companion object {
        fun createBundle(item: BN_FaultHistory.MessageBean.FailueWarningListBean):Bundle{
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

            var item=it.getSerializable("data") as BN_FaultHistory.MessageBean.FailueWarningListBean
            GlideUtils.displayImage(activity,item.devicePhoto,iv_img)
            head_view.setCenterTitle(item.deviceName)
            tv_device_name.text=item.deviceName
            tv_status001.text=item.status
            tv_time.text=item.failueTime
            tv_content.text=if (TextUtils.isEmpty(item.mark)){
                item.status
            }else{
                item.mark
            }
        }
    }

}