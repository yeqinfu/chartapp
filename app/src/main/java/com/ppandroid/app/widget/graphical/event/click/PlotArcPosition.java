/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */
package com.ppandroid.app.widget.graphical.event.click;

import android.graphics.PointF;


/**
 * @ClassName PlotArcPosition
 * @Description  arc位置记录信息类
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  
 */

public class PlotArcPosition extends ArcPosition {
	
	public PlotArcPosition()
	{	
	}
	
	public void saveAngle(float radius,float offsetAngle,
						  float currentAngle,float selectedOffset)
	{
		mRadius = radius;
		mOffsetAngle = offsetAngle;
		mCurrentAngle = currentAngle;
		mSelectedOffset = selectedOffset;
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
		
	
	public void savePlotCirXY(float x,float y)
	{
		if(null == mCirXY)
			mCirXY = new PointF();
		
		mCirXY.x =  x;
		mCirXY.y =  y;				
	}
		
	
	public boolean compareF(float x, float y)
	{
		return compareRange(x,y);
	}
		
}
