package com.ppandroid.app.http.builder;


import com.ppandroid.app.http.OkHttpUtils;
import com.ppandroid.app.http.request.OtherRequest;
import com.ppandroid.app.http.request.RequestCall;

/**
 * Created by zhy on 16/3/2.
 */
public class HeadBuilder extends GetBuilder
{
    @Override
    public RequestCall build()
    {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers,id).build();
    }
}
