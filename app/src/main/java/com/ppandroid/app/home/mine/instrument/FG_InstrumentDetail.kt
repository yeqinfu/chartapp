package com.ppandroid.app.home.mine.instrument

import android.os.Bundle
import com.ppandroid.app.R
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.mine.instrument.BN_InstrumentDetail
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_instrument_detail.*
import kotlinx.android.synthetic.main.layout_head_view.*

/**
 * Created by yeqinfu on 2017/8/23.
 */
class FG_InstrumentDetail:FG_Base(){
    override fun fgRes(): Int= R.layout.fg_instrument_detail

    companion object {
        fun createBundle(id:Long,title:String):Bundle{
            var b= Bundle()
            b.putLong("id",id)
            b.putString("title",title)
            return b
        }
    }

    private var id:Long=-1
    override fun afterViews() {
        head_view.init(activity)
        var b=arguments
        b?.let {
            id=it.getLong("id",-1L)
            head_view.setCenterTitle(it.getString("title",""))
        }
        loadContent()

    }
    private fun loadContent(){
        var url="user/instrument/recordElectricity.json?id="+id
        Http.get(activity,url, BN_InstrumentDetail::class.java,object :MyCallBack<BN_InstrumentDetail>{
            override fun onResponse(response: BN_InstrumentDetail?) {
                response?.let {
                    var body=it.message
                    tv_lastModifyTime.text="最后更新时间："+body.lastModifyTime
                    tv_Ia.text=body.ia.toString()
                    tv_Ib.text=body.ib.toString()
                    tv_Ic.text=body.ic.toString()

                    tv_Ua.text=body.uab.toString()
                    tv_Ub.text=body.ubc.toString()
                    tv_Uc.text=body.uca.toString()


                    tv_Ps.text=body.ps.toString()
                    tv_Qs.text=body.qs.toString()
                    tv_PFs.text=body.pFs.toString()
                    tv_Ep.text=body.pKwh.toString()







                }
            }

            override fun onError(error: ErrorBody?) {
                toast(error)
            }

        })

    }

}