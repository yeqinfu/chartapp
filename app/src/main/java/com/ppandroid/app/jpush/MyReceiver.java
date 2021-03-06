/*
 * Created by yeqinfu on 17-10-27 下午2:45
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.jpush;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.ppandroid.app.base.AC_ContentFG;
import com.ppandroid.app.bean.ET_RedPoint;
import com.ppandroid.app.home.FG_News;
import com.ppandroid.app.home.news.FG_EnergyComparison;
import com.ppandroid.app.home.news.FG_EnergyList;
import com.ppandroid.app.home.news.FG_FaultHistory;
import com.ppandroid.app.home.news.FG_SystemNewList;
import com.ppandroid.app.utils.DebugLog;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import cn.jpush.android.api.JPushInterface;

/**
 * 自定义接收器
 * 
 * 如果不定义这个 Receiver，则： 1) 默认用户会打开主界面 2) 接收不到自定义消息
 */
public class MyReceiver extends BroadcastReceiver {
	private static final String TAG = "JPush";

	@Override
	public void onReceive(Context context, Intent intent) {
		try {
			Bundle bundle = intent.getExtras();
			DebugLog.d(TAG, "[MyReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(context,bundle));

			if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
				String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
				DebugLog.d(TAG, "[MyReceiver] 接收Registration Id : " + regId);
				//send the Registration Id to your server...

			}
			else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
				DebugLog.d(TAG, "[MyReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
				//	processCustomMessage(context, bundle);
			}
			else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
				DebugLog.d(TAG, "[MyReceiver] 接收到推送下来的通知");
				int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
				DebugLog.d(TAG, "[MyReceiver] 接收到推送下来的通知的ID: " + notifactionId);
			}
			else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
				DebugLog.d(TAG, "[MyReceiver] 用户点击打开了通知");
				toDetailPage(context, bundle);

				/*	//打开自定义的Activity
					Intent i = new Intent(context, TestActivity.class);
					i.putExtras(bundle);
					//i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
					context.startActivity(i);*/

			}
			else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
				DebugLog.d(TAG, "[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
				//在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..

			}
			else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
				boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
				DebugLog.w(TAG, "[MyReceiver]" + intent.getAction() + " connected state change to " + connected);
			}
			else {
				DebugLog.d(TAG, "[MyReceiver] Unhandled intent - " + intent.getAction());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void toDetailPage(Context context, Bundle bundle) {
		try {
			JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
			Iterator<String> it = json.keys();
			while (it.hasNext()) {
				String myKey = it.next().toString();
				if (myKey.equals("type")) {
					//消息转发要求隐藏小红点
                    /**新增逻辑，把消息放到sp中作为伪数据库存储。增加之后，ET_RedPoin的type字段就不做使用*/
                    Utils_SharePreferenceData.removeTypeMsg(context,json.optString(myKey));
					EventBus.getDefault().post(new ET_RedPoint(ET_RedPoint.TASKID_RED_POINT_SHOW_MAIN));
					if (json.optString(myKey).equals(FG_News.BN_Data.Companion.getFAILUE_WARNING()+"")) {//故障报警
						Intent intent = AC_ContentFG.createIntent(context, FG_FaultHistory.class.getName());
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						context.startActivity(intent);
					}
					else if (json.optString(myKey).equals(FG_News.BN_Data.Companion.getENERGY_COLLECT()+"")) {//能耗总会
						Intent intent = AC_ContentFG.createIntent(context, FG_EnergyList.class.getName());
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						context.startActivity(intent);
					}
					else if (json.optString(myKey).equals(FG_News.BN_Data.Companion.getSYSTEM()+"")) {//系统消息
						Intent intent = AC_ContentFG.createIntent(context, FG_SystemNewList.class.getName());
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						context.startActivity(intent);
					}
					else if (json.optString(myKey).equals(FG_News.BN_Data.Companion.getPOWER_COMPARISON()+"")) {//能耗对比
						Intent intent = AC_ContentFG.createIntent(context, FG_EnergyComparison.class.getName());
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						context.startActivity(intent);
					}
					else if (json.optString(myKey).equals(FG_News.BN_Data.Companion.getWATER_COMPARISON()+"")) {//水能耗对比
						Intent intent = AC_ContentFG.createIntent(context, FG_EnergyComparison.class.getName(), FG_News.Companion.createBundle());
						intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						context.startActivity(intent);
					}
					else if (json.optString(myKey).equals(FG_News.BN_Data.Companion.getWATER_COLLECT()+"")) {//水能耗汇总
						Intent intent = AC_ContentFG.createIntent(context, FG_EnergyList.class.getName(), FG_News.Companion.createBundle());
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						context.startActivity(intent);
					}
				}

			}
		} catch (JSONException e) {
			DebugLog.e(TAG, "Get message extra JSON error!");
		}
	}

	// 打印所有的 intent extra 数据
	private static String printBundle(Context context,Bundle bundle) {
		StringBuilder sb = new StringBuilder();
		for (String key : bundle.keySet()) {
			if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
				sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
			}
			else if (key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)) {
				sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
			}
			else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
				if (TextUtils.isEmpty(bundle.getString(JPushInterface.EXTRA_EXTRA))) {
					DebugLog.i(TAG, "This message has no Extra data");
					continue;
				}

				try {
					JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
					Iterator<String> it = json.keys();

					while (it.hasNext()) {
						String myKey = it.next().toString();
						sb.append("\nkey:" + key + ", value: [" + myKey + " - " + json.optString(myKey) + "]");
						if (myKey.equals("type")) {
                            /**新增逻辑，把消息放到sp中作为伪数据库存储。增加之后，ET_RedPoin的type字段就不做使用*/
                            Utils_SharePreferenceData.putTypeMsg(context,json.optString(myKey));
                            EventBus.getDefault().post(new ET_RedPoint(ET_RedPoint.TASKID_RED_POINT_SHOW_MAIN));
						}

					}
				} catch (JSONException e) {
					DebugLog.e(TAG, "Get message extra JSON error!");
				}

			}
			else {
				sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
			}
		}
		return sb.toString();
	}

	//send msg to MainActivity
	/*private void processCustomMessage(Context context, Bundle bundle) {
		if (MainActivity.isForeground) {
			String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
			String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
			Intent msgIntent = new Intent(MainActivity.MESSAGE_RECEIVED_ACTION);
			msgIntent.putExtra(MainActivity.KEY_MESSAGE, message);
			if (!ExampleUtil.isEmpty(extras)) {
				try {
					JSONObject extraJson = new JSONObject(extras);
					if (extraJson.length() > 0) {
						msgIntent.putExtra(MainActivity.KEY_EXTRAS, extras);
					}
				} catch (JSONException e) {
	
				}
	
			}
			LocalBroadcastManager.getInstance(context).sendBroadcast(msgIntent);
		}
	}*/
}
