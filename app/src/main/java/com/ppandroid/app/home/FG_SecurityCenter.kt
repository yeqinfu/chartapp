/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ArrayAdapter
import com.ppandroid.app.R
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.news.BN_SecurityCenter
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.app.utils.StatusBarUtils
import com.ppandroid.app.widget.CheckView
import com.ppandroid.app.widget.HeadViewLayout
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_security_center.*
import kotlinx.android.synthetic.main.layout_head_view.*
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread


/**
 * Created by yeqinfu on 2017/8/7.
 * 安全监测中心
 */
class FG_SecurityCenter:FG_Base(){
    var rotate:RotateAnimation?=null
    var score=100
    override fun fgRes(): Int= R.layout.fg_security_center
    override fun afterViews() {
        head_view.setBackgroundResource(android.R.color.transparent)
        head_view.setBackText("返回")
        head_view.init(activity)
        head_view.setCenterTitle("智能监测")
        head_view.theme= HeadViewLayout.THEME_WHITE
        v_check_view.listener = object : CheckView.ProgreesListener {
            override fun change(progress: Float) {
                async {
                    uiThread {
                            isDestroy?.let {
                                if (it){
                                }else{
                                    tv_score.text=(score*progress).toInt().toString()
                                    if (progress>0&&progress<=0.25){//第一阶段
                                        iv_check.setImageResource(R.drawable.icon_check)
                                        iv_check2.setImageResource(R.drawable.icon_check)
                                        iv_check3.setImageResource(R.drawable.icon_check)
                                        iv_check4.setImageResource(R.drawable.icon_check)
                                        excuteAnimation()
                                    }


                                    if (progress>=0.99){
                                        ll_content.setBackgroundResource(R.color.orange)
                                        StatusBarUtils.setWindowStatusBarColor(activity,R.color.orange)
                                        head_view.theme=HeadViewLayout.THEME_ORANGE
                                        rotate?.cancel()
                                        iv_check4.setImageResource(R.drawable.icon_ok)
                                        changeView()
                                    }
                                }
                            }
                    }
                }
            }
        }


        loadContent()

    }

    private fun loadContent() {
        var url="user/failure/examine.json"
        Http.get(activity,url, BN_SecurityCenter::class.java,object :MyCallBack<BN_SecurityCenter>{
            override fun onResponse(response: BN_SecurityCenter?) {
                response?.let {
                    score=it.message.score
                    tv_score.text=score.toString()
                    if (!it.message.deviceEntityList.isEmpty()){
                        val adapter = ArrayAdapter<String>(activity, R.layout.simple_list_item_1)
                        for (item in it.message.deviceEntityList){
                            adapter.add(item.name)
                        }
                        lv_list02.adapter=adapter
                        tv_content02.text="系统检测设备"+it.message.deviceEntityList.size+
                                "个设备存在风险，请尽快排查。"
                        ll_msg2.visibility=View.VISIBLE
                        ll_msg2.setOnClickListener {
                         startAC(FG_DevicesAll::class.java.name)
                        }
                    }
                    if (!it.message.waterDeviceEntityList.isEmpty()){
                        val adapter = ArrayAdapter<String>(activity, R.layout.simple_list_item_1)
                        for (item in it.message.waterDeviceEntityList){
                            adapter.add(item.name)
                        }
                        lv_list03.adapter=adapter
                        tv_content_03.text="系统检测设备"+it.message.waterDeviceEntityList.size+
                                "个设备存在风险，请尽快排查。"
                        ll_msg.visibility=View.VISIBLE
                        ll_msg.setOnClickListener {
                            startAC(FG_DevicesAll::class.java.name)
                        }
                    }
                    if (!it.message.waterDeviceEntityList.isEmpty()||!it.message.deviceEntityList.isEmpty()){
                        rl_001.visibility=View.GONE
                        rl_004.visibility=View.GONE
                    }else{
                        rl_001.visibility=View.VISIBLE
                        rl_004.visibility=View.VISIBLE
                    }

                    v_check_view.startAnim()

                }
            }
            override fun onError(error: ErrorBody?) {
                toast(error)
            }

        })
    }

    private var isDestroy=false
    override fun onDestroy() {
        isDestroy=true
        super.onDestroy()
    }

    private fun excuteAnimation() {

        rotate = RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        rotate?.apply {
            interpolator =  AccelerateDecelerateInterpolator()
            duration = 300//设置动画持续时间
            repeatCount = -1//设置重复次数
            fillAfter = true//动画执行完后是否停留在执行完的状态
            startOffset = 10//执行前的等待时间
            iv_check.startAnimation(this)
            iv_check2.startAnimation(this)
            iv_check3.startAnimation(this)
            iv_check4.startAnimation(this)
        }
    }

    private fun px2sp(pxValue:Float):Int{
        val fontScale = activity.resources.displayMetrics.scaledDensity
        val result=(pxValue / fontScale + 0.5f).toInt()
        return result
    }

    /**
     * dp转px

     * @param dpValue dp值
     * *
     * @return px值
     */
    fun dp2px(dpValue: Float): Int {
        val scale = activity.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }
    private fun changeView() {
        v_check_view.visibility= View.INVISIBLE
        tv_msg02.visibility= View.INVISIBLE
        val location = IntArray(2)
        rl_content.getLocationInWindow(location) //获取在当前窗口内的绝对坐标
        // view 移动
        val out = ObjectAnimator.ofFloat(ll_before, "translationY",0f, ll_content.height.toFloat())
        val int = ObjectAnimator.ofFloat(ll_after, "translationY",ll_content.height.toFloat(),-200f  )
        val animator3 = ObjectAnimator.ofFloat(tv_score, "textSize",px2sp(tv_score.textSize).toFloat(),(px2sp(tv_score.textSize)-6).toFloat())
        val animator4 = ObjectAnimator.ofFloat(rl_move, "translationY",0f,-head_view.height.toFloat())
        out.duration=500
        int.duration=500
        animator3.duration=500
        animator4.duration=500

        int.startDelay=500
        animator3.startDelay=500
        animator4.startDelay=500
        val set = AnimatorSet()
        set.playTogether(out, int,animator3,animator4)
        set.start()
        out.addListener(object: Animator.AnimatorListener {
            override fun onAnimationRepeat(p0: Animator?) {
            }
            override fun onAnimationEnd(p0: Animator?) {
                isDestroy?.let {
                    if (!it){
                        ll_before.visibility= View.GONE
                        ll_after.visibility=View.VISIBLE
                    }
                }
            }
            override fun onAnimationStart(p0: Animator?) {
            }

            override fun onAnimationCancel(p0: Animator?) {
            }

        })
    }

}