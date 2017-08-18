package com.ppandroid.app.home

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.graphics.Typeface
import android.support.v4.view.ViewPager
import android.view.View
import android.widget.TextView
import com.ppandroid.app.R
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.overview.BN_OverView
import com.ppandroid.app.bean.overview.BN_OverViewConfig
import com.ppandroid.app.home.adapter.AD_Energy
import com.ppandroid.app.home.adapter.AD_ViewPager
import com.ppandroid.app.home.overview.FG_OverViewConfig
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.app.http.OKUtils
import com.ppandroid.app.widget.wheelview.FitChartValue
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_over_view.*
import org.jetbrains.anko.find
import java.util.*


/**
 * Created by yeqinfu on 2017/8/3.
 */
class FG_OverView : FG_Base() {
    override fun fgRes(): Int = R.layout.fg_over_view


    override fun afterViews() {
       // loadOverViewConfig()
        /**添加自定义模块*/
        iv_add_model.setOnClickListener {
            startAC(FG_OverViewConfig::class.java.name)
        }

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


    private var choosed: List<BN_OverViewConfig.MessageBean.ChoosedBean>? = null

    private  var body: BN_OverView?=null

    private fun loadContent(){
        var url="user/overview/index.json"
        OKUtils.get(activity,url,BN_OverView::class.java,object :MyCallBack<BN_OverView>{
            override fun onResponse(response: BN_OverView?) {
                response?.let {
                    body=it
                    setData()
                }

            }

            override fun onError(error: ErrorBody?) {
                toast(error)
            }

        })

    }

    /**
     * 页面设定
     */
    private fun  setData() {
        choosed?.let {
            for (item in it){
                //能耗信息
                if (item.id==2){
                    var v=addEnergyInfo()
                    ll_content.addView(v)
                }else if (item.id==3){//仪表用能统计图
                    var v=addInstrumentInfomationList()
                    ll_content.addView(v)
                }else if (item.id==4){//重点设备用能统计图
                    var v=addDeviceInformationList()
                    ll_content.addView(v)
                }
            }
        }


    }

    /**
     * 重点设备用能统计图
     */
    private fun addDeviceInformationList(): View {
        var view=activity.layoutInflater.inflate(R.layout.item_energy_layout,null)
        return view
    }

    /**
     * 添加仪表用能统计图
     */
    private fun  addInstrumentInfomationList(): View {
        var view=activity.layoutInflater.inflate(R.layout.item_energy_layout,null)
        return view
    }

    /**
     * 添加能耗信息
     */
    private fun addEnergyInfo(): View {
        var view=activity.layoutInflater.inflate(R.layout.item_energy_layout,null)
        var energyBody=body?.message?.overviewConsumptionInformation
        energyBody?.let {
            var tv_totalKwh=view.find<TextView>(R.id.tv_totalKwh)
            tv_totalKwh.text=it.totalKwh
            var tv_totalMoney=view.find<TextView>(R.id.tv_totalMoney)
            tv_totalMoney.text="累计："+it.totalMoney
            var tv_carbonEmission=view.find<TextView>(R.id.tv_carbonEmission)
            tv_carbonEmission.text="碳排放："+it.carbonEmission
            var tv_compareToYesterday=view.find<TextView>(R.id.tv_compareToYesterday)
            tv_compareToYesterday.text=it.compareToYesterday
            var tv_compareToLastMonth=view.find<TextView>(R.id.tv_compareToLastMonth)
            tv_compareToLastMonth.text=it.compareToLastMonth
            var tv_energyUseThisMonth=view.find<TextView>(R.id.tv_energyUseThisMonth)
            tv_energyUseThisMonth.text=it.energyUseThisMonth
            var tv_compareToLastMonthToday=view.find<TextView>(R.id.tv_compareToLastMonthToday)
            tv_compareToLastMonthToday.text=it.compareToLastMonthToday
            var tv_cmpareToLastYearToday=view.find<TextView>(R.id.tv_cmpareToLastYearToday)
            tv_cmpareToLastYearToday.text=it.cmpareToLastYearToday
            var tv_totalMoneyMonth=view.find<TextView>(R.id.tv_totalMoneyMonth)
            tv_totalMoneyMonth.text=it.totalMoneyMonth
            /**饼图 分享用电统计*/
            var view_pager=view.find<ViewPager>(R.id.view_pager)
            var adapter=AD_Energy(activity,it.classificationInformationList)
            view_pager.adapter=adapter
            view_pager.setOnPageChangeListener(object :ViewPager.OnPageChangeListener{
                override fun onPageScrollStateChanged(state: Int) {
                }
                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                }

                override fun onPageSelected(position: Int) {

                }

            })












        }
        return view
    }

    /**
     * 加载配置信息
     */
    private fun loadOverViewConfig() {
        var url="user/overview/modular.json"
        OKUtils.get(activity,url, BN_OverViewConfig::class.java,object: MyCallBack<BN_OverViewConfig> {
            override fun onResponse(response: BN_OverViewConfig?) {
                response?.let {
                    choosed=it.message.choosed
                    loadContent()
                }
            }

            override fun onError(error: ErrorBody?) {
                toast(error?.message?:"")
            }

        })
    }



}