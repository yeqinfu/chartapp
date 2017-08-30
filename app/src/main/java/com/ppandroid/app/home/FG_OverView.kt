package com.ppandroid.app.home

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.support.v4.view.ViewPager
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.ppandroid.app.R
import com.ppandroid.app.bean.ET_Base
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.overview.BN_OverView
import com.ppandroid.app.bean.overview.BN_OverViewConfig
import com.ppandroid.app.home.adapter.AD_AreaEnergy
import com.ppandroid.app.home.adapter.AD_Energy
import com.ppandroid.app.home.adapter.AD_Instrument
import com.ppandroid.app.home.adapter.AD_Zhongdian
import com.ppandroid.app.home.overview.FG_OverViewConfig
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.app.utils.DensityUtil
import com.ppandroid.app.utils.Utils_Dialog
import com.ppandroid.app.widget.common.PagerSlidingTab
import com.ppandroid.app.widget.graphical.demoview.DountChart01View
import com.ppandroid.app.widget.wheelview.FitChartHalf
import com.ppandroid.app.widget.wheelview.FitChartValue
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_over_view.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.find
import java.util.*
import java.util.regex.Pattern


/**
 * Created by yeqinfu on 2017/8/3.
 */
class FG_OverView : FG_Base() {
    override fun fgRes(): Int = R.layout.fg_over_view


    override fun afterViews() {
        Utils_Dialog.showLoading(activity)
        loadOverViewConfig()
        isNeedEventBus = true

        refreshLayout.setOnRefreshListener {
            loadOverViewConfig()

        }
        refreshLayout.isEnableLoadmore=false
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: ET_OverView) {
        if (event.taskId === ET_OverView.TASKID_REFRESH) {
            loadOverViewConfig()
        }

    }

    class ET_OverView(taskId: Int) : ET_Base(taskId) {
        companion object {
            /**刷新 */
            val TASKID_REFRESH = UUID.randomUUID().hashCode()
        }

    }


    private var choosed: List<BN_OverViewConfig.MessageBean.ChoosedBean>? = null

    private var body: BN_OverView? = null

