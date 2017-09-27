/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */
package com.ppandroid.app.widget.graphical.render.info;

import android.graphics.Canvas;

/**
 * @ClassName ToolTip
 * @Description tooltip绘制类
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  
 */
public class ToolTipRender extends ToolTip{

	public ToolTipRender()
	{
		
	}
	
	public void renderInfo(Canvas canvas) 
	{
		drawInfo(canvas);
		clear();
	}
	
}
