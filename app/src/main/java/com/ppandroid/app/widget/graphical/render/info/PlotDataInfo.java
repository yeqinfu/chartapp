/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */
package com.ppandroid.app.widget.graphical.render.info;


/**
 * @ClassName PlotDataInfo
 * @Description 用于保存图表一些数据信息的基类
 * 
 * @author XiongChuanLiang<br/>
 *         (xcl_168@aliyun.com) 
 *         
 */

public class PlotDataInfo {
		
		//坐标
		public float X = 0.0f;
		public float Y = 0.0f;
				
		//标签
		public String Label = "";
		
		//将当前为第几个tick传递轴，用以区分是否为主明tick
		public int ID = -1;
				
		
		public float labelX = 0.0f;
		public float labelY = 0.0f;
		
		
		public PlotDataInfo(){};
		
		public PlotDataInfo(float x, float y, String label)
		{
			 X = x;
			 Y = y;
			 Label = label;
			 labelX = x;
			 labelY = y;
		};
		
		public PlotDataInfo(int id, float x, float y, String label)
		{
			 ID = id;
			 X = x;
			 Y = y;
			 Label = label;
			 
			 labelX = x;
			 labelY = y;
		}
		
		public PlotDataInfo(int id, float x, float y, String label, float lx, float ly)
		{
			 ID = id;
			 X = x;
			 Y = y;
			 Label = label;
			 
			 labelX = lx;
			 labelY = ly;
		}

		public float getX() {
			return X;
		}

		public void setX(float x) {
			X = x;
		}

		public float getY() {
			return Y;
		}

		public void setY(float y) {
			Y = y;
		}

		public String getLabel() {
			return Label;
		}

		public void setLabel(String label) {
			Label = label;
		}

		public int getID() {
			return ID;
		}

		public void setID(int iD) {
			ID = iD;
		};
		
		

}
