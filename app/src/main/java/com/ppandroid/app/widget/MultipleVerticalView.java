/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.ppandroid.app.R;
import com.ppandroid.app.bean.BN_Vertical;
import com.ppandroid.app.utils.DebugLog;
import com.ppandroid.app.widget.graphical.common.SysinfoHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yeqinfu on 2017/8/10.
 * 多条树状图
 */

public class MultipleVerticalView extends View implements Runnable{
    public MultipleVerticalView(Context context) {
        super(context);
        initSetting();
    }

    public MultipleVerticalView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initSetting();
    }

    public MultipleVerticalView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initSetting();
    }
    private void initSetting() {
        //禁用硬件加速
        disableHardwareAccelerated();
        strokeSize=40f;

    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }

    private float strokeSize;
    private Paint getPaint() {
        if (!isInEditMode()) {
            return new Paint(Paint.ANTI_ALIAS_FLAG);
        } else {
            return new Paint();
        }
    }
    private Paint getTextPaint() {
        Paint paint = getPaint();
        paint.setTextSize(30f);
        paint.setColor(getResources().getColor(R.color.color_07));
        paint.setTextAlign(Paint.Align.CENTER);
        return paint;
    }
    private Paint getPicPaint(){
        Paint paint = getPaint();
        paint.setColor(getResources().getColor(R.color.color_10));
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(strokeSize);

        return paint;
    }
    private Paint getSelectPaint(){
        Paint paint = getPaint();
        paint.setColor(getResources().getColor(R.color.color_09));
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(strokeSize);

        return paint;
    }


    private List<BN_Vertical> dataSet = new ArrayList<>();

    public List<BN_Vertical> getDataSet() {
        return dataSet;
    }

    public void setDataSet(List<BN_Vertical> dataSet) {
        this.dataSet = dataSet;
        if (!dataSet.isEmpty()){
            strokeSize=getWidth()/(dataSet.size()*2);
        }

    }

    private float progress = 0.0f;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        if (!isInEditMode()){
            if (!dataSet.isEmpty()){
                strokeSize=getWidth()/(dataSet.size()*2);
            }
        }

    }

    /**是否显示平均值*/
    private boolean isShowAvg=false;
    private BN_Vertical avgValue=null;

    public BN_Vertical getAvgValue() {
        return avgValue;
    }

    public void setAvgValue(BN_Vertical avgValue) {
        this.avgValue = avgValue;
    }

    public boolean isShowAvg() {
        return isShowAvg;
    }

    public void setShowAvg(boolean showAvg) {
        isShowAvg = showAvg;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Paint p=getTextPaint();
        Paint.FontMetrics fontMetrics = p.getFontMetrics();
        float top = fontMetrics.top;//为基线到字体上边框的距离,即上图中的top
        float bottom = fontMetrics.bottom;//为基线到字体下边框的距离,即上图中的bottom
        int baseLineY = (int) (bottom-top);//基线中间点的y轴计算公式

        for (int i=0;i<dataSet.size();i++) {
            BN_Vertical item=dataSet.get(i);
            float itemProgress=progress*item.getRealHeight()/item.getTotalHeight();
            float begin=(i+0.5f)*getWidth()/dataSet.size();
            float h=itemProgress*(getHeight()-baseLineY*2.5f);
            canvas.drawText(item.getTopText(),begin,getHeight()-baseLineY*1.5f-h,p);
            canvas.drawText(item.getBottomText(),begin,getHeight(),p);
            Paint p2=getPicPaint();
            float v1=getHeight()-h-baseLineY;
            float v2=getHeight()-baseLineY;
            if (v1<v2){
                if (i==select){//选中的用灰色画笔
                    canvas.drawLine(begin,v1,begin,v2,getSelectPaint());
                    canvas.drawText(item.getRealHeight()+"",begin,getHeight()-baseLineY*1.5f-h,p);
                }else{
                    canvas.drawLine(begin,v1,begin,v2,p2);
                }
                item.setStartY(v1);
                item.setStartX(begin);
                item.setEndX(begin);
               // item.setEndY(v2);
                /*这边设置endy为这个值，是因为当柱状图太短的时候选不中*/
                item.setEndY(getHeight());

            }

        }
        if (isShowAvg){//显示平均线
            Paint  mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mPaint.setColor(getResources().getColor(R.color.color_02));
            mPaint.setStrokeWidth(3);
            mPaint.setPathEffect(new DashPathEffect(new float[] {5, 5}, 0));

            if (avgValue!=null){
                float h=(avgValue.getRealHeight()/avgValue.getTotalHeight())*(getHeight()-baseLineY*2.5f);
                float v1=getHeight()-h-baseLineY;
                DebugLog.d("avg h-->"+h);
                float textWidth=p.measureText(avgValue.getTopText());
                canvas.drawText(avgValue.getTopText(),getWidth()-textWidth,getHeight()-baseLineY*1.5f-h,p);
                canvas.drawLine(0, v1, getWidth(), v1, mPaint);
            }
        }

    }
    //被选中的那条
    private int select=-1;


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction()==MotionEvent.ACTION_DOWN){
            float eventX = event.getX();
            float eventY = event.getY();
            for (int i=0;i<dataSet.size();i++){
                BN_Vertical item=dataSet.get(i);
                if (Math.abs(item.getEndX()-eventX)<=strokeSize&&item.getStartY()<=eventY&&item.getEndY()>=eventY){
                    select=i;
                    invalidate();
                    break;
                }

            }
        }
        return super.onTouchEvent(event);

    }
    /**
     * dp转化成为px
     *
     * @param dp
     * @return
     */
    private int dpToPx(int dp) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5f * (dp >= 0 ? 1 : -1));
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            chartAnimation();
        }
        catch(Exception e) {
            Thread.currentThread().interrupt();
        }
    }

    private void chartAnimation(){
        for (int i=1;i<=100;i++){
            try {
                Thread.sleep(5);
                setProgress((float) i/100);
                postInvalidate();
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }

        }
    }
    public void startAnim(){
        new Thread(this).start();
    }


    /**
     * 禁用硬件加速. 原因:android自3.0引入了硬件加速，即使用GPU进行绘图,但它并不能完善的支持所有的绘图，
     * 通常表现为内容(如Rect或Path)不可见，异常或渲染错误。所以类了保证图表的正常显示，强制禁用掉.
     */
    protected void disableHardwareAccelerated() {
        //View.isHardwareAccelerated()
        //Canvas.isHardwareAccelerated()

        if (SysinfoHelper.getInstance().supportHardwareAccelerated()) {
            //是否开启了硬件加速,如开启将其禁掉
            if (!isHardwareAccelerated()) {
                //setLayerType(View.LAYER_TYPE_NONE,null);  //LAYER_TYPE_SOFTWARE
                setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            }
        }
    }
}
