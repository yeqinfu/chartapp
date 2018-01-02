/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ppandroid.app.R;

import java.util.List;

/**
 * Created by yeqinfu on 2017/8/9.
 * 二级页面黄色统计图
 */

public class YellowChart extends RelativeLayout {
    public YellowChart(Context context) {
        super(context);
    }

    public YellowChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public YellowChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_weekAverage = (TextView) findViewById(R.id.tv_weekAverage);
        tv_weekSum = (TextView) findViewById(R.id.tv_weekSum);
        ll_info = (LinearLayout) findViewById(R.id.ll_info);
        vv_01 = (com.ppandroid.app.widget.VerticalView) findViewById(R.id.vv_01);
        vv_02 = (com.ppandroid.app.widget.VerticalView) findViewById(R.id.vv_02);
        vv_03 = (com.ppandroid.app.widget.VerticalView) findViewById(R.id.vv_03);
        vv_04 = (com.ppandroid.app.widget.VerticalView) findViewById(R.id.vv_04);
        vv_05 = (com.ppandroid.app.widget.VerticalView) findViewById(R.id.vv_05);
        vv_06 = (com.ppandroid.app.widget.VerticalView) findViewById(R.id.vv_06);
        vv_07 = (com.ppandroid.app.widget.VerticalView) findViewById(R.id.vv_07);

    }

    // Content View Elements

    private TextView tv_title;
    private TextView tv_weekAverage;
    private TextView tv_weekSum;
    private LinearLayout ll_info;
    private com.ppandroid.app.widget.VerticalView vv_01;
    private com.ppandroid.app.widget.VerticalView vv_02;
    private com.ppandroid.app.widget.VerticalView vv_03;
    private com.ppandroid.app.widget.VerticalView vv_04;
    private com.ppandroid.app.widget.VerticalView vv_05;
    private com.ppandroid.app.widget.VerticalView vv_06;
    private com.ppandroid.app.widget.VerticalView vv_07;

    public void setTitle(String title){
        if (tv_title!=null&& !TextUtils.isEmpty(title)){
            tv_title.setText(title);
        }
    }

    public void setSumAndAverage(String sum,String average){
        if (tv_weekAverage!=null){
            tv_weekAverage.setText(average);
        }
        if (tv_weekSum!=null){
            tv_weekSum.setText(sum);
        }
    }

    public void startAnim(List<String> args) {
        if (args.size() == 7) {
            float[] aa = new float[7];
            aa[0] = Float.parseFloat(args.get(0));
            aa[1] = Float.parseFloat(args.get(1));
            aa[2] = Float.parseFloat(args.get(2));
            aa[3] = Float.parseFloat(args.get(3));
            aa[4] = Float.parseFloat(args.get(4));
            aa[5] = Float.parseFloat(args.get(5));
            aa[6] = Float.parseFloat(args.get(6));
            float max = aa[0];
            for (float item : aa) {
                if (max<item){
                    max=item;
                }
            }
            max=1.2f*max;
            vv_01.setTotalHeight(max);
            vv_02.setTotalHeight(max);
            vv_03.setTotalHeight(max);
            vv_04.setTotalHeight(max);
            vv_05.setTotalHeight(max);
            vv_06.setTotalHeight(max);
            vv_07.setTotalHeight(max);

            vv_01.setRealHeight(aa[0]);
            vv_02.setRealHeight(aa[1]);
            vv_03.setRealHeight(aa[2]);
            vv_04.setRealHeight(aa[3]);
            vv_05.setRealHeight(aa[4]);
            vv_06.setRealHeight(aa[5]);
            vv_07.setRealHeight(aa[6]);

            startAnim();
        }
    }

    public void startAnim() {
        vv_01.startAnim();
        vv_02.startAnim();
        vv_03.startAnim();
        vv_04.startAnim();
        vv_05.startAnim();
        vv_06.startAnim();
        vv_07.startAnim();
    }


}
