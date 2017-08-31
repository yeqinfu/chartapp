package com.ppandroid.app.home.mine.energyanalysis

import android.app.Activity
import android.support.v4.app.FragmentManager
import android.support.v4.view.PagerAdapter
import com.ppandroid.app.home.mine.adapter.AD_BaseAnalysis

/**
 * Created by yeqinfu on 2017/8/31.
 * 分项用电
 */
class FG_CateAnalysis:FG_BaseAnalysis(){
    override fun getAdapter(): PagerAdapter=AD_Cate(activity,childFragmentManager)

    override fun getTitle(): String="分项用电"

    class AD_Cate(ac: Activity,  fm: FragmentManager) :AD_BaseAnalysis(ac,fm){
        override fun getContentFragment(): FG_BaseAnlysisPage {
            return FG_CatePage()
        }
    }

}