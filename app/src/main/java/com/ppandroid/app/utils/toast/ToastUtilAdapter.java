/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.utils.toast;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.Toast;

import com.ppandroid.app.base.SampleApplicationLike;
import com.ppandroid.app.utils.DebugLog;
import com.ppandroid.app.utils.activitymanager.ActivityManager;

/**
 * 既要保持单例模式，又要保证静态变量toast内部中的context能够及时回收？？
 * 这边是有问题的，toast这个私有变量是intance静态变量的成员变量，instance不回收
 * toast也就不回收，toast不回收，context也就不回收。导致AC_Login这个页面第一次
 * 用到toast之后，生成instance对象之后，AC_Login的上下文永远被instance持有，所以 一直都有AC_Login内存泄漏的提示。
 * 要保持单例又要回收两者不可得兼
 * ==============================以上是针对历史版本代码的单例Toast问题的解释==============================
 * 要保持单例防止多次toast，应该是保持页面实例内的单例，而不是全局单例
 * 而现在改进的写法，保证下次toast的时候mContext对象会被替换成
 */
public class ToastUtilAdapter {
	private static ToastUtilAdapter	instance;

	private Toast					toast;
	private Handler					handler;

	private ToastUtilAdapter() {
		handler = new Handler();
	}

	public static ToastUtilAdapter getInstance() {
		if (instance == null) {
			synchronized (ToastUtilAdapter.class) {
				if (instance == null) {
					instance = new ToastUtilAdapter();
				}
			}
		}
		return instance;
	}

	private Activity mContext;
	public void toast(final String toastMsg) {
		if (TextUtils.isEmpty(toastMsg)) {
			return;
		}
		handler.post(new Runnable() {
			@Override
			public void run() {
			    if (mContext==null){
			        mContext=getContext();
                    toast = Toast.makeText(mContext, toastMsg, Toast.LENGTH_SHORT);
                }else{
			        if (ActivityManager.getActivityManager().isInStack(mContext)){//如果上次保留的mContext还在栈内
                        toast.setText(toastMsg);
                        toast.setDuration(Toast.LENGTH_SHORT);
                    }else{//不在栈内移除引用从新new一个toast对象
                        mContext=getContext();
                        toast = Toast.makeText(mContext, toastMsg, Toast.LENGTH_SHORT);
                    }
                }

				/*	
				这个是旧代码的单例写法，会导致第一个使用的Activity如AC_Login上下文泄漏
				if (toast == null) {
						 toast = Toast.makeText(getContext(), toastMsg, Toast.LENGTH_SHORT);
					}
					else {
						toast.setText(toastMsg);
						toast.setDuration(Toast.LENGTH_SHORT);
					}*/
                DebugLog.d("======================="+mContext);
				toast.show();
			}
		});
	}

	/**
	 * 这边改成获取当前栈顶的activity对象作为上下文
	 * 增加一个activity的监听，如果当前的mContext被回收，则及时释放引用
	 * @return
	 */
	private Activity getContext() {
	    Activity current=ActivityManager.getActivityManager().currentActivity();
        SampleApplicationLike.getContext().registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                if (activity==mContext){
                    mContext=null;
                    DebugLog.d("====================释放==============="+activity);
                }

            }
        });
		return current;
	}

	public void toast(int resId) {

		toast(getContext().getResources().getString(resId));
	}

}
