/*
 * Created by yeqinfu on 17-12-6 下午2:52
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.test

import android.widget.ArrayAdapter
import com.ppandroid.app.R
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_test_page.*

/**
 * Created by yeqinfu on 2017/12/6.
 */

class FG_TestPage : FG_Base() {
    override fun fgRes(): Int {
        return R.layout.fg_test_page
    }

    override fun afterViews() {

        val list = ArrayList<String>()
        for (i in 0 until 50) {
            list.add("item" + i)
        }
        val adapter = ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1, list)
        lv_list2.adapter=adapter
    }
}
