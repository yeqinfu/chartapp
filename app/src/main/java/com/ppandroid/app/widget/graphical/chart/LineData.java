/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */
package com.ppandroid.app.widget.graphical.chart;

import com.ppandroid.app.widget.graphical.render.XEnum;

import java.util.List;


/**
 * @ClassName LineData
 * @Description 数据类, 折线图,面积图都用这个传数据
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  
 */

public class LineData extends LnData{
		
			//线上每个点的值
			private List<Double> mLinePoint;

			//标签文字旋转角度
			private float mItemLabelRotateAngle = 0.0f;	
			
			
			public LineData() {
				// TODO Auto-generated constructor stub
			}
			
			/**
			 *  构成一条完整的线条
			 * @param key	键值		
			 * @param color  线条颜色
			 * @param dataSeries   对应的数据集
			 */
			public LineData(String key, List<Double> dataSeries, int color)
			{
				setLabel(key);	
				setLineKey(key);
				setDataSet(dataSeries);
				setLineColor(color);				
			}
			
			/**
			 * 
			 * @param key	key值
			 * @param dataSeries 数据序列
			 * @param color		线颜色
			 * @param dotStyle	坐标点绘制类型
			 */
			public LineData(String key,
                            List<Double> dataSeries,
                            int color,
                            XEnum.DotStyle  dotStyle)
			{
				setLabel(key);	
				setLineKey(key);
				setLineColor(color);
				setDataSet(dataSeries);
				setDotStyle(dotStyle);
			}
							
			/**
			 * 设置绘制线的数据序列
			 * @param dataSeries 数据序列
			 */
			public void setDataSet(List<Double> dataSeries) 
			{
				mLinePoint = dataSeries;
			}

			/**
			 * 返回绘制线的数据序列
			 * @return 绘制线的数据序列
			 */
			public List<Double> getLinePoint() {
				return mLinePoint;
			}
			
			/**
			 * 返回标签在显示时的旋转角度
			 * @return 旋转角度
			 */
			public float getItemLabelRotateAngle() {
				return mItemLabelRotateAngle;
			}

			/**
			 * 设置标签在显示时的旋转角度
			 * @param rotateAngle 旋转角度
			 */
			public void setItemLabelRotateAngle(float rotateAngle) {
				this.mItemLabelRotateAngle = rotateAngle;
			}
			
			
}
