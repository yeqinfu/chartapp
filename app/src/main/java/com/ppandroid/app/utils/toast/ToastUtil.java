/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.utils.toast;

import android.text.TextUtils;

public class ToastUtil {

	public static void toast(String toastMsg){
        if (!TextUtils.isEmpty(toastMsg)){
            ToastUtilAdapter.getInstance().toast(toastMsg);
        }
	}
	
	public static void toast(int resId){
		ToastUtilAdapter.getInstance().toast(resId);
	}


	
}
