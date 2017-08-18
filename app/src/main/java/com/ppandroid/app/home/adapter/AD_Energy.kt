package com.ppandroid.app.home.adapter

import android.app.Activity
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ppandroid.app.R
import com.ppandroid.app.bean.overview.BN_OverView.MessageBean.OverviewConsumptionInformationBean.ClassificationInformationListBean
import org.jetbrains.anko.find

/**
 * Created by yeqinfu on 2017/8/11.
 */
class AD_Energy(ac: Activity, list: List<ClassificationInformationListBean>): PagerAdapter(){
    var views = ArrayList<View>()// 将要分页显示的View装入数组中
    var list: List<ClassificationInformationListBean>?=null
    var ac:Activity?=null
    init {
        this.ac=ac
        this.list=list
        /**饼图viewpager*/
        val lf = ac.layoutInflater
        for (item in list){
            var view1 = lf.inflate(R.layout.item_yongdian, null)
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
        var tv_totalKwh=views[position].find<TextView>(R.id.tv_totalKwh)
        tv_totalKwh.text= list?.get(position)?.totalKwh?:""
        container.addView(views[position])
        return views[position]
    }

}