/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */
package com.ppandroid.app.widget.graphical.render.info;

import android.graphics.Canvas;

/**
 * @ClassName Legend
 * @Description 动态图例绘制类
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  
 */
public class LegendRender extends Legend{
	
	public LegendRender()
	{

	}
	
	public void setPlotWH(float width,float height)
	{				
		setCenterXY(width * mXPercentage,height * mYPercentage);
	}

	public void renderInfo(Canvas canvas) 
	{
		drawInfo(canvas);
	}
	
}
