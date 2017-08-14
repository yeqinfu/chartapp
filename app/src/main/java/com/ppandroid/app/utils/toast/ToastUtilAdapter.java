package com.ppandroid.app.utils.toast;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class ToastUtilAdapter {
	private static ToastUtilAdapter instance;
	
	private Toast toast;
	private WeakReference<Context> context;
	private Handler handler;
	
	private ToastUtilAdapter (Context context){
		this.context = new WeakReference<Context>(context);
		handler = new Handler();
	}
	
	public static ToastUtilAdapter getInstance(Context context){
		if(instance == null){
			synchronized (ToastUtilAdapter.class) {
				if(instance == null){
					instance = new ToastUtilAdapter(context);
				}
			}
		}
		return instance;
	}

	public void toast(final String toastMsg){
		if(context== null){
			return ;
		}
		if (TextUtils.isEmpty(toastMsg)){
            return;
        }
		handler.post(new Runnable() {
			@Override
			public void run() {
				if (toast == null) {  
					toast = Toast.makeText(context.get(), toastMsg, Toast.LENGTH_SHORT);
		        } else {  
		        	toast.setText(toastMsg);  
		        	toast.setDuration( Toast.LENGTH_SHORT);  
		        }  
				toast.show();  
			}
		});
	}
	
	public void toast(int resId){
		if(context == null){
			return ;
		}
		toast( context.get().getResources().getString(resId));
	}
	
}