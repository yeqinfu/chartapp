package com.ppandroid.app.home.mine.aboutme

import com.ppandroid.app.R
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.mine.BN_AboutMe
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_about_me.*
import kotlinx.android.synthetic.main.layout_head_view.*

/**
 * Created by yeqinfu on 2017/8/24.
 */
class FG_AboutMe :FG_Base(){
    override fun fgRes(): Int= R.layout.fg_about_me

    override fun afterViews() {
        head_view.init(activity)
        head_view.setCenterTitle("关于节能通")
        val info = context.packageManager.getPackageInfo(context.packageName, 0)
        var currVersionName = info.versionName
        tv_version.text="当前版本："+currVersionName
        loadContent()
    }

    private fun loadContent(){
        var url="user/personal/about.json"
        Http.get(activity,url,BN_AboutMe::class.java,object :MyCallBack<BN_AboutMe>{
            override fun onResponse(response: BN_AboutMe?) {
                response?.let {
                    tv_company_name.text=it.message.companyName
                    tv_address.text=it.message.companyAddress
                    tv_phone.text=it.message.companyPhone
                    tv_website.text=it.message.companyWebsite

                }
            }

            override fun onError(error: ErrorBody?) {
            }

        })
    }

}