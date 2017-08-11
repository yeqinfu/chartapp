package com.ppandroid.im

import com.ppandroid.app.R
import com.ppandroid.app.bean.BN_Vertical
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_mine.*
import java.util.*

/**
 * Created by yeqinfu on 2017/7/28.
 */
class FG_Mine: FG_Base() {
    override fun fgRes(): Int= R.layout.fg_mine

    override fun afterViews() {
        var list = ArrayList<BN_Vertical>()
        for (i in 0..23) {
            var bn = BN_Vertical()
            if (i % 2 == 0) {
                if (i < 10) {
                    bn.bottomText = "0" + i
                } else {
                    bn.bottomText = "" + i
                }

            }
            bn.totalHeight = 100f
            var r = Random()
            bn.realHeight = r.nextFloat() * 100f
            list.add(bn)
        }

        v_multiple_view.dataSet = list
        btn_multi.setOnClickListener {
            v_multiple_view.startAnim()

        }



    }


}