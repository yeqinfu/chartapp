package com.ppandroid.app.home.mine.energyanalysis.horizontalanalysis

import android.os.Bundle
import android.support.v4.app.Fragment
import com.ppandroid.app.R
import com.ppandroid.app.widget.WheelViewSelector
import com.ppandroid.im.base.AC_Base
import kotlinx.android.synthetic.main.activity_ac__hor_chart.*


class AC_HorChart : AC_Base() {
    private var fragments: ArrayList<Fragment>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ac__hor_chart)


        fragments =ArrayList()
        fragments?.let {
            it.add(FG_HistogramAnalysis())
            it.add(FG_HuanAnalysis())
            it.add(FG_TongAnalysis())
        }





        wv_choose.offset = 3
        var list=ArrayList<String>()
        list.add("柱状图")
        list.add("环比图")
        list.add("同比图")
        wv_choose.setItems(list)
        wv_choose.onWheelViewListener = object : WheelViewSelector.OnWheelViewListener() {
            override fun onSelected(selectedIndex: Int, item: String) {
                replaceFragment(selectedIndex)
            }
        }
        replaceFragment(0)
        setEnablePullToBack(false)


    }
    fun replaceFragment(selectedIndex: Int){
        fragments?.let {
            val t = supportFragmentManager.beginTransaction()
            t.replace(R.id.ll_fragment, it[selectedIndex])
            t.commit()
        }

    }
}
