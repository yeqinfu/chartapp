/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.widget.graphical.chart;

import com.ppandroid.app.widget.graphical.render.XEnum;

import java.util.List;


/**
 * @ClassName RadarData
 * @Description  雷达图数据传输基类
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  * MODIFIED    YYYY-MM-DD   REASON
 */
public class RadarData extends LineData{
	
	//数据填充区域显示风格
	private XEnum.DataAreaStyle mAreaStyle = XEnum.DataAreaStyle.FILL;
	//网格线绘制风格 Solid、Dot、Dash。
	private XEnum.LineStyle mLineStyle = XEnum.LineStyle.SOLID;
	
	public RadarData() {
		// TODO Auto-generated constructor stub
		
		getPlotLine().setDotStyle(XEnum.DotStyle.HIDE);
	}
	
	/**
	 * 
	 * @param key	key值
	 * @param dataSeries 数据序列
	 * @param color		线颜色
	 * @param areaStyle	区域填充类型
	 */
	public RadarData(String key,
                     List<Double> dataSeries,
                     int color,
                     XEnum.DataAreaStyle  areaStyle)
	{
		setLabel(key);		
		setLineColor(color);
		setDataSet(dataSeries);
		setAreaStyle(areaStyle);
		getPlotLine().setDotStyle(XEnum.DotStyle.HIDE);
	}
	
	/**
	 * 设置雷达图数据填充区域显示风格
	 * @param style 填满或为空(即只显示线)
	 */
	public void setAreaStyle(XEnum.DataAreaStyle style){
		mAreaStyle = style;
	}
	
	/**
	 * 返回雷达图数据当前的填充区域显示风格
	 * @return 填充区域显示风格
	 */
	public XEnum.DataAreaStyle getAreaStyle()
	{
		return mAreaStyle;
	}
	
	 
	 
	/**
	 * 返回网格线当前绘制风格
	 * @return 绘制风格
	 */
	@Override
	public XEnum.LineStyle getLineStyle()
	{
		return mLineStyle;
	}

	/**
	 * 设置网格线绘制风格 
	 * @param style 绘制风格
	 */
	@Override
	public void setLineStyle(XEnum.LineStyle style)
	{
		mLineStyle = style;
	}
	 
	
	
}
