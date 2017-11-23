/*
 * Created by yeqinfu on 17-10-17 上午9:28
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.base;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.ppandroid.app.AC_Login;
import com.ppandroid.app.R;
import com.ppandroid.app.http.OkHttpUtils;
import com.ppandroid.app.utils.AppExceptionHandler;
import com.ppandroid.app.utils.DebugLog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.interfaces.BetaPatchListener;
import com.tencent.tinker.loader.app.DefaultApplicationLike;

import java.util.Locale;

import cn.jpush.android.api.JPushInterface;
import okhttp3.OkHttpClient;

/**
 * Created by yeqinfu on 2017/10/12.
 */

public class SampleApplicationLike extends DefaultApplicationLike {

    public static final String TAG = "Tinker";

    public SampleApplicationLike(Application application, int tinkerFlags,
                                 boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime,
                                 long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }



    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplication();
        initConfig();
        // 这里实现SDK初始化，appId替换成你的在Bugly平台申请的appId
        // 调试时，将第三个参数改为true
        Bugly.init(getApplication(), "5463838ad7", true);
    }




    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        // you must install multiDex whatever tinker is installed!
        MultiDex.install(base);

        // 安装tinker
        // TinkerManager.installTinker(this); 替换成下面Bugly提供的方法
        Beta.installTinker(this);

        //设置是否显示弹窗提示用户重启
        Beta.canNotifyUserRestart = true;
        Beta.betaPatchListener = new BetaPatchListener() {
            @Override
            public void onPatchReceived(String patchFile) {
                Log.d(TAG, "补丁下载地址" + patchFile);
            }

            @Override
            public void onDownloadReceived(long savedLength, long totalLength) {
                Log.d(TAG,
                        String.format(Locale.getDefault(), "%s %d%%",
                                Beta.strNotificationDownloading,
                                (int) (totalLength == 0 ? 0 : savedLength * 100 / totalLength)));
            }

            @Override
            public void onDownloadSuccess(String msg) {
                Log.d(TAG, "补丁下载成功");
            }

            @Override
            public void onDownloadFailure(String msg) {
                Log.d(TAG, "补丁下载失败");

            }

            @Override
            public void onApplySuccess(String msg) {
                Log.d(TAG, "补丁应用成功");
            }

            @Override
            public void onApplyFailure(String msg) {
                Log.d(TAG, "补丁应用失败");
            }

            @Override
            public void onPatchRollback() {
                Log.d(TAG, "补丁回滚5");
            }
        };
        //主动检查更新
        Beta.checkUpgrade(false,true);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void registerActivityLifecycleCallback(Application.ActivityLifecycleCallbacks callbacks) {
        getApplication().registerActivityLifecycleCallbacks(callbacks);
    }


    public static Application context;

    public static Application getContext() {
        return context;
    }

    public static void setContext(Application context) {
        SampleApplicationLike.context = context;
    }

    private void initConfig() {

        /**极光推送*/
        JPushInterface.setDebugMode(DebugLog.isDebuggable());
        JPushInterface.init(getApplication());
        DebugLog.d("yeqinfu",JPushInterface.getRegistrationID(getApplication())+"===");


        PersistentCookieJar cookieJar=new PersistentCookieJar(new SetCookieCache(),new SharedPrefsCookiePersistor(context));
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cookieJar(cookieJar)
                //其他配置
                .build();
        OkHttpUtils.initClient(okHttpClient);
        if (LeakCanary.isInAnalyzerProcess(context)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
        }else{
            LeakCanary.install(context);
        }
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
                return new ClassicsHeader(context).setSpinnerStyle(SpinnerStyle.Translate);//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                return new ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Translate);
            }
        });
        /*****************************************************************
         * 闪退处理
         *****************************************************************/
        AppExceptionHandler.getInstance().init(context);
    }
    //toLogin
    public static void toLogin(){
        Intent it=new Intent();
        it.setClass(context, AC_Login.class);
        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(it);
    }







}