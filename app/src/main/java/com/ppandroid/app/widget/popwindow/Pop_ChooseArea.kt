/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.widget.popwindow

import android.app.Activity
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.OvershootInterpolator
import android.view.animation.TranslateAnimation
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import com.ppandroid.app.R
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.mine.systemsetting.BN_Area
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.app.utils.DimensUtils
import com.ppandroid.app.utils.toast.ToastUtil
import com.ppandroid.app.widget.popwindow.basepopup.BasePopupWindow
import org.jetbrains.anko.find


/**
 * Created by yeqinfu on 2017/6/27.
 */

class Pop_ChooseArea(context: Activity) : BasePopupWindow(context, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT) {

    interface IChooseListener {
        fun choose(item: String, provinceId: String, cityId: String, contryId: String)
    }

    var listener: IChooseListener? = null

    private var provinceId: String = ""
    private var provinceName: String = ""
    private var cityId: String = ""
    private var cityName: String = ""
    private var contryId: String = ""
    private var contryName: String = ""

    private var currentStep = 0//0 省选择 1 市选择 2 县选择

    var lv_list: ListView? = null

    init {
        isNeedgreybg = false
        popupGravity = Gravity.BOTTOM
        var tv_back = popupWindowView.find<ImageView>(R.id.tv_back)
        var tv_close = popupWindowView.find<TextView>(R.id.tv_close)
        lv_list = popupWindowView.find<ListView>(R.id.lv_list)
        tv_close.setOnClickListener {
            dismiss()
        }
        tv_back.setOnClickListener {
            if (currentStep == 0) {
                //do nothing
            } else if (currentStep==1) {
                //返回省级选择
                loadContent("")
                currentStep--
            }else {
                //返回市级选择
                loadContent(provinceId)
                currentStep--
            }
        }
        lv_list?.setOnItemClickListener { _, _, i, _ ->
            when (currentStep) {
                0 -> {
                    currentStep++
                    provinceId = provinceList?.get(i)?.id.toString()
                    provinceName = provinceList?.get(i)?.name.toString()
                    loadContent(provinceId)
                }
                1 -> {
                    currentStep++
                    cityId = cityList?.get(i)?.id.toString()
                    cityName = cityList?.get(i)?.name.toString()
                    loadContent(cityId)
                }
                2 -> {
                    currentStep++
                    contryId = contryList?.get(i)?.id.toString()
                    contryName = contryList?.get(i)?.name.toString()
                    listener?.let {
                        it.choose(provinceName + cityName + contryName, provinceId, cityId, contryId)
                    }

                }
            }
        }
        loadContent("")
    }

    internal var provinceList: List<BN_Area.MessageBean>? = null
    internal var cityList: List<BN_Area.MessageBean>? = null
    internal var contryList: List<BN_Area.MessageBean>? = null
    private fun loadContent(parentId: String) {
        var url = "user/personal/area.json"
        if (!TextUtils.isEmpty(parentId)) {
            url += "?parentId=" + parentId
        }
        Http.get(mContext, url, BN_Area::class.java, object : MyCallBack<BN_Area> {
            override fun onResponse(response: BN_Area?) {
                response?.let {
                    when (currentStep) {
                        0 -> provinceList = it.message
                        1 -> cityList = it.message
                        2 -> contryList = it.message
                    }
                    val adapter = ArrayAdapter<String>(mContext, android.R.layout.simple_expandable_list_item_1, getData(it))
                    lv_list?.setAdapter(adapter)
                }
            }

            override fun onError(error: ErrorBody?) {
                ToastUtil.toast(mContext, error?.message)
            }

        })

    }

    private fun getData(it: BN_Area): List<String>? {
        var list = ArrayList<String>()
        it.message.mapTo(list) { it.name }
        return list
    }


    override fun onCreatePopupView(): View {
        return createPopupById(R.layout.pop_choose_area)
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
        return getPopupWindowView();
    }

    override fun initExitAnimation(): Animation {
        val translateAnimation = TranslateAnimation(0f, 0f, 0f, (DimensUtils.dipToPx(context, 350f)).toFloat())
        translateAnimation.duration = 450
        translateAnimation.interpolator = OvershootInterpolator(-4f)
        return translateAnimation
    }


}