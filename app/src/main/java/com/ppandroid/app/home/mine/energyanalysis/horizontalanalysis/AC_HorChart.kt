/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home.mine.energyanalysis.horizontalanalysis

import android.os.Bundle
import android.support.v4.app.Fragment
import com.ppandroid.app.R
import com.ppandroid.app.bean.mine.energyanalysis.Model
import com.ppandroid.app.widget.WheelViewSelector
import com.ppandroid.im.base.AC_Base
import kotlinx.android.synthetic.main.activity_ac__hor_chart.*


open abstract class AC_HorChart : AC_Base() {
    protected var energyClassificationId="1"


    private var fragments: ArrayList<Fragment>? = null

    protected fun setContent(){
        fragments =ArrayList()
        wv_choose.offset = 3
        var list=ArrayList<Model>()
        /**分别代表右边栏的列表总数*/
        var total=getCountPage()-1
        for (i in 0..total){
            var model= Model()
            model.title=getTitlePage(i)
            model.topDrawable=getTopDrawable(i)
            list.add(model)
            fragments?.add(getFragmentPage(i))
        }
        replaceFragment(0)
        wv_choose.setItems(list)
        wv_choose.onWheelViewListener = object : WheelViewSelector.OnWheelViewListener() {
            override fun onSelected(selectedIndex: Int, item: String) {
                replaceFragment(selectedIndex)
            }
        }

    }

    abstract fun getTopDrawable(i: Int): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ac__hor_chart)
        energyClassificationId=intent?.getStringExtra("energyClassificationId")?:"1"
        init()
        setEnablePullToBack(false)
    }

    abstract fun init()

    abstract fun getTitlePage(i: Int): String

    abstract fun getFragmentPage(i: Int): Fragment

    abstract fun getCountPage(): Int

    fun replaceFragment(selectedIndex: Int){
        fragments?.let {
            val t = supportFragmentManager.beginTransaction()
            t.replace(R.id.ll_fragment, it[selectedIndex])
            t.commit()
        }

    }
}