    private fun loadContent() {
        var url = "user/overview/index.json"
        Http.get(activity, url, BN_OverView::class.java, object : MyCallBack<BN_OverView> {
            override fun onResponse(response: BN_OverView?) {
                response?.let {
                    body = it
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
    private fun setData() {
        ll_content.removeAllViews()
        choosed?.let {
            for (item in it) {
                //能耗信息
                if (item.id == 2) {
                    var v = addEnergyInfo()
                    ll_content.addView(v)
                } else if (item.id == 3) {//仪表用能统计图
                    var v = addInstrumentInfomationList()
                    ll_content.addView(v)
                } else if (item.id == 4) {//重点设备用能统计图
                    var v = addDeviceInformationList()
                    ll_content.addView(v)
                }
            }
            var view = addModule()
            ll_content.addView(view)
            sv.smoothScrollTo(0, 0)
        }


    }

    private fun addModule(): View {
        var view = activity.layoutInflater.inflate(R.layout.layout_add_module, null)
        var iv_add_model = view.find<ImageView>(R.id.iv_add_model)
        iv_add_model.setOnClickListener {
            startAC(FG_OverViewConfig::class.java.name)
        }

        return view
    }

    /**
     * 重点设备用能统计图
     */
    private fun addDeviceInformationList(): View {
        var view = activity.layoutInflater.inflate(R.layout.item_zhongdian_layout, null)
        var zhongdianBody = body?.message?.deviceInformationList
        zhongdianBody?.let {
            /**添加仪表用能统计图*/
            var adapter = AD_Zhongdian(activity, it)
            var view_pager_zhongdian = view.find<ViewPager>(R.id.view_pager_zhongdian)
            //为ViewPager设置高度
            val params = view_pager_zhongdian.getLayoutParams()
            params.height = DensityUtil.dip2px(activity, 65f) * (it[0].deviceKwhMapList.size-1)
            view_pager_zhongdian.setLayoutParams(params)
            view_pager_zhongdian.adapter = adapter
            var title_indicator_zhongdian = view.find<PagerSlidingTab>(R.id.title_indicator_zhongdian)
            val density = resources.displayMetrics.density
            title_indicator_zhongdian.setViewPager(view_pager_zhongdian)
            title_indicator_zhongdian.setTabSelectedTextColorResource(R.color.color_01)
            title_indicator_zhongdian.setIndicatorColorResource(R.color.color_01)
            title_indicator_zhongdian.setTypeface(null, Typeface.NORMAL)
            title_indicator_zhongdian.setTextSize((14 * density).toInt())
        }


        return view
    }

    /**
     * 添加仪表用能统计图
     */
    private fun addInstrumentInfomationList(): View {
        var view = activity.layoutInflater.inflate(R.layout.item_instrument_layout, null)

        var instrumentBody = body?.message?.instrumentInfomationList
        instrumentBody?.let {
            /**添加仪表用能统计图*/
            var adapter = AD_Instrument(activity, it)
            var view_pager_instruemnt = view.find<ViewPager>(R.id.view_pager_instruemnt)
            //为ViewPager设置高度
            val params = view_pager_instruemnt.getLayoutParams()
            params.height = DensityUtil.dip2px(activity, 65f) * (it[0].instrumentMapList.size-1)
            view_pager_instruemnt.setLayoutParams(params)
            view_pager_instruemnt.adapter = adapter
            var title_indicator2 = view.find<PagerSlidingTab>(R.id.title_indicator2)
            val density = resources.displayMetrics.density
            title_indicator2.setViewPager(view_pager_instruemnt)
            title_indicator2.setTabSelectedTextColorResource(R.color.color_01)
            title_indicator2.setIndicatorColorResource(R.color.color_01)
            title_indicator2.setTypeface(null, Typeface.NORMAL)
            title_indicator2.setTextSize((14 * density).toInt())
        }
        return view
    }

    /**
     * 添加能耗信息
     */
    private fun addEnergyInfo(): View {
        var view = activity.layoutInflater.inflate(R.layout.item_energy_layout, null)
        var energyBody = body?.message?.overviewConsumptionInformation
        energyBody?.let {
            var tv_totalKwh = view.find<TextView>(R.id.tv_totalKwh)
            val regEx = "kwh$|万kwh$"
            val p = Pattern.compile(regEx)
            val m = p.matcher(it.totalKwh)
            tv_totalKwh.text = m.replaceAll("")
            var tv_unit = view.find<TextView>(R.id.tv_unit)
            m.reset()
            if (m.find()) {
                tv_unit.text = m.group()
            }
            var fitChart=view.find<FitChartHalf>(R.id.fitChart)
            fitChart.setWillNotDraw(false)
            view?.postDelayed({
                val resources = resources
                val values = ArrayList<FitChartValue>()
                values.add(FitChartValue(40f, resources.getColor(R.color.color_01)))
                fitChart?.setValues(values)
            }, 1000)


            var tv_totalMoney = view.find<TextView>(R.id.tv_totalMoney)
            tv_totalMoney.text = "累计：" + it.totalMoney
            var tv_carbonEmission = view.find<TextView>(R.id.tv_carbonEmission)
            tv_carbonEmission.text = "碳排放：" + it.carbonEmission

            var tv_compareToYesterday = view.find<TextView>(R.id.tv_compareToYesterday)
            tv_compareToYesterday.text =it.compareToYesterday
            setUpDownIcon(tv_compareToYesterday, it.compareToYesterday)

            var tv_compareToLastMonth = view.find<TextView>(R.id.tv_compareToLastMonth)
            tv_compareToLastMonth.text = it.compareToLastMonth
            setUpDownIcon(tv_compareToLastMonth, it.compareToLastMonth)

            var tv_energyUseThisMonth = view.find<TextView>(R.id.tv_energyUseThisMonth)
            tv_energyUseThisMonth.text = it.energyUseThisMonth

            var tv_compareToLastMonthToday = view.find<TextView>(R.id.tv_compareToLastMonthToday)
            tv_compareToLastMonthToday.text = it.compareToLastMonthToday
            setUpDownIcon(tv_compareToLastMonthToday, it.compareToLastMonthToday)

            var tv_cmpareToLastYearToday = view.find<TextView>(R.id.tv_cmpareToLastYearToday)
            tv_cmpareToLastYearToday.text = it.cmpareToLastYearToday
            setUpDownIcon(tv_cmpareToLastYearToday, it.cmpareToLastYearToday)

            var tv_totalMoneyMonth = view.find<TextView>(R.id.tv_totalMoneyMonth)
            tv_totalMoneyMonth.text = it.totalMoneyMonth

            setUpDownIcon(tv_cmpareToLastYearToday, it.cmpareToLastYearToday)
            /**饼图 分享用电统计*/

            val res = activity.resources
            val img = BitmapFactory.decodeResource(res, R.drawable.icon_forward)
            val matrix = Matrix()
            matrix.postRotate(180f) /*翻转180度*/
            val width = img.width
            val height = img.height
            val img_a = Bitmap.createBitmap(img, 0, 0, width, height, matrix, true)
            var iv_forward = view.find<ImageView>(R.id.iv_forward)
            var iv_next = view.find<ImageView>(R.id.iv_next)
            iv_forward.setImageBitmap(img_a)
            iv_forward.visibility = View.GONE


            var view_pager = view.find<ViewPager>(R.id.view_pager)
            var adapter = AD_Energy(activity, it.classificationInformationList)
            view_pager.adapter = adapter
            view_pager.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) {
                }

                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                }

                override fun onPageSelected(position: Int) {
                    var v_dount_view=adapter.views[position].find<DountChart01View>(R.id.v_dount_view)
                    v_dount_view.startAnim()
                    if (position == 0) {
                        iv_forward.visibility = View.GONE
                    }
                    if (position == it.classificationInformationList.size - 1) {
                        iv_next.visibility = View.GONE
                    }
                    if (position > 0 && position < it.classificationInformationList.size - 1) {
                        iv_forward.visibility = View.VISIBLE
                        iv_next.visibility = View.VISIBLE
                    }

                    setEnergyInfo(view, it.classificationInformationList[position].classificationKwhMapList)
                }

            })
            setEnergyInfo(view, it.classificationInformationList[0].classificationKwhMapList)

            /**区域用电统计*/
            var view_pager_energy = view.find<ViewPager>(R.id.view_pager_energy)
            view_pager_energy.adapter = AD_AreaEnergy(activity, it.areaInformationList)
            var title_indicator = view.find<PagerSlidingTab>(R.id.title_indicator)
            title_indicator.setViewPager(view_pager_energy)
            val density = resources.displayMetrics.density
            title_indicator.setTabSelectedTextColorResource(R.color.color_01)
            title_indicator.setIndicatorColorResource(R.color.color_01)
            title_indicator.setTypeface(null, Typeface.NORMAL)
            title_indicator.textSize = (14 * density).toInt()
        }
        return view
    }

    private fun setUpDownIcon(textView: TextView, str: String?) {
        var drawable: Drawable = if (str?.startsWith("-") == true) {
            resources.getDrawable(R.drawable.icon_down_yellow)
        } else {
            resources.getDrawable(R.drawable.icon_up_blue)
        }
        drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
        textView.setCompoundDrawables(null, null, drawable, null)
    }

    private fun setEnergyInfo(view: View, list: List<BN_OverView.MessageBean.OverviewConsumptionInformationBean.ClassificationInformationListBean.ClassificationKwhMapListBean>) {
        var tv_info01 = view.find<TextView>(R.id.tv_info01)
        tv_info01.text = list[0].key
        var tv_value01 = view.find<TextView>(R.id.tv_value01)
        tv_value01.text = list[0].value

        var tv_info02 = view.find<TextView>(R.id.tv_info02)
        tv_info02.text = list[1].key
        var tv_value02 = view.find<TextView>(R.id.tv_value02)
        tv_value02.text = list[1].value

        var tv_info03 = view.find<TextView>(R.id.tv_info03)
        tv_info03.text = list[2].key
        var tv_value03 = view.find<TextView>(R.id.tv_value03)
        tv_value03.text = list[2].value

        var tv_info04 = view.find<TextView>(R.id.tv_info04)
        tv_info04.text = list[3].key
        var tv_value04 = view.find<TextView>(R.id.tv_value04)
        tv_value04.text = list[3].value

        var tv_info05 = view.find<TextView>(R.id.tv_info05)
        tv_info05.text = list[4].key
        var tv_value05 = view.find<TextView>(R.id.tv_value05)
        tv_value05.text = list[4].value

    }

    /**
     * 加载配置信息
     */
    private fun loadOverViewConfig() {
        var url = "user/overview/modular.json"
        Http.get(activity, url, BN_OverViewConfig::class.java, object : MyCallBack<BN_OverViewConfig> {
            override fun onResponse(response: BN_OverViewConfig?) {
                Utils_Dialog.disMissLoading()
                refreshLayout.finishRefresh()
                response?.let {
                    choosed = it.message.choosed
                    loadContent()
                }
            }

            override fun onError(error: ErrorBody?) {
                refreshLayout.finishRefresh()
                Utils_Dialog.disMissLoading()
                toast(error?.message ?: "")
            }

        })
    }


}