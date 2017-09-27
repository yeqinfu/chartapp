/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */
package com.ppandroid.app.widget.graphical.render.plot;

import android.graphics.Paint;

import com.ppandroid.app.widget.graphical.render.XEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName PlotAttrInfo
 * @Description 图的附加信息绘制基类
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 * 
 */
public class PlotAttrInfo {
	
	protected List<XEnum.Location> mAttrInfoLocation = null;
	protected List<String> mAttrInfo = null;
	protected List<Float> mAttrInfoPostion = null;	
	protected List<Paint> mAttrInfoPaint = null;
	
	
	public PlotAttrInfo()
	{
	}
	
	/**
	 * 返回附加信息集合
	 * @return 集合
	 */
	public List<String> getPlotAttrInfo()
	{
		return mAttrInfo;
	}
	
	/**
	 * 返回附加信息位置集合
	 * @return 集合
	 */
	public List<Float> getPlotAttrInfoPostion()
	{
		return mAttrInfoPostion;
	}
	
	/**
	 * 返回附加信息画笔集合
	 * @return 集合
	 */
	public List<Paint> getPlotAttrInfoPaint()
	{
		return mAttrInfoPaint;
	}
	
	
	/**
	  * 清掉所有附加信息
	  */
	 public void clearPlotAttrInfo()
	 {
		if(null != mAttrInfoLocation) mAttrInfoLocation.clear();	
		if(null != mAttrInfo) mAttrInfo.clear();	
		if(null != mAttrInfoPostion) mAttrInfoPostion.clear();	
		if(null != mAttrInfoPaint) mAttrInfoPaint.clear();	
	 }		
	 
	
	 
	 /**
	  * 增加附加信息
	  * @param position		显示方位
	  * @param info			附加信息
	  * @param infoPosRadiusPercentage	信息显示在总半径指定比例所在位置
	  * @param paint		用来绘制用的画笔
	  */
	   public void addAttributeInfo(XEnum.Location position , String info,
                                    float infoPosRadiusPercentage, Paint paint) {
	   	
		   	if(null == mAttrInfoLocation) mAttrInfoLocation = new ArrayList<XEnum.Location> ();
		   	if(null == mAttrInfo) mAttrInfo = new ArrayList<String>();
		   	
		   	if(null == mAttrInfoPostion) mAttrInfoPostion = new ArrayList<Float>();    	
		   	if(null == mAttrInfoPaint) mAttrInfoPaint = new ArrayList<Paint>();
		   	    	
		   	mAttrInfoLocation.add(position);
		   	mAttrInfo.add(info);
		   	mAttrInfoPostion.add(infoPosRadiusPercentage);
		   	mAttrInfoPaint.add(paint);    	
	   }
	

}
