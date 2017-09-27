/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */
package com.ppandroid.app.widget.graphical.render.info;

import android.graphics.PointF;

/**
 * @ClassName PlotArcLabelInfo
 * @Description 用于保存标签数据信息的类
 * 
 * @author XiongChuanLiang<br/>
 *         (xcl_168@aliyun.com) 
 *         
 */

public class PlotArcLabelInfo extends PlotDataInfo{
		
		public float Radius = 0.0f;
				
		public float OffsetAngle = 0.0f;
		public float CurrentAngle = 0.0f;
		
		private PointF mLabelPointF = null;
						
		public PlotArcLabelInfo(){};
		
		
		public PlotArcLabelInfo(int id, float x, float y,
                                float radius, float offsetAngle, float currentAngle)
		{
			 ID = id;
			 X = x;
			 Y = y;
			 Radius = radius;
			 OffsetAngle = offsetAngle;
			 CurrentAngle = currentAngle;
		}


		public float getRadius() {
			return Radius;
		}


		public void setRadius(float radius) {
			Radius = radius;
		}


		public float getOffsetAngle() {
			return OffsetAngle;
		}


		public void setOffsetAngle(float offsetAngle) {
			OffsetAngle = offsetAngle;
		}


		public float getCurrentAngle() {
			return CurrentAngle;
		}


		public void setCurrentAngle(float currentAngle) {
			CurrentAngle = currentAngle;
		}


		public PointF getLabelPointF() {
			return mLabelPointF;
		}


		public void setLabelPointF(PointF point) {
			this.mLabelPointF = point;
		};
		
		

}
