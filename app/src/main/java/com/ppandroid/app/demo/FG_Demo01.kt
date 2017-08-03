package com.ppandroid.app.demo

import android.os.Handler
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.ScrollView
import com.ppandroid.app.R
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_demo01.*
import java.util.*


/**
 * Created by yeqinfu on 2017/8/2.
 */
class FG_Demo01:FG_Base(){
    override fun fgRes(): Int= R.layout.fg_demo01
    var TIME:Int=5*1000
    //本期是否投注
    var isPut=false
    //投注金额
    var putNumber=-1
    var targetNumber=-1
    //总余额
    var total=30000
    var isAotoPut=false
    var getCount=0
    override fun afterViews() {
        tv_show.text="公告栏\n"
        // 建立数据源
        val mItems = resources.getStringArray(R.array.array_number)
      // 建立Adapter并且绑定数据源
        val adapter = ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, mItems)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        //绑定 Adapter到控件
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View,
                                            pos: Int, id: Long) {

                    toast("你点击的是:" +mItems[pos])
                    TIME=mItems[pos].toInt()*1000
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Another interface callback
                }
        }
        handler.postDelayed(runnable, TIME.toLong()) //每隔1s执行
        btn_confirm2.setOnClickListener {
            var count=et_put.text.toString().toInt()
            var number=et_put_number.text.toString().toInt()
            et_put.setText((count+1).toString())
            count++
            put(count, number)
        }

        btn_confirm.setOnClickListener {
            var count=et_put.text.toString().toInt()
            var number=et_put_number.text.toString().toInt()
            put(count, number)


        }
        btn_auto_put.setOnClickListener {
            if (isAotoPut){
                isAotoPut=false
                toast("您已经取消全自动增额跟注")
            }else{
                isAotoPut=true
                toast("已经开启全总动增额跟注")
            }
        }
        btn_net.setOnClickListener {

            if (isNetAuto){
                tempCount=-1
                isNetAuto=false
                toast("您已经取消智能保本投注")
            }else{
                tempCount=total
                isNetAuto=true
                toast("已经开启智能保本投注")
            }

        }





    }
    //上一次余额
    var tempCount=0
    var isNetAuto=false


    private fun put(count: Int, number: Int) {
        if (isPut) {
            toast("本期您已经投注")
            return
        }

        if (count in 1..total) {
            if (number in 1..50) {
                targetNumber = number
                total -= count
                isPut = true
                putNumber = count
                tv_cash.text = "剩余￥" + total
                appendMsg("您投注数字" + targetNumber + "投注金额" + putNumber)
            } else {
                toast("投注数字不合法")
            }
        } else {
            toast("输入金额不合法")
        }
    }

    private var number:Int=1
    private fun appendMsg(msg:String){
        var text="-（第"+number+"期："+msg+")-\n"
        tv_show.append(text)
        sv.fullScroll(ScrollView.FOCUS_DOWN)
    }

    var handler = Handler()
    var runnable: Runnable = object : Runnable {

        override fun run() {
            // handler自带方法实现定时器
            try {
                val random = Random()
                var nextInt=random.nextInt(49)
                appendMsg("+++++++开奖号码+++++++"+nextInt.toString())
                if (isPut){
                    //中奖
                    if (nextInt==targetNumber){
                        total+=(42*putNumber)
                        tv_cash.text="剩余￥"+total
                        appendMsg("******本期您中奖啦**********")
                        getCount++
                        tv_count.text="中奖次数"+getCount
                        if (isAotoPut){
                             appendMsg("全自动增额跟注 中奖，为您重置金额为1")
                            et_put.setText((1).toString())
                        }
                    }else{
                        appendMsg("本期未中奖")
                    }
                    isPut=false
                }
                number++
                //全自动增额跟注
                if (isAotoPut){
                    var count=et_put.text.toString().toInt()
                    var number=et_put_number.text.toString().toInt()
                    et_put.setText((count+1).toString())
                    count++
                    put(count, number)
                }
                //智能保本投注
                autoPut()

                handler.postDelayed(this, TIME.toLong())


            } catch (e: Exception) {
                e.printStackTrace()
                println("exception...")
            }

        }
    }

    private fun autoPut() {
        if (isNetAuto) {
            //如果现在的钱大于等于上次记录的钱说明此次中奖了 中奖了就重新投注设定为1元
            if (total > tempCount) {
                //重新记录没亏本的最后一次钱的数量
                tempCount = total
                //重置投注金额
                et_put.setText((1).toString())
            } else {
                //说明没有中奖 d变量记录从上一次赚钱到一直亏的亏钱总量
                var d=tempCount-total
                //动态增加盈利 冲刺系数
                tv_flag.text="上次财富峰值："+tempCount+"盈利冲刺系数："+(total/49)
                d+=(total/49)
                //下一次投注多少才能至少赚d 数量的金币？ target就是至少要投注的量
                var target=d/42
                target++
                //如果需要投的量小于余额，说明本次的自动保本算法失败了
                if (target>total){
                    toast("余额已经不足用来继续智能投注了，停止了投注")
                    isNetAuto=false
                }else{
                    //如果还有机会 设置下次投注额度
                    et_put.setText((target).toString())
                    var count3=et_put.text.toString().toInt()
                    var number3=et_put_number.text.toString().toInt()
                    //进行投注
                    put(count3, number3)
                }

            }
        }
    }

}