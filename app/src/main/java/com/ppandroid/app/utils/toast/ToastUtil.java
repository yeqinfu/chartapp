package com.ppandroid.app.utils.toast;

import android.content.Context;
import android.text.TextUtils;

public class ToastUtil {

	public static void toast(Context context,String toastMsg){
        if (!TextUtils.isEmpty(toastMsg)){
            ToastUtilAdapter.getInstance().setContext(context).toast(toastMsg);
        }
	}
	
	public static void toast(Context context,int resId){
		ToastUtilAdapter.getInstance().setContext(context).toast(resId);
	}


	
}
