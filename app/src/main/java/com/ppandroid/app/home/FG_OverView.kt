package com.ppandroid.app.home

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.graphics.Typeface
import android.support.v4.view.ViewPager
import android.view.View
import com.ppandroid.app.R
import com.ppandroid.app.home.adapter.AD_ViewPager
import com.ppandroid.app.widget.wheelview.FitChartValue
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_over_view.*
import java.util.*


/**
 * Created by yeqinfu on 2017/8/3.
 */
class FG_OverView : FG_Base() {
    override fun fgRes(): Int = R.layout.fg_over_view

    override fun afterViews() {

        view?.postDelayed({
            val resources = resources
            val values = ArrayList<FitChartValue>()
            values.add(FitChartValue(40f, resources.getColor(R.color.color_01)))
            fitChart?.setValues(values)

        }, 1000)


        val res = activity.resources
        val img = BitmapFactory.decodeResource(res, R.drawable.icon_forward)
        val matrix = Matrix()
        matrix.postRotate(180f) /*翻转180度*/
        val width = img.width
        val height = img.height
        val img_a = Bitmap.createBitmap(img, 0, 0, width, height, matrix, true)
        iv_forward.setImageBitmap(img_a)
        iv_forward.visibility = View.GONE

        /**横条图viewpager*/
        var arrays_title = arrayOf(
                "今日",
                "本月",
                "本年"

        )
        /**饼图统计*/
        var viewList = ArrayList<View>()// 将要分页显示的View装入数组中
        /**饼图viewpager*/
        val lf = activity.layoutInflater
        var view1 = lf.inflate(R.layout.item_yongdian, null)
        var view2 = lf.inflate(R.layout.item_yongdian, null)
        var view3 = lf.inflate(R.layout.item_yongdian, null)
        viewList.add(view1)
        viewList.add(view2)
        viewList.add(view3)
        view_pager.adapter = AD_ViewPager(viewList, arrays_title)
        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                if (position == 0) {
                    iv_forward.visibility = View.GONE
                }
                if (position == viewList.size - 1) {
                    iv_next.visibility = View.GONE
                }
                if (position > 0 && position < viewList.size - 1) {
                    iv_forward.visibility = View.VISIBLE
                    iv_next.visibility = View.VISIBLE
                }

            }

        })



        /**区域用能统计*/
        var viewStatistics = ArrayList<View>()// 将要分页显示的View装入数组中
        var view4 = lf.inflate(R.layout.item_energy, null)
        var view5 = lf.inflate(R.layout.item_energy, null)
        var view6 = lf.inflate(R.layout.item_energy, null)
        viewStatistics.add(view4)
        viewStatistics.add(view5)
        viewStatistics.add(view6)
        view_pager_energy.adapter =  AD_ViewPager(viewStatistics, arrays_title)
        title_indicator.setViewPager(view_pager_energy)
        val density = resources.displayMetrics.density
        title_indicator.setTabSelectedTextColorResource(R.color.color_01)
        title_indicator.setIndicatorColorResource(R.color.color_01)
        title_indicator.setTypeface(null, Typeface.NORMAL)
        title_indicator.setTextSize((14 * density).toInt())

        /**重点设备用能统计*/

        var viewZhongdian = ArrayList<View>()// 将要分页显示的View装入数组中
        var view7 = lf.inflate(R.layout.item_zhongdian, null)
        var view8 = lf.inflate(R.layout.item_zhongdian, null)
        var view9 = lf.inflate(R.layout.item_zhongdian, null)
        viewZhongdian.add(view7)
        viewZhongdian.add(view8)
        viewZhongdian.add(view9)
        view_pager_zhongdian.adapter =  AD_ViewPager(viewZhongdian, arrays_title)
        title_indicator2.setViewPager(view_pager_zhongdian)
        title_indicator2.setTabSelectedTextColorResource(R.color.color_01)
        title_indicator2.setIndicatorColorResource(R.color.color_01)
        title_indicator2.setTypeface(null, Typeface.NORMAL)
        title_indicator2.setTextSize((14 * density).toInt())

        /**仪表用电统计*/

        var viewsyibiao = ArrayList<View>()// 将要分页显示的View装入数组中
        var view10 = lf.inflate(R.layout.item_zhongdian, null)
        var view11= lf.inflate(R.layout.item_zhongdian, null)
        var view12= lf.inflate(R.layout.item_zhongdian, null)
        viewsyibiao.add(view10)
        viewsyibiao.add(view11)
        viewsyibiao.add(view12)

        view_pager_3.adapter =  AD_ViewPager(viewsyibiao, arrays_title)
        title_indicator3.setViewPager(view_pager_3)
        title_indicator3.setTabSelectedTextColorResource(R.color.color_01)
        title_indicator3.setIndicatorColorResource(R.color.color_01)
        title_indicator3.setTypeface(null, Typeface.NORMAL)
        title_indicator3.setTextSize((14 * density).toInt())




    }




}