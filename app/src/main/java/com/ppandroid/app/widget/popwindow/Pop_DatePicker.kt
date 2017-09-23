package com.ppandroid.app.widget.popwindow

import android.app.Activity
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.OvershootInterpolator
import android.view.animation.TranslateAnimation
import android.widget.TextView
import com.ppandroid.app.R
import com.ppandroid.app.utils.DebugLog
import com.ppandroid.app.utils.DimensUtils
import com.ppandroid.app.widget.WheelView
import com.ppandroid.app.widget.popwindow.basepopup.BasePopupWindow
import org.jetbrains.anko.find
import java.util.*

/**
 * Created by yeqinfu on 2017/6/27.
 * 简单日期选择器
 * type=0 年月日选择器
 * type=1 年月选择器
 * type=2 年选择器
 */

class Pop_DatePicker(context: Activity, type: Int) : BasePopupWindow(context, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT) {
    var tv_title: TextView? = null
    var type = type
    private val DEFAULT_MIN_YEAR = 1900
    internal var yearList: MutableList<String> = ArrayList()
    internal var monthList: MutableList<String> = ArrayList()
    internal var dayList: MutableList<String> = ArrayList()
    var wv_year: WheelView? = null
    var wv_month: WheelView? = null
    var wv_day: WheelView? = null
    private var yearPos = 0
    private var monthPos = 0
    private var dayPos = 0
    private var yearStr = ""
    private var monthStr = ""
    private var dayStr = "01"
    interface IChooseListener {
        fun choose(year: String, month: String, day: String)
    }

    var listener: IChooseListener? = null

    init {

        isNeedgreybg = false
        popupGravity = Gravity.BOTTOM
        tv_title = popupWindowView.find<TextView>(R.id.tv_title)
        var tv_save = popupWindowView.find<TextView>(R.id.tv_save)
        var tv_cancel = popupWindowView.find<TextView>(R.id.tv_cancel)
        wv_year = popupWindowView.find<WheelView>(R.id.wv_year)
        wv_month = popupWindowView.find<WheelView>(R.id.wv_month)
        wv_day = popupWindowView.find<WheelView>(R.id.wv_day)
        when (type) {
            0 -> {
                wv_year?.visibility=View.VISIBLE
                wv_month?.visibility=View.VISIBLE
                wv_day?.visibility=View.VISIBLE
            }
            1 -> {
                wv_year?.visibility=View.VISIBLE
                wv_month?.visibility=View.VISIBLE
                wv_day?.visibility=View.GONE
            }
            else -> {
                wv_year?.visibility=View.VISIBLE
                wv_month?.visibility=View.GONE
                wv_day?.visibility=View.GONE
            }
        }

        val c = Calendar.getInstance()
        var yearCount = c.get(Calendar.YEAR) - DEFAULT_MIN_YEAR
        for (i in 0 until yearCount) {
            yearList.add(format2LenStr(DEFAULT_MIN_YEAR + i+1))
        }
        for (j in 0..11) {
            monthList.add(format2LenStr(j + 1))
        }
        wv_year?.setItems(yearList)
        wv_year?.setSeletion(yearCount-1)
        yearStr=(DEFAULT_MIN_YEAR+yearCount).toString()
        wv_month?.setItems(monthList)
        val calendar = Calendar.getInstance()
        wv_month?.setSeletion(calendar.get(Calendar.MONTH))
        monthStr=format2LenStr(calendar.get(Calendar.MONTH)+1)
        initDayPickerView()

        wv_year?.onWheelViewListener = object : WheelView.OnWheelViewListener() {
            override fun onSelected(selectedIndex: Int, item: String?) {
                yearPos = DEFAULT_MIN_YEAR + selectedIndex
                yearStr=item?:""
                initDayPickerView()
            }
        }
        wv_month?.onWheelViewListener = object : WheelView.OnWheelViewListener() {
            override fun onSelected(selectedIndex: Int, item: String?) {
                monthPos = selectedIndex
                monthStr=item?:""
                initDayPickerView()
            }
        }


        tv_cancel.setOnClickListener {
            dismiss()
        }
        tv_save.setOnClickListener {
            listener?.let {
                it.choose(yearStr, monthStr,dayStr)
            }


        }

    }


    /**
     * Init day item
     */
    private fun initDayPickerView() {
        val dayMaxInMonth: Int
        val calendar = Calendar.getInstance()
        dayList = ArrayList()

        DebugLog.d("======initDayPickerView=======")
        calendar.set(Calendar.YEAR, DEFAULT_MIN_YEAR + yearPos)
        calendar.set(Calendar.MONTH, monthPos-1)

        //get max day in month
        dayMaxInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

        for (i in 0 until dayMaxInMonth) {
            dayList.add(format2LenStr(i + 1))
        }
        wv_day?.setItems(dayList)
        wv_day?.onWheelViewListener=object : WheelView.OnWheelViewListener(){
            override fun onSelected(selectedIndex: Int, item: String?) {
                super.onSelected(selectedIndex, item)
                dayPos=selectedIndex
                dayStr=item?:""
            }

        }
    }

    /**
     * Transform int to String with prefix "0" if less than 10
     * @param num
     * @return
     */
    private fun format2LenStr(num: Int): String {
        return if (num < 10) "0" + num else num.toString()
    }

    fun setTitle(title: String) {
        tv_title?.text = title
    }


    override fun onCreatePopupView(): View {
        return createPopupById(R.layout.pop_date_picker)
    }

    override fun initAnimaView(): View {
        return findViewById(R.id.ll_content)
    }

    override fun initShowAnimation(): Animation {
        val translateAnimation = TranslateAnimation(0f, 0f, (DimensUtils.dipToPx(context, 350f)).toFloat(), 0f)
        translateAnimation.duration = 450
        translateAnimation.interpolator = OvershootInterpolator(1f)
        return translateAnimation
    }

    override fun getClickToDismissView(): View {
        return popupWindowView
    }

    override fun initExitAnimation(): Animation {
        val translateAnimation = TranslateAnimation(0f, 0f, 0f, (DimensUtils.dipToPx(context, 350f)).toFloat())
        translateAnimation.duration = 450
        translateAnimation.interpolator = OvershootInterpolator(-4f)
        return translateAnimation
    }


}