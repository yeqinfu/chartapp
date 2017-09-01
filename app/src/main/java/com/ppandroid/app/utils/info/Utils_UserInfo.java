package com.ppandroid.app.utils.info;

import android.content.Context;
import android.text.TextUtils;

import com.ppandroid.app.bean.login.LoginBody;
import com.ppandroid.app.bean.login.MD5Body;
import com.ppandroid.app.utils.Utils_SharedPreferences;

/**
 * Created by yeqinfu on 2017/3/16.
 * 用户信息工具类
 */

public class Utils_UserInfo {

    public static String getCompanyName(Context context){
        Utils_SharedPreferences sp=new Utils_SharedPreferences(context);
       return  sp.getString("companyName","");
    }
    public static String getTempAccount(Context context){
        Utils_SharedPreferences sp=new Utils_SharedPreferences(context);
       return  sp.getString("temp_account","");
    }
    public static void saveTempAccount(Context context,String account){
        Utils_SharedPreferences sp=new Utils_SharedPreferences(context);
        sp.put("temp_account",account);
    }

    public static String getUserId(Context context){
        Utils_SharedPreferences sp=new Utils_SharedPreferences(context);
        return sp.getString("userID","16311");
    }
    public static String getUserMD5(Context context){
        Utils_SharedPreferences sp=new Utils_SharedPreferences(context);
        return sp.getString("MD5","");
    }
    public static void saveReloginInfo(Context context,MD5Body body){
        Utils_SharedPreferences sp=new Utils_SharedPreferences(context);
        sp.put("userID", body.getMessage().getId()+"");
        sp.put("MD5", body.getMessage().getMd5());
        sp.put("Token", body.getMessage().getTokenDto().getToken());
    }
    /**
     * 清除用户信息
     * @param context
     */
    public static void clearUserInfo(Context context){
        Utils_SharedPreferences sp=new Utils_SharedPreferences(context);
        sp.put("userID", "");
        sp.put("MD5", "");
        sp.put("Token", "");
        sp.put("companyName", "");

    }
    public static void saveInfo(Context context, LoginBody body){
        Utils_SharedPreferences sp=new Utils_SharedPreferences(context);
        sp.put("userID", body.getMessage().getId()+"");
        sp.put("MD5", body.getMessage().getMd5());
        sp.put("Token", body.getMessage().getTokenDto().getToken());
        sp.put("companyName", body.getMessage().getCompanyName());
    }


    public static boolean isLogined(Context context){
        Utils_SharedPreferences sp=new Utils_SharedPreferences(context);
        boolean logined = false;
        String string = sp.getString("Token", null);
        if (TextUtils.isEmpty(string)) {
            logined = false;
        } else {
            logined = true;
        }
        return logined;
    }

}
