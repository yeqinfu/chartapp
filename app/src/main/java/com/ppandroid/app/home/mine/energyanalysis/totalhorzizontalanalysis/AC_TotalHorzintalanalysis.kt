/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home.mine.energyanalysis.totalhorzizontalanalysis

import android.os.Bundle
import android.support.v4.app.Fragment
import com.ppandroid.app.R
import com.ppandroid.app.home.mine.energyanalysis.horizontalanalysis.AC_HorChart

/**
 * Created by yeqinfu on 2017/9/18.
 * 总的横向分析图
 */
class AC_TotalHorzintalanalysis : AC_HorChart() {
    override fun getTopDrawable(i: Int): Int {
        return when (i) {
            0 -> {
                R.drawable.zuzt
            }
            1 -> {
                R.drawable.dianzt
            }
            2 -> {
                R.drawable.dianzt
            }
            else -> {
                R.drawable.zuzt
            }
        }

    }

    var list = ArrayList<String>()
    override fun init() {
        if (energyClassificationId=="1"){
            list.add("用电柱状图")
            list.add("用电环比图")
            list.add("用电同比图")
        }else{
            list.add("用水柱状图")
            list.add("用水环比图")
            list.add("用水同比图")

        }

        setContent()
    }

    override fun getTitlePage(i: Int): String = list[i]

    override fun getFragmentPage(i: Int): Fragment {
        return when (i) {
            0 -> {
                var fg = FG_Histogram()
                fg.arguments = createBundle()
                fg
            }
            1 -> {

                var fg = FG_Huan()
                fg.arguments = createBundle()
                fg
            }
            else -> {

                var fg = FG_Tong()
                fg.arguments = createBundle()
                fg
            }
        }
    }

    override fun getCountPage(): Int = 3

    fun createBundle(): Bundle {
        var b = Bundle()
        b.putString("energyClassificationId", energyClassificationId)
        return b
    }

}