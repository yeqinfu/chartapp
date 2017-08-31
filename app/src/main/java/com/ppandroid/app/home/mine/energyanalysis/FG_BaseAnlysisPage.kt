package com.ppandroid.app.home.mine.energyanalysis

import android.os.Bundle
import com.ppandroid.app.R
import com.ppandroid.im.base.FG_Base

/**
 * Created by yeqinfu on 2017/8/31.
 */
abstract class FG_BaseAnlysisPage:FG_Base(){
    companion object {
        fun createBundle(index:Int):Bundle{
            var b= Bundle()
            b.putInt("index",index)
            return b
        }
    }
    override fun fgRes(): Int= R.layout.fg_base_analysis_page
    //0 日 1 月 2 年 3 总
    var index:Int = 0
    override fun afterViews() {
        arguments?.let {
            index=it.getInt("index",0)
        }
        init()
    }

    abstract fun init()


}