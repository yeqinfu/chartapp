package com.ppandroid.app.home.mine.energyanalysis.totalhorzizontalanalysis

import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.mine.energyanalysis.BN_HuanPage
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.app.utils.Utils_Common
import kotlinx.android.synthetic.main.fg_base_line_chart.*
import java.util.*

/**
 * Created by yeqinfu on 2017/9/21.
 */
class FG_HuanPage:FG_BaseLineChart(){
    override fun afterViews() {
        super.afterViews()
        loadContent()
    }
    private fun loadContent(){
        if (index==2){
            loadWeekContent()
            return
        }
        var url="user/energy/analysis/getDeviceAllDetailHuanbi.json?dateString=$select"
        Http.get(activity,url,BN_HuanPage::class.java,object :MyCallBack<BN_HuanPage>{
            override fun onResponse(response: BN_HuanPage?) {
                response?.let {
                    var sum=it.message.thisAnalysisDeviceDetailDto.accurateTotalKwh.toString()
                    var radio=it.message.huanbiRatio
                    setSumRadio(sum,radio)
                    value.putAll(setValues(it.message.huanbiAnalysisDeviceDetailDto))
                    value2.putAll(setValues(it.message.huanbiAnalysisDeviceDetailDto))
                    setValueAll()

                }
            }

            override fun onError(error: ErrorBody?) {
                toast(error)
            }

        })
    }



    private fun setValues(list: BN_HuanPage.MessageBean.AnalysisDeviceDetailDtoBean?): Map<out String, Double> {
        var result= HashMap<String, Double>()
        var data=list?.stageKwh
        data?.let {
            var need=false
            if (xValue.size<it.size){//横坐标的刻度长取决于两组数据的大的那一个
                xValue.clear()
                need=true
            }
            for (item in it){
                if (need){//添加x轴数据
                    xValue.add(item.key)
                }
                result.put(item.key,Utils_Common.parseNumberString(item.value))

            }
        }
        return result
    }

    private fun loadWeekContent() {

    }
}