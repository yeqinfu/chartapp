/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home.adapter

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import java.util.*

/**
 * Created by yeqinfu on 2017/8/11.
 */
class AD_ViewPager(views: ArrayList<View>,arrays_title: Array<String>):PagerAdapter(){
    var views = ArrayList<View>()// 将要分页显示的View装入数组中
    private var arrays_title: Array<String>? = null
    init {
        this.views=views
        this.arrays_title=arrays_title
    }

    override fun isViewFromObject(arg0: View, arg1: Any): Boolean {

        return arg0 === arg1
    }

    override fun getCount(): Int {
        return views.size
    }

    override fun destroyItem(container: ViewGroup, position: Int,
                             `object`: Any) {
        container.removeView(views.get(position))

    }

    override fun getItemPosition(`object`: Any?): Int {

        return super.getItemPosition(`object`)
    }

    override fun getPageTitle(position: Int): CharSequence {
        return arrays_title?.get(position) ?: ""
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        container.addView(views[position])
        return views[position]
    }

}