package com.ppandroid.app.home

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import android.view.ViewGroup
import com.ppandroid.app.R
import com.ppandroid.app.bean.BN_Vertical
import com.ppandroid.app.widget.wheelview.FitChartHalf
import com.ppandroid.app.widget.wheelview.FitChartValue
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_over_view.*
import org.jetbrains.anko.find
import java.util.*




/**
 * Created by yeqinfu on 2017/8/3.
 */
class FG_OverView :FG_Base(){
    override fun fgRes():Int= R.layout.fg_over_view

    override fun afterViews() {
        var list=ArrayList<BN_Vertical>()
        for (i in 0..23){
            var bn=BN_Vertical()
            if (i%2==0){
                if (i<10){
                    bn.bottomText="0"+i
                }else{
                    bn.bottomText=""+i
                }

            }
            bn.totalHeight=100f
            var r=Random()
            bn.realHeight=r.nextFloat()*100f
            list.add(bn)
        }

        v_multiple_view.dataSet=list
        btn_multi.setOnClickListener {
            v_multiple_view.startAnim()

        }

        val fitChart = view?.find<FitChartHalf>(R.id.fitChart)
        fitChart?.let {
            it.minValue=0f
            it.maxValue=100f
        }

        view?.postDelayed({
            val resources = resources
            val values = ArrayList<FitChartValue>()
            values.add(FitChartValue(40f, resources.getColor(R.color.color_01)))
            fitChart?.setValues(values)

        },1000)



        btn_hp.setOnClickListener {
            v_hp.startAnim()
        }


        val res = activity.resources
        val img = BitmapFactory.decodeResource(res, R.drawable.icon_forward)
        val matrix = Matrix()
        matrix.postRotate(180f) /*翻转180度*/
        val width = img.width
        val height = img.height
        val img_a = Bitmap.createBitmap(img, 0, 0, width, height, matrix, true)
        iv_forward.setImageBitmap(img_a)
        iv_forward.visibility=View.GONE


        val lf = activity.layoutInflater
        var view1 = lf.inflate(R.layout.item_yongdian, null)
        var view2 = lf.inflate(R.layout.item_yongdian, null)
        var view3 = lf.inflate(R.layout.item_yongdian, null)
        viewList.add(view1)
        viewList.add(view2)
        viewList.add(view3)
        view_pager.adapter=pagerAdapter
        view_pager.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                if (position==0){
                    iv_forward.visibility=View.GONE
                }
                if (position==viewList.size-1){
                    iv_next.visibility=View.GONE
                }
                if (position>0&&position<viewList.size-1){
                    iv_forward.visibility=View.VISIBLE
                    iv_next.visibility=View.VISIBLE
                }

            }

        })
    }

    var viewList = ArrayList<View>()// 将要分页显示的View装入数组中

    var pagerAdapter: PagerAdapter = object : PagerAdapter() {

        override fun isViewFromObject(arg0: View, arg1: Any): Boolean {

            return arg0 === arg1
        }

        override fun getCount(): Int {
            return viewList.size
        }

        override fun destroyItem(container: ViewGroup, position: Int,
                                 `object`: Any) {
            container.removeView(viewList.get(position))

        }

        override fun getItemPosition(`object`: Any?): Int {

            return super.getItemPosition(`object`)
        }



        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            container.addView(viewList[position])
            return viewList[position]
        }

    }


}