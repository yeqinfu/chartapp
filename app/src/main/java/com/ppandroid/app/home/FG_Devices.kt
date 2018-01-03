/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home

import com.ppandroid.app.R
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.devices.BN_Devices
import com.ppandroid.app.home.adapter.AD_ExList
import com.ppandroid.app.home.devices.FG_DevicesInfo
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_devices.*


/**
 * Created by yeqinfu on 2017/8/3.
 */
class FG_Devices : FG_Base() {
    var energyClassificationId="1"
    override fun fgRes(): Int = R.layout.fg_devices
    override fun afterViews() {
        isNeedEventBus=true
        arguments?.let {
            energyClassificationId=it.getString("energyClassificationId","1")
        }
        loadContent()
        refreshLayout.setOnRefreshListener {
            loadContent()
        }
        refreshLayout.isEnableLoadmore = false

    }

    override fun refresh() {
        super.refresh()
        loadContent()
    }



    private fun loadContent() {
        var url = "user/device/index.json?energyClassificationId="+energyClassificationId

        Http.get(activity, url, BN_Devices::class.java, object : MyCallBack<BN_Devices> {
            override fun onResponse(response: BN_Devices?) {
                refreshLayout.finishRefresh()
                response?.let {
                    val adapter = AD_ExList(it.message.deviceCateList, activity)
                    lv_ex.setAdapter(adapter)
                    lv_ex.setOnGroupCollapseListener { var1 ->
                        it.message.deviceCateList[var1].isOpen = false
                        adapter.notifyDataSetChanged()
                      /*  val animator = ObjectAnimator.ofFloat(iv_setting, "rotation", 0f, 180f)
                        animator.duration = (500)
                        animator.start()*/
                    }
                    lv_ex.setOnGroupExpandListener { var1 ->
                        it.message.deviceCateList[var1].isOpen = true
                        adapter.notifyDataSetChanged()

                    }
                    for (i in 0 until adapter.groupCount) {
                        lv_ex.expandGroup(i)
                    }
                    lv_ex.setOnChildClickListener { expandableListView, view, i, j, l ->
                        var item=it.message.deviceCateList[i].deviceList[j]
                        var b=FG_DevicesInfo.Companion.createIntent(item.name,item.id.toString())
                        startAC(FG_DevicesInfo::class.java.name,b)
                        false
                    }
                }

            }

            override fun onError(error: ErrorBody?) {
                refreshLayout.finishRefresh()
                toast(error)
            }

        })

    }


}