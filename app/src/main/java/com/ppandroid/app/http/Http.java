package com.ppandroid.app.http;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.ppandroid.app.R;
import com.ppandroid.app.bean.ErrorBody;
import com.ppandroid.app.bean.login.MD5Body;
import com.ppandroid.app.bean.mine.BN_ChooseInstrument;
import com.ppandroid.app.http.callback.StringCallback;
import com.ppandroid.app.utils.DebugLog;
import com.ppandroid.app.utils.Utils_SharedPreferences;
import com.ppandroid.app.utils.activitymanager.ActivityManager;
import com.ppandroid.app.utils.info.Utils_UserInfo;
import com.ppandroid.im.APP;
import com.ppandroid.im.bean.BaseBody;
import com.ppandroid.im.utils.Contants;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.Map;

import okhttp3.Call;

import static org.jetbrains.anko.DialogsKt.toast;

/**
 * Created by yeqinfu on 2017/7/31.
 */

public class Http {

    private void test(){
        BN_ChooseInstrument bn=new BN_ChooseInstrument();
        int dd=-1;
        int count=0;
        for(int i=0;i<bn.getMessage().size();i++){
            if (bn.getMessage().get(i).getId()==dd){
                count=i;
            }
        }
    }

	public static <T extends BaseBody>  void get(final Context context, String url, final Class<T> tt, final MyCallBack<T> callBack) {
        final WeakReference<Context> mWeakContext=new WeakReference<Context>(context);
        url=addBaseParams(context,url);
        if (!url.startsWith("http")){
            url=  Contants.Companion.getBaseUrl()+url;
        }
        DebugLog.i("httpget==>" + url + "\n\n");
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
			@Override
			public void onError(Call call, Exception e, int id) {
				e.printStackTrace();
                ErrorBody errorBody=new ErrorBody();
                errorBody.setMessage(e.getMessage());
                callBack.onError(errorBody);
			}

			@Override
			public void onResponse(String response, int id) {
                DebugLog.i("httpget==>" + response + "\n\n");
                if (isGoodJson(response)){
                    ErrorBody errorBody = parseErrorBody(response);
                    if (errorBody==null){
                        Gson gson = new Gson();
                        callBack.onResponse(gson.fromJson(response,tt));
                    }else{
                        callBack.onError(errorBody);
                        parseError(mWeakContext.get(), errorBody);
                    }


                }else{
                    ErrorBody body=new ErrorBody();
                    body.setMessage("json解析异常");
                    callBack.onError(body);
                }
			}
		});
	}

	public static <T extends BaseBody> void post(final Context context, String url,  Map<String, String> params, final Class<T> tt, final MyCallBack<T> callBack) {
        params=addBaseParams(context,params);
        final WeakReference<Context> mWeakContext=new WeakReference<Context>(context);
        if (!url.startsWith("http")){
            url=  Contants.Companion.getBaseUrl()+url;
        }
        DebugLog.i("httppost==>" + url + "\n\n" + params.toString() + "\n\n");
		OkHttpUtils.post().params(params).url(url).build().execute(new StringCallback() {
			@Override
			public void onError(Call call, Exception e, int id) {
				e.printStackTrace();
			}

			@Override
			public void onResponse(String response, int id) {
                DebugLog.i("httpget==>" + response + "\n\n");
                if (isGoodJson(response)){
                    ErrorBody errorBody = parseErrorBody(response);
                    if (errorBody==null){
                        callBack.onResponse((T) parseJson(response,tt));
                    }else{
                        callBack.onError(errorBody);
                        parseError(mWeakContext.get(), errorBody);
                    }

                }else{
                    ErrorBody body=new ErrorBody();
                    body.setMessage("json解析异常");
                    callBack.onError(body);
                }

			}
		});
	}


    /**
     * 错误码处理
     *
     * @param errorBody
     */
    private static void parseError(Context context, ErrorBody errorBody) {
        if (errorBody.getErrorCode() == null) {
            return;
        }
        switch (errorBody.getErrorCode()) {
            case HttpConstant.SERVICE_ERROR://服务器异常
                toast(context, R.string.service_error);
                break;
            case HttpConstant.TOKEN_BLANK_ERROR://TOKEN 为空
                toast(context, R.string.token_invalid);
                clearInfoAndLogin(context);
                break;
            case HttpConstant.TOKEN_INVALID_ERROR://TOKEN失效 尝试MD5
                getTokenByMD5(context);
                break;
            case HttpConstant.RELOAAGIN_MD5_INVALID_ERROR://自动登录已过期，请重新登录
                toast(context, errorBody.getMessage());
                clearInfoAndLogin(context);
                break;
            case HttpConstant.BLANK_ERROR://校验数据为空 比如验证码输入为空，评论输入为空
                toast(context, errorBody.getMessage());
                break;
            case HttpConstant.CHECK_ERROR:
                toast(context, errorBody.getMessage());
                break;
            default:
                if (!TextUtils.isEmpty(errorBody.getMessage())) {
                    toast(context, errorBody.getMessage());
                }
                break;

        }

    }

    private static void clearInfoAndLogin(Context context) {
        Utils_UserInfo.clearUserInfo(context);
        APP.Companion.toLogin();
        ActivityManager.getActivityManager().popAllActivity();
        ((Activity)context).finish();
    }

    private static boolean  isAutoCheck=false;
    /**
     * 用md5获取新的token
     */
    public static void getTokenByMD5(final Context context) {
        if (isAutoCheck){
            return;
        }
        isAutoCheck=true;
        DebugLog.i("http", "====================== token 失效 尝试MD5 登录====================================");
        Utils_SharedPreferences sp = new Utils_SharedPreferences(context);
        String url =  Contants.Companion.getBaseUrl()+"user/login/auto/check.json" + "?id=" + sp.getString("userID", null) + "&md5=" + sp.getString("MD5", null);
        DebugLog.d("http->md5登录" + url);
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                e.printStackTrace();
                isAutoCheck=false;

            }

            @Override
            public void onResponse(String response, int id) {
                isAutoCheck=false;
                DebugLog.d("http->md5登录" + response);

                ErrorBody errorBody = parseErrorBody(response);
                if (errorBody == null) {
                    Gson gson = new Gson();
                    MD5Body body = gson.fromJson(response, MD5Body.class);
                    if (body != null) {
                        DebugLog.i("http", "====================== token 失效 尝试MD5 登录成功新Token===" + body.getMessage().getTokenDto().getToken());
                        Utils_UserInfo.saveReloginInfo(context, body);
                    }
                }
                else {
                    parseError(context, errorBody);
                }
            }
        });




    }

    @Nullable
    public static Object parseJson(String json, Object response) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            boolean boolean1 = jsonObject.getBoolean("isSuccess");
            if (boolean1) {
                Gson gson = new Gson();
                response = gson.fromJson(json, response.getClass());
                return response;
            }
            else {
                DebugLog.e(jsonObject.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ErrorBody parseErrorBody(String json) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            boolean boolean1 = jsonObject.getBoolean("isSuccess");
            if (!boolean1) {
                Gson gson = new Gson();
                ErrorBody errorBody = gson.fromJson(json, ErrorBody.class);
                if (errorBody != null) {
                    return errorBody;
                }
                else {
                    return null;
                }
            }
            else {
                return null;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isGoodJson(String json) {
        if ("null".equals(json)) {
            return false;
        }

        try {
            new JsonParser().parse(json);
            return true;
        } catch (JsonParseException e) {
            DebugLog.e("bad json: " + json);
            return false;
        }
    }

    /**
     * 添加基本参数post
     *
     * @param params
     * @return
     */
    private static Map<String, String> addBaseParams(Context context, Map<String, String> params) {
        Utils_SharedPreferences sp = new Utils_SharedPreferences(context);
        if (!TextUtils.isEmpty(sp.getString("Token", ""))) {
            params.put("X-Auth-Token", sp.getString("Token", ""));
        }
        return params;
    }

    /**
     * 添加基本参数get
     *
     * @param context
     * @param url
     * @return
     */
    public static String addBaseParams(Context context, String url) {
        Utils_SharedPreferences sp = new Utils_SharedPreferences(context);
        if (!url.contains("X-Auth-Token=") && (!TextUtils.isEmpty(sp.getString("Token", "")))) {
            if (url.contains("?")) {
                url += "&X-Auth-Token=" + sp.getString("Token", "");
            }
            else {
                url += "?X-Auth-Token=" + sp.getString("Token", "");
            }

        }

        return url;
    }

}
