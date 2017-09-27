/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.widget.graphical.event.touch;

import android.view.MotionEvent;

/**
 * @InterfaceName IChartTouch
 * @Description  用于手势操作图表的接口
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  
 */

public interface IChartTouch {

	public void handleTouch(MotionEvent event);
}
