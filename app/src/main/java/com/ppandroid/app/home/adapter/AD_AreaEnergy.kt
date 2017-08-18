package com.ppandroid.app.home.adapter

import android.app.Activity
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import com.ppandroid.app.R
import com.ppandroid.app.bean.overview.BN_OverView

/**
 * Created by yeqinfu on 2017/8/11.
 */
class AD_AreaEnergy(ac: Activity, list: List<BN_OverView.MessageBean.OverviewConsumptionInformationBean.AreaInformationListBean>): PagerAdapter(){
    var views = ArrayList<View>()// 将要分页显示的View装入数组中
    var list: List<BN_OverView.MessageBean.OverviewConsumptionInformationBean.AreaInformationListBean>?=null
    var ac: Activity?=null
    init {
        this.ac=ac
        this.list=list
        /**viewpager*/
        val lf = ac.layoutInflater
        for (item in list){
            var view1 = lf.inflate(R.layout.item_energy, null)
            views.add(view1)
        }
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
        return ""
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        container.addView(views[position])
        return views[position]
    }

}