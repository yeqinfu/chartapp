package com.ppandroid.app.utils.info;

import android.content.Context;

import com.ppandroid.app.bean.login.MD5Body;
import com.ppandroid.app.utils.Utils_SharedPreferences;
import com.ppandroid.im.APP;

/**
 * Created by yeqinfu on 2017/3/16.
 * 用户信息工具类
 */

public class Utils_UserInfo {

    public static String getUserId(){
        Utils_SharedPreferences sp=new Utils_SharedPreferences(APP.Companion.getContext());
        return sp.getString("userID","16311");
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
        sp.clear();

    }


    public static boolean isLogined(Context context){
        Utils_SharedPreferences sp=new Utils_SharedPreferences(context);
        boolean logined = false;
        String string = sp.getString("Token", null);
        if (string == null) {
            logined = false;
        } else {
            logined = true;
        }
        return logined;
    }

}
