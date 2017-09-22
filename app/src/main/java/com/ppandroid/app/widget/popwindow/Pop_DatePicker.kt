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
import com.ppandroid.app.utils.DimensUtils
import com.ppandroid.app.widget.popwindow.basepopup.BasePopupWindow
import com.ppandroid.app.widget.wv.LoopView
import org.jetbrains.anko.find

/**
 * Created by yeqinfu on 2017/6/27.
 */

class Pop_DatePicker(context: Activity, data:ArrayList<String>): BasePopupWindow(context, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT) {
    var tv_title: TextView?=null
    interface IChooseListener{
        fun choose(item:String)
    }
    var listener:IChooseListener?=null

    init {
        isNeedgreybg=true
        popupGravity= Gravity.BOTTOM
        tv_title=popupWindowView.find<TextView>(R.id.tv_title)
        var tv_save=popupWindowView.find<TextView>(R.id.tv_save)
        var tv_cancel=popupWindowView.find<TextView>(R.id.tv_cancel)
        var lv_view=popupWindowView.find<LoopView>(R.id.lv_view)
        lv_view.setNotLoop()
        lv_view.setArrayList(data)

        tv_cancel.setOnClickListener {
            dismiss()
        }
        tv_save.setOnClickListener {
            listener?.choose(data[lv_view.selectedItem])
        }

    }

    fun setTitle(title :String ){
        tv_title?.text=title
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