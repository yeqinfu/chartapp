package com.ppandroid.app

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import com.ppandroid.app.base.AC_ContentFG
import com.ppandroid.app.home.FG_Center
import com.ppandroid.app.home.FG_Devices
import com.ppandroid.app.home.FG_News
import com.ppandroid.app.home.FG_OverView
import com.ppandroid.im.FG_Mine
import com.ppandroid.im.base.AC_Base
import kotlinx.android.synthetic.main.activity_main.*


class AC_Main : AC_Base() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setEnablePullToBack(false)



        rg_main_tab.setOnCheckedChangeListener { _, i ->
            when (i) {
                R.id.tab_01 -> replaceTab(0)
                R.id.tab_02 -> replaceTab(1)
                R.id.tab_03 -> replaceTab(2)
                R.id.tab_04 -> replaceTab(3)
            }
        }
        replaceTab(0)

        tv_center.setOnClickListener {
           startAC(FG_Center::class.java.name)
        }
    }





    private var fragementNews: Fragment? = null
    private var fragementOver: Fragment? = null
    private var fragementDevices: Fragment? = null
    private var fragementMine: Fragment? = null
    private fun replaceTab(tabId: Int) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        hideFragments(fragmentTransaction)
        when (tabId) {
            0 -> {
                if (fragementNews == null) {
                    fragementNews = FG_News()
                    fragmentTransaction.add(R.id.ll_content, fragementNews)
                } else {
                    fragmentTransaction.show(fragementNews)
                }
            }
            1 -> {
                if (fragementOver == null) {
                    fragementOver = FG_OverView()
                    fragmentTransaction.add(R.id.ll_content, fragementOver)
                } else {
                    fragmentTransaction.show(fragementOver)
                }
            }
            2 -> {
                if (fragementDevices == null) {
                    fragementDevices = FG_Devices()
                    fragmentTransaction.add(R.id.ll_content, fragementDevices)
                } else {
                    fragmentTransaction.show(fragementDevices)
                }
            }
            3 -> {
                if (fragementMine == null) {
                    fragementMine = FG_Mine()
                    fragmentTransaction.add(R.id.ll_content, fragementMine)
                } else {
                    fragmentTransaction.show(fragementMine)
                }
            }
        }

        // Commit the transaction
        fragmentTransaction.commit()

    }

    // 当fragment已被实例化，就隐藏起来
    fun hideFragments(ft: FragmentTransaction) {
        if (fragementNews != null)
            ft.hide(fragementNews)
        if (fragementOver != null)
            ft.hide(fragementOver)
        if (fragementDevices != null)
            ft.hide(fragementDevices)
        if (fragementMine != null)
            ft.hide(fragementMine)
    }

    private fun startAC(fragment: String) {
        var it = AC_ContentFG.createIntent(this, fragment)
        startActivity(it)
    }


}
