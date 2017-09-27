/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */
package com.ppandroid.app.widget.graphical.event.click;

import android.graphics.PointF;
import android.graphics.RectF;


/**
 * @ClassName PlotPointPosition
 * @Description  点位置记录信息类
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  
 */

public class PlotPointPosition extends PointPosition {
	
	public PlotPointPosition()
	{	
	}	
			
	//当前记录在数据源中行号
	public void savePlotDataID(int num)
	{
		saveDataID(num);
	}

	//当前记录所属数据集的行号
	public void savePlotDataChildID(int num)
	{
		saveDataChildID(num);
	}	
			
	public void savePlotPosition(float x,float y)
	{
		if(null == mPoint)mPoint = new PointF();
		
		mPoint.x =  x;
		mPoint.y =  y;				
	}
	
	public void savePlotRectF(float left,float top,
							  float right,float bottom)
	{		
		saveRectF(left, top, right, bottom);
	}
	
	public void savePlotRectF(final RectF r)
	{
		saveRectF(r);
	}
	
	public  boolean compareF(float x, float y) 
	{
		// TODO Auto-generated method stub
		
		return compareRange(x,y);	
	}			
	
	
}
