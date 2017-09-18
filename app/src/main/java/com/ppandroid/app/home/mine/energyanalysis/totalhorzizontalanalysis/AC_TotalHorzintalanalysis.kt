package com.ppandroid.app.home.mine.energyanalysis.totalhorzizontalanalysis

import android.support.v4.app.Fragment
import com.ppandroid.app.home.mine.energyanalysis.horizontalanalysis.AC_HorChart

/**
 * Created by yeqinfu on 2017/9/18.
 * 总的横向分析图
 */
class AC_TotalHorzintalanalysis:AC_HorChart(){
    var list=ArrayList<String>()
    override fun init() {
        list.add("用电柱状图")
        list.add("用电环比图")
        list.add("用电同比图")
    }

    override fun getTitlePage(i: Int): String=list[i]

    override fun getFragmentPage(i: Int): Fragment {
        if (i==0){
            return FG_TotalHisogramPage()
        }else if(i==1){
            return FG_HuanPage()
        }else{
            return FG_TongPage()
        }
    }

    override fun getCountPage(): Int=3

}