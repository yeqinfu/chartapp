/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */
package com.ppandroid.app.widget.graphical.render.info;


import android.graphics.Canvas;

import com.ppandroid.app.widget.graphical.common.DrawHelper;

/**
 * @ClassName DyLineRender
 * @Description 交叉线基类
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  
 */
public class DyLineRender extends DyLine{
	
	
	private float mLeft = 0.0f;
	private float mTop = 0.0f;
	private float mRight = 0.0f;
	private float mBottom = 0.0f;
	
	public DyLineRender()
	{
	}
		
	// Cross 指定交叉的水平线和垂直线。
	// BackwardDiagonal 从右上到左下的对角线的线条图案。
	// VerticalView		垂直线
	// Horizontal 水平线
	private void drawCross(Canvas canvas)
	{	
		//竖线
		drawVertical(canvas);
		//横线
		drawHorizontal(canvas);
	}
	
	private void drawBackwardDiagonal(Canvas canvas)
	{		
		//竖线
		DrawHelper.getInstance().drawLine(getLineDrawStyle(),
				mCenterXY.x, mCenterXY.y, mCenterXY.x, mBottom,canvas,getLinePaint());	
		//横线
		DrawHelper.getInstance().drawLine(getLineDrawStyle(), 
				mLeft, mCenterXY.y, mCenterXY.x, mCenterXY.y,canvas,getLinePaint());	
	}
	
	private void drawVertical(Canvas canvas) //竖线
	{			
		DrawHelper.getInstance().drawLine(getLineDrawStyle(), 
				mCenterXY.x, mTop, mCenterXY.x, mBottom,canvas,getLinePaint());	
	}
	
	private void drawHorizontal(Canvas canvas)
	{
		DrawHelper.getInstance().drawLine(getLineDrawStyle(), 
				mLeft, mCenterXY.y, mRight, mCenterXY.y,canvas,getLinePaint());	
	}

	public void renderLine(Canvas canvas,float left,float top,float right,float bottom) 
	{						
		if(null == mCenterXY) return;
						
		//if(Float.compare(left, 0.0f) == 0 && Float.compare(top, 0.0f) == 0 &&
		//	Float.compare(right, 0.0f) == 0 && Float.compare(bottom, 0.0f) == 0) return;
		
		if(Float.compare(mCenterXY.x, left) == 0 ||
		  Float.compare(mCenterXY.x, left) == -1 ||
		  Float.compare(mCenterXY.x, right) == 0 ||
		  Float.compare(mCenterXY.x, right) == 1 ||				  
		  Float.compare(mCenterXY.y, top) == 0 ||
		  Float.compare(mCenterXY.y, top) == -1 ||
		  Float.compare(mCenterXY.y, bottom) == 0 ||
		  Float.compare(mCenterXY.y, bottom) == 1 ) return;
		
		
		mLeft = left;
		mTop = top;
		mRight = right;
		mBottom = bottom;
		
		switch(getDyLineStyle())
		{
		case Cross:
			drawCross(canvas);
			break;
		case BackwardDiagonal:
			drawBackwardDiagonal(canvas);
			break;
		case Vertical:
			drawVertical(canvas);
			break;
		case Horizontal:
			drawHorizontal(canvas);
			break;
		}
	}

}
