/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */
package com.ppandroid.app.widget.wheelview;

import android.graphics.Path;
import android.graphics.RectF;

import com.ppandroid.app.utils.DebugLog;

class OverdrawValueRenderer extends BaseRenderer implements Renderer {
    public OverdrawValueRenderer(RectF drawingArea, FitChartValue value) {
        super(drawingArea, value);
    }

    @Override
    public Path buildPath(float animationProgress, float animationSeek) {
        float startAngle = FitChart.START_ANGLE;
        float valueSweepAngle = (getValue().getStartAngle() +  getValue().getSweepAngle());
        valueSweepAngle -= startAngle;
        float sweepAngle = valueSweepAngle * animationProgress;
        DebugLog.d("getStartAngle"+getValue().getStartAngle() + "+++"+ getValue().getSweepAngle());
        Path path = new Path();
        path.addArc(getDrawingArea(), startAngle, sweepAngle);
        return path;
    }
}
