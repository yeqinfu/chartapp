/*
 * Created by yeqinfu on 18-1-19 上午9:11
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.jpush;

import android.content.Context;
import android.text.TextUtils;

import com.ppandroid.app.utils.Utils_SharedPreferences;

import java.util.HashSet;

/**
 * Created by yeqinfu on 2018/1/19.
 * 小红点逻辑过于简单，未读数存在sp
 */

public class Utils_SharePreferenceData {
    public static String split="_jxt_";
    public static String key="msg_key";
    public static HashSet<String> StrToHashSet(String str){
        String[] strs=str.split(split);
        HashSet<String> set=new HashSet();
        for (String item :strs){
            if (!TextUtils.isEmpty(item)){
                set.add(item);
            }
        }
        return set;
    }
    public static String hashSetToStr(HashSet<String> set){
        String result="";

        for (String str:set){
            result+=str+split;
        }
        if (result.length()>split.length()){
            result.substring(0,result.length()-split.length());
        }
        return result;
    }
    public static void removeTypeMsg(Context context,String type){
        Utils_SharedPreferences sp=new Utils_SharedPreferences(context);
        String data=sp.getString(key,"");
        HashSet<String> set=StrToHashSet(data);
        set.remove(type);
        sp.put(key,hashSetToStr(set));
    }
    public static void putTypeMsg(Context context, String type){
        Utils_SharedPreferences sp=new Utils_SharedPreferences(context);
        String data=sp.getString(key,"");
        HashSet<String> set=StrToHashSet(data);
        set.add(type);
        sp.put(key,hashSetToStr(set));
    }
    public static HashSet<String> getHashSet(Context context){
        Utils_SharedPreferences sp=new Utils_SharedPreferences(context);
        String data=sp.getString(key,"");
        return StrToHashSet(data);
    }
    public static boolean isContains(Context context,String str){
        Utils_SharedPreferences sp=new Utils_SharedPreferences(context);
        String data=sp.getString(key,"");
        return StrToHashSet(data).contains(str);
    }
}
