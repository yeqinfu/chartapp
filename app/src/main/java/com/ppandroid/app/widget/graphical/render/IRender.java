/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.widget.graphical.render;

/**
 * @InterfaceName IRender
 * @Description  用于绘制的接口
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 */

import android.graphics.Canvas;

public interface IRender {	
	public boolean render(Canvas canvas) throws Exception;
}
