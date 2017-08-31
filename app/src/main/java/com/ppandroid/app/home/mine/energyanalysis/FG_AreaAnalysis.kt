package com.ppandroid.app.home.mine.energyanalysis

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.view.PagerAdapter
import com.ppandroid.app.home.mine.adapter.AD_BaseAnalysis

/**
 * Created by yeqinfu on 2017/8/31.
 * 区域用电
 */
class FG_AreaAnalysis : FG_BaseAnalysis(){

    override fun init() {

    }

    override fun getAdapter(): PagerAdapter =AD_Cate(activity,childFragmentManager,parentId)

    override fun getTitle(): String="区域用电"

    class AD_Cate(ac: Activity, fm: FragmentManager, parentId:String) : AD_BaseAnalysis(ac,fm){
        var parentId=parentId
        override fun getBundle(index:Int): Bundle {
            if (parentId==null){
                //TODO 奇怪，不应该null的
                parentId="-1"
            }
            return FG_BaseAnlysisPage.createBundle(index, parentId)

        }
        override fun getContentFragment(): FG_BaseAnlysisPage {
            return FG_AreaPage()
        }
    }

}