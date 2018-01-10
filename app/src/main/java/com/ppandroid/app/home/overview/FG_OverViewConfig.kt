/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home.overview

import android.os.Bundle
import android.text.TextUtils
import com.ppandroid.app.R
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.overview.BN_OverViewConfig
import com.ppandroid.app.home.FG_OverView
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.im.base.FG_Base
import com.ppandroid.im.bean.BaseBody
import kotlinx.android.synthetic.main.fg_over_view_config.*
import kotlinx.android.synthetic.main.layout_head_view.*
import org.greenrobot.eventbus.EventBus

/**
 * Created by yeqinfu on 2017/8/14.
 */
class FG_OverViewConfig:FG_Base(){
    override fun fgRes(): Int= R.layout.fg_over_view_config
    lateinit var adapter:DragAdapter
    lateinit var adapter2:DragAdapter

    companion object {
        fun createIntent(type:Int): Bundle{
            var b=Bundle()
            b.putInt("type",type)
            return b;

        }
    }
    var type=0
    override fun afterViews() {
        arguments?.let {
            type=it.getInt("type",0)
        }
        head_view.setCenterTitle("自定义模块")
        head_view.init(activity)
        head_view.setRightText("保存") {
          saveInfo()
        }
        adapter = DragAdapter(activity)
        adapter2 = DragAdapter(activity)
        adapter2.delIcon=R.drawable.icon_add
        loadContent()

    }

    private fun loadContent() {
        var url=""
        if (type==1){//用水
            url="user/overview/water/modular.json"
        }else {//用电
            url="user/overview/modular.json"
        }
        Http.get(activity,url, BN_OverViewConfig::class.java,object: MyCallBack<BN_OverViewConfig> {
            override fun onResponse(response: BN_OverViewConfig?) {
                response?.let {
                    if (!isAdded){
                        return
                    }
                    adapter.setDatas(it.message.choosed)
                    drag_list.adapter=adapter
                    adapter2.setDatas(it.message.noChoosed)
                    lv_nochoosed.adapter=adapter2
                    adapter.setListener { pos ->
                        var list=adapter.ts
                        var item= list[pos]
                        list.remove(item)
                        adapter.notifyDataSetChanged()
                        var list2=adapter2.ts
                        list2.add(item)
                        adapter2.notifyDataSetChanged()
                    }

                    adapter2.setListener { pos ->
                        var list=adapter2.ts
                        var item= list[pos]
                        list.remove(item)
                        adapter2.notifyDataSetChanged()
                        var list2=adapter.ts
                        list2.add(item)
                        adapter.notifyDataSetChanged()

                    }

                }
            }

            override fun onError(error: ErrorBody?) {
                toast(error?.message?:"")
            }

        })
    }

    private fun saveInfo() {
        var list=adapter.ts
        var url=""
        if (type==1){
            url="user/overview/water/modular/save.json?"
        }else{
            url="user/overview/modular/save.json?"
        }
        var ids=""
        for (item in list){
            ids+=item.id.toString()+","
        }
        if(!TextUtils.isEmpty(ids)){
            url+="ids="+ids.substring(0,ids.length-1)
        }else{
            toast("请至少选择一个模块显示")
            return
        }
        Http.get(activity,url,BaseBody::class.java,object :MyCallBack<BaseBody>{
            override fun onResponse(response: BaseBody?) {
                response?.let {
                    if (!isAdded){
                        return
                    }
                    if (it.isSuccess){
                        toast("保存成功")
                        if (type==1){
                            EventBus.getDefault().post(FG_OverView.ET_OverView(FG_OverView.ET_OverView.TASKID_REFRESH_WATER))

                        }else{
                            EventBus.getDefault().post(FG_OverView.ET_OverView(FG_OverView.ET_OverView.TASKID_REFRESH))

                        }
                        finish()
                    }
                }
            }

            override fun onError(error: ErrorBody?) {
                toast(error?.message)
            }

        })
    }


}