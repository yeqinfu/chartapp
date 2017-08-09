package com.ppandroid.app.home

import android.animation.ObjectAnimator
import com.ppandroid.app.R
import com.ppandroid.app.home.adapter.AD_ExList
import com.ppandroid.app.home.adapter.BN_Ex
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_devices.*


/**
 * Created by yeqinfu on 2017/8/3.
 */
class FG_Devices :FG_Base(){
    override fun fgRes():Int= R.layout.fg_devices
    private val dataset = HashMap<BN_Ex,List<String>>()
    private val parentList =ArrayList<BN_Ex>()
    private val childrenList1 = ArrayList<String>()
    private val childrenList2 = ArrayList<String>()
    private val childrenList3 = ArrayList<String>()

    override fun afterViews() {
        initialData()
        val adapter= AD_ExList(dataset,parentList,activity)
        lv_ex.setAdapter(adapter)
        lv_ex.setOnGroupCollapseListener {var1->
            parentList[var1].isOpen=false
            adapter.notifyDataSetChanged()
            val animator = ObjectAnimator.ofFloat(iv_setting, "rotation", 0f, 180f)
            animator.duration=(500)
            animator.start()
        }
        lv_ex.setOnGroupExpandListener {var1->
            parentList[var1].isOpen=true
            adapter.notifyDataSetChanged()
            val animator = ObjectAnimator.ofFloat(iv_setting, "rotation", 0f, 180f)
            animator.duration=(500)
            animator.start()
        }

    }

    private fun initialData() {
        parentList.add(BN_Ex(false,"高压离心式空压机"))
        parentList.add(BN_Ex(false,"空调系统-冷却塔"))
        parentList.add(BN_Ex(false,"空调系统-冷冻泵"))
        childrenList1.add(parentList[0].title + "-" + "first")
        childrenList1.add(parentList[0].title + "-" + "second")
        childrenList1.add(parentList[0].title+ "-" + "third")
        childrenList2.add(parentList[1].title + "-" + "first")
        childrenList2.add(parentList[1].title + "-" + "second")
        childrenList2.add(parentList[1].title+ "-" + "third")
        childrenList3.add(parentList[2].title+ "-" + "first")
        childrenList3.add(parentList[2].title+ "-" + "second")
        childrenList3.add(parentList[2].title+ "-" + "third")
        dataset.put(parentList[0], childrenList1)
        dataset.put(parentList[1], childrenList2)
        dataset.put(parentList[2], childrenList3)
    }


}