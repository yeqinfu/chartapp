package com.ppandroid.app.home.mine.energyanalysis

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.view.PagerAdapter
import com.ppandroid.app.home.mine.adapter.AD_BaseAnalysis

/**
 * Created by yeqinfu on 2017/8/31.
 * 分项用电
 */
class FG_CateAnalysis:FG_BaseAnalysis(){

    override fun init() {

    }

    override fun getAdapter(): PagerAdapter{
        return AD_Cate(activity,childFragmentManager,parentId)
    }

    override fun getTitle(): String="分项用电"

    class AD_Cate(ac: Activity,  fm: FragmentManager,parentId:String) :AD_BaseAnalysis(ac,fm){
        var parentId:String?=null
        init {
            this.parentId=parentId
            initFragment()
        }
        override fun getBundle(index:Int): Bundle {
            return FG_BaseAnlysisPage.createBundle(index,parentId?:"-1")

        }
        override fun getContentFragment(): FG_BaseAnlysisPage {
            return FG_CatePage()
        }
    }

}