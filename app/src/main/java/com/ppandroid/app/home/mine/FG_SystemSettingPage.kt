package com.ppandroid.app.home.mine

import com.ppandroid.app.R
import com.ppandroid.app.base.NetWorkErrorView
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.mine.BN_SystemSettingPage01
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.layout_network_error.*

/**
 * Created by yeqinfu on 2017/8/22.
 */
class FG_SystemSettingPage:FG_Base(){
    override fun fgRes(): Int= R.layout.fg_system_setting_page

    override fun afterViews() {
        loadContent()
        network_error.setListener {
            loadContent()
        }
    }
    private fun loadContent(){
        var url="user/sysSet/instrument/search.json"
        Http.get(activity,url,BN_SystemSettingPage01::class.java,object :MyCallBack<BN_SystemSettingPage01>{
            override fun onResponse(response: BN_SystemSettingPage01?) {
                response?.let {
                    if (response.message.isEmpty()){
                        network_error.setViewType(NetWorkErrorView.NOT_DATA)
                    }else{
                        network_error.setViewType(NetWorkErrorView.NORMAL_VIEW)
                    }

                }
            }

            override fun onError(error: ErrorBody?) {
                network_error.setViewType(NetWorkErrorView.NETWORK_ERROR)
                toast(error)
            }

        })

    }

}