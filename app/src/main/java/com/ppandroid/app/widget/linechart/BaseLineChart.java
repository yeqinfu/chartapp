/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.widget.linechart;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import com.ppandroid.app.widget.graphical.view.GraphicalView;

/**
 * Created by yeqinfu on 2017/9/18.
 */

public class BaseLineChart extends GraphicalView {
    public BaseLineChart(Context context) {
        super(context);
    }

    public BaseLineChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseLineChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    //
    private double maxValue=0;

    @Override
    public void render(Canvas canvas) {


    }
}
