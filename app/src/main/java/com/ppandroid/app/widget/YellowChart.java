package com.ppandroid.app.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ppandroid.app.R;

/**
 * Created by yeqinfu on 2017/8/9.
 * 二级页面黄色统计图
 */

public class YellowChart extends RelativeLayout{
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
    private LinearLayout ll_info;
    private com.ppandroid.app.widget.VerticalView vv_01;
    private com.ppandroid.app.widget.VerticalView vv_02;
    private com.ppandroid.app.widget.VerticalView vv_03;
    private com.ppandroid.app.widget.VerticalView vv_04;
    private com.ppandroid.app.widget.VerticalView vv_05;
    private com.ppandroid.app.widget.VerticalView vv_06;
    private com.ppandroid.app.widget.VerticalView vv_07;

    public void startAnim(){
        vv_01.startAnim();
        vv_02.startAnim();
        vv_03.startAnim();
        vv_04.startAnim();
        vv_05.startAnim();
        vv_06.startAnim();
        vv_07.startAnim();
    }





}
