package com.ppandroid.app.home

import com.ppandroid.app.R
import com.ppandroid.app.widget.CheckView
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_security_center.*
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread

/**
 * Created by yeqinfu on 2017/8/7.
 * 安全监测中心
 */
class FG_SecurityCenter:FG_Base(){
    override fun fgRes(): Int= R.layout.fg_security_center

    override fun afterViews() {
        v_check_view.listener = object : CheckView.ProgreesListener {
            override fun change(progress: Float) {
                async {
                    uiThread {
                        tv_score.text=(progress*100).toInt().toString()
                    }
                }
            }
        }
        button.setOnClickListener {
            v_check_view.startAnim()
        }
    }

}