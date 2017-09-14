package com.ppandroid.app.demo

import android.os.Bundle
import com.ppandroid.app.R
import com.ppandroid.app.widget.WheelViewSelector
import com.ppandroid.im.base.AC_Base
import kotlinx.android.synthetic.main.activity_ac__hor_chart.*


class AC_HorChart : AC_Base() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ac__hor_chart)
        wv_choose.offset = 1
        var list=ArrayList<String>()
        list.add("Mercury")
        list.add("Venus")
        list.add("Earth")
        list.add("Jupiter")
        wv_choose.setItems(list)
        wv_choose.onWheelViewListener = object : WheelViewSelector.OnWheelViewListener() {
            override fun onSelected(selectedIndex: Int, item: String) {
                toast("item--"+item)

            }
        }
        setEnablePullToBack(false)
    }
}
