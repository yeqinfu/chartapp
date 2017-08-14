package com.ppandroid.app.home.overview

import com.ppandroid.app.R
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.overview.BN_OverViewConfig
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.app.http.OKUtils
import com.ppandroid.im.base.FG_Base
import com.ppandroid.im.bean.BaseBody
import kotlinx.android.synthetic.main.fg_over_view_config.*
import kotlinx.android.synthetic.main.layout_head_view.*

/**
 * Created by yeqinfu on 2017/8/14.
 */
class FG_OverViewConfig:FG_Base(){
    override fun fgRes(): Int= R.layout.fg_over_view_config
    lateinit var adapter:DragAdapter
    lateinit var adapter2:DragAdapter
    override fun afterViews() {
        head_view.setCenterTitle("自定义模块")
        head_view.init(activity)
        head_view.setRightText("保存") {
          saveInfo()
        }
        adapter = DragAdapter(activity)
        adapter2 = DragAdapter(activity)
        loadContent()

    }

    private fun loadContent() {
        var url="user/overview/modular.json"
        OKUtils.get(activity,url, BN_OverViewConfig::class.java,object: MyCallBack<BN_OverViewConfig> {
            override fun onResponse(response: BN_OverViewConfig?) {
                response?.let {
                    adapter.setDatas(it.message.choosed)
                    drag_list.adapter=adapter

                    adapter2.setDatas(it.message.noChoosed)
                    lv_nochoosed.adapter=adapter2
                    adapter.setListener { pos ->
                        var list=adapter.ts
                        var item=list.get(pos)
                        list.remove(item)
                        adapter.notifyDataSetChanged()
                        var list2=adapter2.ts
                        list2.add(item)
                        adapter2.notifyDataSetChanged()
                    }

                    adapter2.setListener { pos ->
                        var list=adapter2.ts
                        var item=list.get(pos)
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
        var url="user/overview/modular/save.json?"
        for (item in list){
            url+="ids="+item.id.toString()+"&"
        }
        url=url.substring(0,url.length-1)
        OKUtils.get(activity,url,BaseBody::class.java,object :MyCallBack<BaseBody>{
            override fun onResponse(response: BaseBody?) {
                response?.let {
                    if (it.isSuccess){
                        toast("保存成功")
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