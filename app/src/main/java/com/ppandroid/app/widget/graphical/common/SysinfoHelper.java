/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */
package com.ppandroid.app.widget.graphical.common;

/**
 * @ClassName SysinfoHelper
 * @Description  得到系统信息,如版本之类
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  
 */

public class SysinfoHelper {
	
	private static SysinfoHelper instance = null;
	
	public SysinfoHelper()
	{	
	}
	
	public static synchronized SysinfoHelper getInstance(){
		if(instance == null){
			instance = new SysinfoHelper();
		}
		return instance;
	}
	
	
	/**
	 * android自3.0引入了硬件加速，即使用GPU进行绘图.但低版本的Android不支持这个类，
	 * 为了兼容性，在低版本中将其硬件加速相关的代码设为不可用。
	 * @return 系统是否包含硬件加速类
	 */
	public boolean supportHardwareAccelerated()
	{
		boolean result = true;
		int currentVersion = android.os.Build.VERSION.SDK_INT;	
		//android 3.0 == android.os.Build.VERSION_CODES.HONEYCOMB
		if(currentVersion < 11) result = false;
		return result;
	}

}
