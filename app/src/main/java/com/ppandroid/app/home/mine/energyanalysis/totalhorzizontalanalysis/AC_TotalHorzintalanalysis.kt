package com.ppandroid.app.home.mine.energyanalysis.totalhorzizontalanalysis

import android.support.v4.app.Fragment
import com.ppandroid.app.R
import com.ppandroid.app.home.mine.energyanalysis.horizontalanalysis.AC_HorChart

/**
 * Created by yeqinfu on 2017/9/18.
 * 总的横向分析图
 */
class AC_TotalHorzintalanalysis:AC_HorChart(){
    override fun getTopDrawable(i: Int): Int{
        return when(i){
            0->{
                R.drawable.zuzt
            }
            1->{
               R.drawable.dianzt
            }
            2->{
                R.drawable.dianzt
            }
            else->{
                R.drawable.zuzt
            }
        }

    }
    var list=ArrayList<String>()
    override fun init() {
        list.add("用电柱状图")
        list.add("用电环比图")
        list.add("用电同比图")
        setContent()
    }

    override fun getTitlePage(i: Int): String=list[i]

    override fun getFragmentPage(i: Int): Fragment {
        return when (i) {
            0 -> FG_Histogram()
            1 -> FG_Huan()
            else -> FG_Tong()
        }
    }

    override fun getCountPage(): Int=3

}