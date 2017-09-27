/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */
package com.ppandroid.app.widget.graphical.render.plot;

import android.graphics.Canvas;
import android.graphics.PointF;

/**
 * @ClassName PlotAttrInfoRender
 * @Description 图的附加信息绘制类
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 * 
 */
public class PlotAttrInfoRender extends PlotAttrInfo{
	
	PointF mPosPintF = new PointF();
	
	
	public PlotAttrInfoRender()
	{
	}
	
	 
	/**
	 * 绘制附加信息
	 * @param canvas		画布
	 * @param centerX		绘图区中心点X坐标
	 * @param centerY		绘图区中心点Y坐标
	 * @param plotRadius	当前半径
	 */
	public void renderAttrInfo(Canvas canvas,float centerX,float centerY,float plotRadius)
	{		
		if(null == mAttrInfo) return ;
		if(null == mAttrInfoLocation) return ;
		float radius = 0.0f; 
		String info = "";		
		
		for(int i=0;i<mAttrInfo.size();i++)
		{
			info = mAttrInfo.get(i);
			if("" == info) continue;
			
			if(null == mAttrInfoPostion || mAttrInfoPostion.size() < i)continue;	
			if(null == mAttrInfoPaint.get(i) || mAttrInfoPaint.size() < i) continue;
			
			mPosPintF.x =  centerX;
			mPosPintF.y =  centerY;
			
			radius = plotRadius * mAttrInfoPostion.get(i);
			switch(mAttrInfoLocation.get(i))
			{
				case TOP:
					mPosPintF.y =  centerY - radius;
					break;
				case BOTTOM:
					mPosPintF.y =  centerY + radius;
					break;
				case LEFT:
					mPosPintF.x =  centerX - radius;
					break;
				case RIGHT:
					mPosPintF.x =  centerX + radius;
					break;
			}	    							
			canvas.drawText(info, mPosPintF.x, mPosPintF.y, mAttrInfoPaint.get(i));
		}
		
	}

}
