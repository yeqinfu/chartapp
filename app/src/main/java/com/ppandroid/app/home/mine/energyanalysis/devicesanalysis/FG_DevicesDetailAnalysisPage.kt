package com.ppandroid.app.home.mine.energyanalysis.devicesanalysis

import com.ppandroid.app.bean.BN_Vertical
import kotlinx.android.synthetic.main.fg_base_devices_analysis_page.*
import java.util.*

/**
 * Created by yeqinfu on 2017/9/13.
 */
class FG_DevicesDetailAnalysisPage :FG_BaseDevicesAnlysisPage(){
    override fun init() {
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
        v_multiple_view.startAnim()
    }

    override fun loadContent() {
    }

}